package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConfigModel {
    @JsonProperty("users")
    private List<UserModel> users;

    @JsonProperty("input paths")
    private List<PathModel> paths;

    @JsonProperty("CurrentDatasetId")
    private int currentDatasetID;

    public ConfigModel(){
    }

    public ConfigModel(List<UserModel> users, List<PathModel> paths){
        this.users = users;
        this.paths = paths;
    }

    public ConfigModel(List<UserModel> users){
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public List<PathModel> getPaths() {
        return paths;
    }


    public int getCurrentDatasetID() {
        return currentDatasetID;
    }

    @Override
    public String toString() {
        return "ConfigModel{" +
                "users=" + users +
                ", paths=" + paths +
                ", currentDatasetID=" + currentDatasetID +
                '}';
    }
}
