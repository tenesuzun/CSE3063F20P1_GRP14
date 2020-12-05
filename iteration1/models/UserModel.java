package iteration1.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
    @JsonProperty("user id")
    private Integer id;
    @JsonProperty("user name")
    private String username;
    @JsonProperty("user type")
    private String type;

    public UserModel() {
    }

    public UserModel(Integer id, String username, String type) {
        this.id = id;
        this.username = username;
        this.type = type;
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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id + "\n" +
                ", username='" + username + '\'' + "\n" +
                ", type='" + type + '\'' + "\n" +
                '}';
    }
}
