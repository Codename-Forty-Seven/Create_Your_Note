package com.brainetrainer.taskmanager;

import static com.brainetrainer.taskmanager.db.Constants.BOX_WITH_ITEM_INTENT;
import static com.brainetrainer.taskmanager.db.Constants.STATE_EDIT_OR_NOT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.brainetrainer.taskmanager.adapters.BoxWithTasks;
import com.brainetrainer.taskmanager.databinding.ActivityWriteNewTaskBinding;
import com.brainetrainer.taskmanager.db.AppExecuted;
import com.brainetrainer.taskmanager.db.MyDbManager;

public class WriteNewTaskActivity extends AppCompatActivity {
    private boolean editTaskOrNo = true;
    private BoxWithTasks boxWithTasks;
    private EditText edTxtNameTask, edTxtMainTextTask;
    private Button btnSaveTask;
    private MyDbManager myDbManager;
    private ActivityWriteNewTaskBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_new_task);
        initAllComponents();

        btnSaveTask.setOnClickListener(view -> onClickAddTaskToDb());
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
    }

    private void initAllComponents() {
        myDbManager = new MyDbManager(this);
        edTxtNameTask = binding.edTxtNameTask;
        edTxtMainTextTask = binding.edTxtMainTextTask;
        btnSaveTask = binding.btnSaveTask;
        boxWithTasks = new BoxWithTasks();
        Intent getIntent = getIntent();
        if (getIntent != null) {
            boxWithTasks = (BoxWithTasks) getIntent.getSerializableExtra(BOX_WITH_ITEM_INTENT);
            editTaskOrNo = getIntent.getBooleanExtra(STATE_EDIT_OR_NOT, true);
        }
        binding.setNoteItem(boxWithTasks);
    }

    private void onClickAddTaskToDb() {
        String nameTask = edTxtNameTask.getText().toString();
        String mainTextTask = edTxtMainTextTask.getText().toString();
        if (nameTask.isEmpty() && mainTextTask.isEmpty()) {
            Toast.makeText(WriteNewTaskActivity.this, R.string.nothing_to_write, Toast.LENGTH_LONG).show();
        } else {
            if (editTaskOrNo) {
                AppExecuted.getInstance().getSecondIO().execute(() -> myDbManager.writeToDb(nameTask, mainTextTask));
                Toast.makeText(WriteNewTaskActivity.this, R.string.task_created, Toast.LENGTH_LONG).show();
            } else {
                myDbManager.updateItemInList(nameTask, mainTextTask, boxWithTasks.getId());
                Toast.makeText(WriteNewTaskActivity.this, R.string.task_created, Toast.LENGTH_LONG).show();
            }
            myDbManager.closeDb();
            finish();
        }
    }
}