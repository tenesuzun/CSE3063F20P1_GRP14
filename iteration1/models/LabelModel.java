package iteration1.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelModel {
    @JsonProperty("label id")
    private Integer id;
    @JsonProperty("label text")
    private String text;

    public LabelModel(){

    }

    public LabelModel(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "LabelModel{" +
                "id='" + id + '\'' + "\n" +
                ", text='" + text + '\'' +
                '}';
    }
}
