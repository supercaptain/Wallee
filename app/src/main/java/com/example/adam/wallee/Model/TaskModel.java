package com.example.adam.wallee.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adam on 5.3.2015.
 */
public class TaskModel implements Serializable {

    private int idTask;
    private String nameTask;
    private int statusTask;
    private Date dateTask;


    public TaskModel() {

    }

    public TaskModel(int idTask, String nameTask) {
        this(idTask, nameTask, 0);
    }

    public TaskModel(int idTask, String nameTask, int statusTask) {
        this(idTask, nameTask, statusTask, null);
    }

    public TaskModel(int idTask, String nameTask, int statusTask, Date dateTask) {
        this.idTask = idTask;
        this.nameTask = nameTask;
        this.statusTask = statusTask;
        this.dateTask = dateTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public int getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(int statusTask) {
        this.statusTask = statusTask;
    }

    public Date getDateTask() {
        return dateTask;
    }

    public void setDateTask(Date dateTask) {
        this.dateTask = dateTask;
    }




}
