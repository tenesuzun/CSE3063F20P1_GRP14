package iteration2.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OutputModel {
    private List<InputModel> inputModel;
    @JsonProperty("class label assignments")
    private List<AssignmentModel> assignements;
    @JsonProperty("users")
    private List<UserModel> users;

    public OutputModel() {
    }

    public OutputModel(List<InputModel> inputModel, List<AssignmentModel> assignements, List<UserModel> users) {
        this.inputModel = inputModel;
        this.assignements = assignements;
        this.users = users;
    }

    public List<InputModel> getInputModel() {
        return inputModel;
    }

    public List<AssignmentModel> getAssignements() {
        return assignements;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "OutputModel{" +
                "inputModel=" + inputModel +
                ", assignements=" + assignements +
                ", users=" + users +
                '}';
    }
}
