package iteration2.handlers;

import iteration2.controller.MetricController;
import iteration2.controller.MetricGenerator;
import iteration2.models.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LabelAssignementHandler {

    private final List<AssignmentModel> labelAssignements = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy',' HH:mm:ss");
    private final MetricController metricController = new MetricController();
    private final MetricGenerator metricGenerator = new MetricGenerator();

    public void labelDataset(UserModel userModel, InputModel dataset){
        metricController.getMetricModel().incrementDatasetCount();
        for (InstanceModel instance: dataset.getInstances()){
            long executionStartTime = System.currentTimeMillis();

            AssignmentModel assignmentModel = new AssignmentModel(Integer.parseInt(instance.getId()),userModel.getId(), dateFormat.format(new Date()));

            assignLabelToModel(assignmentModel, dataset.getLabels(), false);

            if (userModel.shouldLabelAgain()){
                System.out.println("Will be labeled again.");
                int randomAssignement = new Random().nextInt(labelAssignements.size());
                AssignmentModel reLabeledAssignement = labelAssignements.get(randomAssignement);

                assignLabelToModel(reLabeledAssignement, dataset.getLabels(), true);
            }
            System.out.println("Dataset ID: "+dataset.getId()+
                    "\nUser ID: "+userModel.getId()+
                    "\nInstance ID: "+instance.getId());

            metricController.calculateDatasetCount(1);
            metricController.calculateDatasetMetrics(dataset, labelAssignements);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long executionEndTime = System.currentTimeMillis();
            metricController.calculateAverageTimeSpentForLabel(executionEndTime - executionStartTime);

            metricGenerator.createMetricJson(metricController.getMetricModel());
        }
    }

    private void assignLabelToModel(AssignmentModel assignmentModel, List<LabelModel> labels, boolean isRelabeling){
        System.out.println("Label Assignement Started");
        int randomLabelID = new Random().nextInt(labels.size());
        assignmentModel.addLabelID(labels.get(randomLabelID).getId());
        if (!isRelabeling)
            labelAssignements.add(assignmentModel);
        System.out.println("Label assigned, LabelID: "+labels.get(randomLabelID).getId()+
                "\nAssignement Model: "+ assignmentModel.toString());
        assignmentModel.setFinalLabel();
    }

    public List<AssignmentModel> getLabelAssignements() {
        return labelAssignements;
    }
}
