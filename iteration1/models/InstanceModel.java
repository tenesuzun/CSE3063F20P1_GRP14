package iteration1.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstanceModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("instance")
    private String instance;

    public InstanceModel(){

    }

    public InstanceModel(String id, String instance) {
        this.id = id;
        this.instance = instance;
    }

    public String getId() {
        return id;
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "InstanceModel{" +
                "id='" + id + '\'' + "\n" +
                ", instance='" + instance + '\'' +
                '}';
    }
}
