package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class AssignmentModel {
    @JsonProperty("instanece id")
    private Integer instanceID;
    @JsonProperty("class label ids")
    private List<Integer> labelIds;
    @JsonProperty("user id")
    private Integer userId;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("final label")
    private int finalLabel;

    public AssignmentModel() {
    }

    public AssignmentModel(Integer instanceID, Integer userId, String datetime) {
        this.instanceID = instanceID;
        this.labelIds = new ArrayList<>();
        this.userId = userId;
        this.datetime = datetime;
    }

    public Integer getInstanceID() {
        return instanceID;
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

    public void addLabelID(Integer labelID){
        labelIds.add(labelID);
        System.out.println("Label with id "+labelID+" added to instance.");
    }

    public int getFinalLabel() {
        return finalLabel;
    }

    public void setFinalLabel(){
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (Integer labelID: labelIds){
            Integer count = hashMap.get(labelID);
            hashMap.put(labelID, count == null ? 1 : count+1);
        }

        int maxValueCount = -1;
        int maxValue = -1;
        for (Integer key: hashMap.keySet()){
            if (maxValueCount < hashMap.get(key)){
                maxValue = key;
                maxValueCount = hashMap.get(key);
            }
        }

        System.out.println("Final label value set to "+maxValue);
        this.finalLabel = maxValue;
    }

    @Override
    public String toString() {
        return "AssignmentModel{" +
                "id=" + instanceID + ", userId=" + userId + "\n" +
                ", labelIds=" + labelIds + "}\n";
    }
}
