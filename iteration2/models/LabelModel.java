package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelModel {
    @JsonProperty("label id")
    private int id;
    @JsonProperty("label text")
    private String text;

    public LabelModel(){

    }

    public LabelModel(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
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
