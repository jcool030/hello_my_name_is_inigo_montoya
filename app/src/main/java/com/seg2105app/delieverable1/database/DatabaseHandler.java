package com.seg2105app.delieverable1.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.delieverable1.activities.OpeningScreenActivity;
import com.seg2105app.delieverable1.users.*;

import java.util.List;

public class DatabaseHandler {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userInfoDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";

    DatabaseReference databaseUsers;


    private void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + "TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        databaseUsers  = FirebaseDatabase.getInstance().getReference("users");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    private void addUser(String userID, User user){
        String id = databaseUsers.push().getKey();
        databaseUsers.child(id).setValue(user);
    }

}
