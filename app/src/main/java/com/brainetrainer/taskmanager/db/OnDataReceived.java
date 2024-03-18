package com.brainetrainer.taskmanager.db;

import com.brainetrainer.taskmanager.adapters.BoxWithTasks;

import java.util.List;

public interface OnDataReceived {
    void onReceived(List<BoxWithTasks> boxWithTasks);
}
