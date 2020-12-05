package iteration1.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OutputModel {
    private InputModel inputModel;
    @JsonProperty("class label assignements")
    private List<AssignementModel> assignements;
    @JsonProperty("users")
    private List<UserModel> users;

    public OutputModel() {
    }

    public OutputModel(InputModel inputModel, List<AssignementModel> assignements, List<UserModel> users) {
        this.inputModel = inputModel;
        this.assignements = assignements;
        this.users = users;
    }

    public InputModel getInputModel() {
        return inputModel;
    }

    public List<AssignementModel> getAssignements() {
        return assignements;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "OutputModel{" +
                "inputModel=" + inputModel + "\n" +
                ", assignements=" + assignements + "\n" +
                ", users=" + users +
                '}';
    }
}
