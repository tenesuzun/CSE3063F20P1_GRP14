package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Random;

public class UserModel {
    @JsonProperty("user id")
    private Integer id;
    @JsonProperty("user name")
    private String username;
    @JsonProperty("user type")
    private String type;
    @JsonProperty("datasets")
    private List<Integer> datasets;
    @JsonProperty("ConsistencyCheckProbability")
    private Double consistencyProb;

    public UserModel() {
    }

    public UserModel(Integer id, String username, String type,List<Integer> datasets,Double consistencyProb) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.datasets = datasets;
        this.consistencyProb = consistencyProb;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public List<Integer> getDatasets() {
        return datasets;
    }

    public Double getConsistencyProb() {
        return consistencyProb;
    }

    public Boolean shouldLabelAgain(){
        int randomNo = new Random().nextInt(100);
        System.out.println("Label consistensy prob is "+consistencyProb+" Random No: "+randomNo);
        return randomNo < consistencyProb * 100;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", probability='" + consistencyProb + '\'' +
                ", datasets=" + datasets +
                '}';
    }
}
