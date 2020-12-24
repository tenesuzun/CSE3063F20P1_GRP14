package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PathModel {
    @JsonProperty("path")
    private String path;

    @JsonProperty("dataset id")
    private int datasetID;

    @JsonProperty("dataset name")
    private String datasetName;

    public PathModel(){
    }

    public PathModel(String path, int datasetID, String datasetName) {
        this.path = path;
        this.datasetID = datasetID;
        this.datasetName = datasetName;
    }

    public String getPath() {
        return path;
    }

    public int getDatasetID() {
        return datasetID;
    }

    public String getDatasetName() {
        return datasetName;
    }

    @Override
    public String toString() {
        return "PathModel{" +
                "path='" + path + '\'' +
                ", datasetID=" + datasetID +
                ", datasetName='" + datasetName + '\'' +
                '}';
    }
}
