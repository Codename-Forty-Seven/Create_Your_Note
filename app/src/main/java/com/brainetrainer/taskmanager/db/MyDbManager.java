package com.brainetrainer.taskmanager.db;

import static com.brainetrainer.taskmanager.db.Constants.NAME_TASK;
import static com.brainetrainer.taskmanager.db.Constants.TABLE_NAME;
import static com.brainetrainer.taskmanager.db.Constants.TEXT_TASK;
import static com.brainetrainer.taskmanager.db.Constants._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brainetrainer.taskmanager.adapters.BoxWithTasks;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private final MyDbHelper myDbHelper;
    private SQLiteDatabase sqLiteDatabase;


    public MyDbManager(Context context) {
        myDbHelper = new MyDbHelper(context);
    }

    public void openDb() {
        sqLiteDatabase = myDbHelper.getWritableDatabase();
    }

    public void writeToDb(String nameTask, String textTask) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_TASK, nameTask);
        contentValues.put(TEXT_TASK, textTask);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void readFromDb(String nameTask, OnDataReceived onDataReceived) {
        List<BoxWithTasks> boxWithTasks = new ArrayList<>();
        String searchNameTask = NAME_TASK + " LIKE ?";
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, searchNameTask, new String[]{"%" + nameTask + "%"}, null,
                null, null, null);
        while (cursor.moveToNext()) {
            BoxWithTasks boxWithTasks1 = new BoxWithTasks();
            boxWithTasks1.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
            boxWithTasks1.setNameTask(cursor.getString(cursor.getColumnIndexOrThrow(NAME_TASK)));
            boxWithTasks1.setMainTextTask(cursor.getString(cursor.getColumnIndexOrThrow(TEXT_TASK)));
            boxWithTasks.add(boxWithTasks1);
        }
        cursor.close();
        onDataReceived.onReceived(boxWithTasks);
    }

    public void updateItemInList(String nameTask, String textTask, int idWhatDelete) {
        String selectFromDb = _ID + "=" + idWhatDelete;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_TASK, nameTask);
        contentValues.put(TEXT_TASK, textTask);
        sqLiteDatabase.update(TABLE_NAME, contentValues, selectFromDb, null);
    }

    public void deleteFromDb(int idWhatDelete) {
        String selectFromDb = _ID + "=" + idWhatDelete;
        sqLiteDatabase.delete(TABLE_NAME, selectFromDb, null);
    }

    public void closeDb() {
        myDbHelper.close();
    }
}
