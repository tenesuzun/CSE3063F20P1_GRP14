package iteration2.controller;

import iteration2.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class MetricController {

    private final MetricModel metricModel = new MetricModel();

    public MetricModel getMetricModel() {
        return metricModel;
    }

    public void calculateDatasetCount(int count){
        System.out.println("Number of dataset is: "+count);
    }

    public void calculateDatasetMetrics(InputModel inputModel, List<AssignmentModel> labelAssignements){
        HashSet<Integer> uniqueList = labelAssignements.stream().map(AssignmentModel::getInstanceID).collect(Collectors.toCollection(HashSet::new));

        int percentage = uniqueList.size() * 100 / inputModel.getInstances().size();
        System.out.println(
                "Dataset ID: "+inputModel.getId()+"\n"+
                "Completeness Percentage: "+(percentage)
        );
        System.out.println(
                "Total number of labeled instances: "+labelAssignements.size()+"\n"+
                "Total number of unique instances: "+uniqueList.size()
        );

        metricModel.setCompletenessPercentage(percentage);
        metricModel.setTotalInstances(labelAssignements.size());
        metricModel.setTotalUniqueInstaces(uniqueList.size());
        calculateAssignementMetrics(inputModel,labelAssignements);
    }

    private void calculateAssignementMetrics(InputModel inputModel,List<AssignmentModel> labelAssignements){
        List<Integer> uniqueLabelList = new ArrayList<>();
        List<Integer> labelList = new ArrayList<>();
        List<Integer> uniqueUserList = new ArrayList<>();

        int labelCount = 0;
        for (AssignmentModel assignmentModel: labelAssignements){
            labelCount += assignmentModel.getLabelIds().size();

            for (int labelID: assignmentModel.getLabelIds()) {
                labelList.add(labelID);
                if (!uniqueLabelList.contains(labelID))
                    uniqueLabelList.add(labelID);
            }

            if (!uniqueUserList.contains(assignmentModel.getUserId()))
                uniqueUserList.add(assignmentModel.getUserId());
        }

        metricModel.setTotalAssignment(labelCount);
        metricModel.setUniqueTotalAssignment(uniqueLabelList.size());
        metricModel.setUniqueTotalUsers(uniqueUserList.size());
        calculateLabelFrequency(inputModel.getLabels(),labelList);
    }

    public void calculateAverageTimeSpentForLabel(long timeSpent){
        metricModel.addAverageTimeSpendList(timeSpent);
        metricModel.calculateStandartDeviation();
    }

    private void calculateLabelFrequency(List<LabelModel> labelModelList, List<Integer> labelList){
        List<LabelDistributionModel> labelDistributionList = new ArrayList<>();
        Map<Integer, Integer> hashMap = calculateLabelOccurences(labelList);
        int frequentLabelID = -1;
        int frequentLabelCount = 0;

        for (Integer key: hashMap.keySet()){
            labelDistributionList.add(new LabelDistributionModel(
                    (hashMap.get(key) * 100) / labelList.size(),
                    labelModelList
                            .stream()
                            .filter(labelModel -> labelModel.getId() == key)
                            .collect(Collectors.toList()).get(0).getText()
            ));

            if (frequentLabelCount < hashMap.get(key)){
                frequentLabelID = key;
                frequentLabelCount = hashMap.get(key);
            }
        }
        final int frequencyPercentage = (frequentLabelCount * 100) / labelList.size();
        metricModel.setLabelDistributionList(labelDistributionList);
        metricModel.setMostFrequentLabelID(frequentLabelID);
        metricModel.setMostFrequentLabelPercentage(frequencyPercentage);
    }

    private Map<Integer, Integer> calculateLabelOccurences(List<Integer> labelList){
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (Integer labelID: labelList){
            Integer count = hashMap.get(labelID);
            hashMap.put(labelID, count == null ? 1 : count+1);
        }

        return hashMap;
    }
}
