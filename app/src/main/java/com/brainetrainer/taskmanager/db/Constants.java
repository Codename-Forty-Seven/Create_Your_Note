package com.brainetrainer.taskmanager.db;

public class Constants {
    public static final String BOX_WITH_ITEM_INTENT = "box_with_item_intent";
    public static final String STATE_EDIT_OR_NOT = "edit_state_or_not";
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_idINTEGER";
    public static final String NAME_TASK = "main_title_task";
    public static final String TEXT_TASK = "text_in_task";
    public static final String DB_NAME = "db_test_notes.db";
    public static final int DB_VERSION = 2;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_TASK + " TEXT," + TEXT_TASK  + " TEXT)";

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


}
