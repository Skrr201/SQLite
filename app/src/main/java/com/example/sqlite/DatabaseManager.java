package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
       dbHelper = new DataBaseHelper(context);
       database = dbHelper.getWritableDatabase();
       return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert (String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.USER_NAME, username);
        contentValues.put(DataBaseHelper.USER_PASSWORD, password);
        database.insert(DataBaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String [] columns = new String[] {DataBaseHelper.USER_ID, DataBaseHelper.USER_NAME, DataBaseHelper.USER_PASSWORD};
        Cursor cursor = database.query(DataBaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor !=null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String username, String password) {
       ContentValues contentValues = new ContentValues();
       contentValues.put(DataBaseHelper.USER_NAME, username);
       contentValues.put(DataBaseHelper.USER_PASSWORD, password);
       int ret = database.update(DataBaseHelper.DATABASE_TABLE, contentValues, DataBaseHelper.USER_ID + "=" + _id, null);
       return ret;
    }

    public void delete (long _id) {
        database.delete(DataBaseHelper.DATABASE_TABLE, DataBaseHelper.USER_ID + "=" + _id, null);
    }
}
