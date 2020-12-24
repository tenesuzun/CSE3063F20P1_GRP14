package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputModel {
    @JsonProperty("dataset id")
    private Integer id;
    @JsonProperty("dataset name")
    private String datasetName;
    @JsonProperty("maximum number of labels per instance")
    private Integer maxInstance;
    @JsonProperty("class labels")
    private List<LabelModel> labels;
    @JsonProperty("instances")
    private List<InstanceModel> instances;

    public InputModel() {
    }

    public InputModel(Integer id, String datasetName, Integer maxInstance, List<LabelModel> labels, List<InstanceModel> instances) {
        this.id = id;
        this.datasetName = datasetName;
        this.maxInstance = maxInstance;
        this.labels = labels;
        this.instances = instances;
    }

    public Integer getId() {
        return id;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public Integer getMaxInstance() {
        return maxInstance;
    }

    public List<LabelModel> getLabels() {
        return labels;
    }

    public List<InstanceModel> getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        return "InputModel{" +
                "id='" + id + '\'' + "\n" +
                ", datasetName='" + datasetName + '\'' + "\n" +
                ", maxInstance=" + maxInstance + "\n" +
                ", labels=" + labels + "\n" +
                ", instances=" + instances +
                '}';
    }
}
