package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelDistributionModel {
    @JsonProperty("label percentage")
    private int percentage;
    @JsonProperty("label name")
    private String name;

    public LabelDistributionModel(){
    }

    public LabelDistributionModel(int percentage, String name){
        this.percentage = percentage;
        this.name = name;
    }

    @Override
    public String toString() {
        return "LabelDistributionModel{" +
                "percentage=" + percentage +
                ", name='" + name + '\'' +
                '}';
    }
}
