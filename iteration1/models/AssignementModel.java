package iteration1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AssignementModel {
    @JsonProperty("instanece id")
    private Integer id;
    @JsonProperty("class label ids")
    private List<Integer> labelIds;
    @JsonProperty("user id")
    private Integer userId;
    @JsonProperty("datetime")
    private String datetime;

    public AssignementModel() {
    }

    public AssignementModel(Integer id, List<Integer> labelIds, Integer userId, String datetime) {
        this.id = id;
        this.labelIds = labelIds;
        this.userId = userId;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return "AssignmentModel{" +
                "id=" + id + "\n" +
                ", labelIds=" + labelIds + "\n" +
                ", userId=" + userId + "\n" +
                ", datetime=" + datetime +
                '}';
    }
}
