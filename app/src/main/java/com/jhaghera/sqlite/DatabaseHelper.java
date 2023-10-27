package com.jhaghera.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    // Define the table and its columns
    private static final String TABLE_NAME = "MyTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LAST_NAME = "COLUMN_LAST_NAME";
    private static final String COLUMN_FIRST_NAME = "COLUMN_FIRST_NAME";

    // Create the table SQL statement
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database if needed
    }

    // CRUD operations

    // Insert a new record
    public long insertRecord(String f_name, String l_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, f_name);
        values.put(COLUMN_LAST_NAME, l_name);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public List<DataModel> getAllRecords() {
        List<DataModel> recordList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                DataModel record = new DataModel();
                record.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                record.setF_name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                record.setL_name(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));

                recordList.add(record);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return recordList;
    }

    public void deleteRecord(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = "id = ?"; // Define the condition for deletion
            String[] whereArgs = {String.valueOf(id)}; // The value to replace "?" in the where clause

            // Perform the deletion
            db.delete(TABLE_NAME, whereClause, whereArgs);

            // Close the database
            db.close();
    }

    public DataModel getRecordById(int recordId) {
        DataModel record = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME};
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(recordId)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            record = new DataModel();
            record.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            record.setF_name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
            record.setL_name(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
        }

        cursor.close();
        db.close();

        return record;
    }

    public void updateRecord(int recordId, String updatedName, String upadteL_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Set the updated values
        values.put(COLUMN_FIRST_NAME, updatedName);
        values.put(COLUMN_LAST_NAME, upadteL_name);

        // Define the WHERE clause to update the specific record
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(recordId)};

        // Execute the update
        db.update(TABLE_NAME, values, whereClause, whereArgs);

        // Close the database
        db.close();
    }

}
