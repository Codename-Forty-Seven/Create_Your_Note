package com.brainetrainer.taskmanager.db;

import static com.brainetrainer.taskmanager.db.Constants.DB_NAME;
import static com.brainetrainer.taskmanager.db.Constants.DB_VERSION;
import static com.brainetrainer.taskmanager.db.Constants.DELETE_TABLE;
import static com.brainetrainer.taskmanager.db.Constants.TABLE_STRUCTURE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}