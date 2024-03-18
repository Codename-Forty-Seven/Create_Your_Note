package com.brainetrainer.taskmanager.adapters;

import java.io.Serializable;

public class BoxWithTasks implements Serializable {
    private String nameTask, mainTextTask;
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getMainTextTask() {
        return mainTextTask;
    }

    public void setMainTextTask(String mainTextTask) {
        this.mainTextTask = mainTextTask;
    }
}
