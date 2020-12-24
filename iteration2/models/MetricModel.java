package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MetricModel {
    @JsonProperty("number of dataset")
    private int datasetCount = 0;
    @JsonProperty("completeness percentage")
    private int completenessPercentage;
    @JsonProperty("total labeled instances")
    private int totalInstances;
    @JsonProperty("total unique labeled instances")
    private int totalUniqueInstaces;
    @JsonProperty("average time spend labeling")
    private final List<Long> avgTimeSpentList = new ArrayList<>();
    @JsonProperty("standart deviation")
    private Double standartDeviation;
    //Dataset
    @JsonProperty("total label assignments")
    private int totalAssignment;
    @JsonProperty("unique total label assignments")
    private int uniqueTotalAssignment;
    @JsonProperty("unique total users")
    private int uniqueTotalUsers;
    @JsonProperty("most frequent label")
    private int mostFrequentLabelID;
    @JsonProperty("most frequent label percentage")
    private int mostFrequentLabelPercentage;
    @JsonProperty("label assignement distribution")
    private List<LabelDistributionModel> labelDistributionList;

    public MetricModel(){
    }

    public void addAverageTimeSpendList(Long averageTimeSpend) {
        avgTimeSpentList.add(averageTimeSpend);
    }

    public void setCompletenessPercentage(int completenessPercentage) {
        this.completenessPercentage = completenessPercentage;
    }

    public void setTotalAssignment(int totalAssignment) {
        System.out.println("Total Label is "+totalAssignment);
        this.totalAssignment = totalAssignment;
    }

    public void setUniqueTotalAssignment(int uniqueTotalAssignment) {
        System.out.println("Total Unique Label is "+uniqueTotalAssignment);
        this.uniqueTotalAssignment = uniqueTotalAssignment;
    }

    public void setUniqueTotalUsers(int uniqueTotalUsers) {
        System.out.println("Total number of unique users "+uniqueTotalUsers);
        this.uniqueTotalUsers = uniqueTotalUsers;
    }

    public void setMostFrequentLabelPercentage(int mostFrequentLabelPercentage) {
        System.out.println("Frequent Label Percentage "+mostFrequentLabelPercentage);
        this.mostFrequentLabelPercentage = mostFrequentLabelPercentage;
    }

    public void setMostFrequentLabelID(int mostFrequentLabelID) {
        System.out.println("Frequent Label ID "+mostFrequentLabelID);
        this.mostFrequentLabelID = mostFrequentLabelID;
    }

    public void setLabelDistributionList(List<LabelDistributionModel> labelDistributionList) {
        this.labelDistributionList = labelDistributionList;
    }

    public void calculateStandartDeviation(){
        long mean = 0;
        for (Long avgTimeSpent : avgTimeSpentList) {
            mean += avgTimeSpent;
        }
        mean = mean / avgTimeSpentList.size();

        long variance = 0;
        for (Long avgTimeSpent : avgTimeSpentList) {
            variance += Math.pow(avgTimeSpent - mean, 2);
        }
        variance = variance / avgTimeSpentList.size();

        standartDeviation = Math.sqrt(variance);
        System.out.println("Standart Deviation is "+standartDeviation);
    }

    public void incrementDatasetCount() {
        this.datasetCount++;
    }

    public void setTotalInstances(int totalInstances) {
        this.totalInstances = totalInstances;
    }

    public void setTotalUniqueInstaces(int totalUniqueInstaces) {
        this.totalUniqueInstaces = totalUniqueInstaces;
    }

    @Override
    public String toString() {
        return "MetricModel{" +
                "datasetCount=" + datasetCount +
                ", completenessPercentage=" + completenessPercentage +
                ", totalInstances=" + totalInstances +
                ", totalUniqueInstaces=" + totalUniqueInstaces +
                ", avgTimeSpentList=" + avgTimeSpentList +
                ", standartDeviation=" + standartDeviation +
                ", totalAssignment=" + totalAssignment +
                ", uniqueTotalAssignment=" + uniqueTotalAssignment +
                ", uniqueTotalUsers=" + uniqueTotalUsers +
                ", mostFrequentLabelID=" + mostFrequentLabelID +
                ", mostFrequentLabelPercentage=" + mostFrequentLabelPercentage +
                ", labelDistributionList=" + labelDistributionList +
                '}';
    }
}
