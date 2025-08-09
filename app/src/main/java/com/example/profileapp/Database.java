package com.example.profileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static String db_name = "student_db";
    public static String table = "users";
    public static String id = "id";
    public static String name = "name";
    public static String age = "age";
    public static String index_number = "index_number";
    public static String programme = "programme";
    public static String dob = "dob"; // date of birth
    public static int db_version = 1;

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + index_number + " TEXT UNIQUE, "
                + name + " VARCHAR(255), "
                + age + " INTEGER, "
                + programme + " VARCHAR(255), "
                + dob + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    public void insert_data(String name, String age, String index_number, String programme, String dob) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.name, name);
        values.put(this.age, age);
        values.put(this.index_number, index_number);
        values.put(this.programme, programme);
        values.put(this.dob, dob);
        db.insert(table, null, values);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + table, null);
    }

    public void updateUser(String index_number, String name, String age, String programme, String dob) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.name, name);
        values.put(this.age, age);
        values.put(this.programme, programme);
        values.put(this.dob, dob);
        db.update(table, values, "index_number = ?", new String[]{index_number});
    }

    public void deleteUser(String index_number) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, "index_number = ?", new String[]{index_number});
    }
}
