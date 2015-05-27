package com.example.adam.wallee.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adam on 5.3.2015.
 */
public class UserModel implements Serializable{

    private String firstName;
    private String lastName;
    private String email;
    private String apiKey;
    private List<TaskModel> taskList;








    public List<TaskModel> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
