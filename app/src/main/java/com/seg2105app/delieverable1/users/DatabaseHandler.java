package com.seg2105app.delieverable1.users;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import android.provider.BaseColumns;
import android.support.annotation.RequiresApi;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.delieverable1.activities.OpeningScreenActivity;
import com.seg2105app.delieverable1.users.*;

import java.util.List;

public final class DatabaseHandler{

    /////////////NESTED CLASSES//////////////////////////////////////////
    private static class UserEntry implements BaseColumns {
        private static final String TABLE_USERS = "users";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_PASSWORD = "password";
    }

    private static class UserInfoEntry implements BaseColumns {
        private static final String TABLE_NAME = "userInfo";
        private static final String COLUMN_FIRST_NAME = "firstname";
        private static final String COLUMN_LAST_NAME = "lastname";
        private static final String COLUMN_USER_TYPE = "type";
    }

    public class UserDatabaseHelper extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "userInfoDB.db";

        public UserDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_USERS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(DELETE_USERS_TABLE);
            onCreate(db);
        }

    }

    ///////QUERY STRINGS//////////////////////////////
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + UserEntry.TABLE_USERS + "("
            + UserEntry._ID + " INTEGER PRIMARY KEY," + UserEntry.COLUMN_USERNAME+" TEXT"+")";

    private static final String CREATE_USER_INFO_TABLE = "CREATE TABLE " + UserInfoEntry.TABLE_NAME + "("
            + UserInfoEntry._ID + " INTEGER PRIMARY KEY," + UserInfoEntry.COLUMN_FIRST_NAME + " TEXT,"
            + UserInfoEntry.COLUMN_LAST_NAME + "TEXT,"+ UserInfoEntry.COLUMN_USER_TYPE+ "TEXT"+")";

    private static final String DELETE_USERS_TABLE = "DROP TABLE IF EXISTS " + UserEntry.TABLE_USERS;

    ////////DEFAULT CONSTRUCTOR///////////////////////////////////
    public DatabaseHandler(Context context){
        FirebaseApp.initializeApp(context);
    }

    //////METHODS//////////////////////////////////////////////////
    public void createUserWithUsernameAndPassword(Context context, User user){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_USERNAME, user.getUsername());
        values.put(UserEntry.COLUMN_PASSWORD, user.getPassword());
//        values.put(UserEntry.COLUMN_FIRST_NAME, user.getFirstName());
//        values.put(UserEntry.COLUMN_LAST_NAME, user.getLastName());
//        values.put(UserEntry.COLUMN_USER_TYPE, user.getType());getType

        mDatabase.getReference(UserEntry.TABLE_USERS).child(UserEntry.COLUMN_USERNAME).setValue(user.getUsername());
        mDatabase.getReference(UserEntry.TABLE_USERS).child(UserEntry.COLUMN_PASSWORD).setValue(user.getPassword());
        mDatabase.getReference(UserInfoEntry.TABLE_NAME).child(UserInfoEntry.COLUMN_FIRST_NAME).setValue(user.getUsername());
        mDatabase.getReference(UserInfoEntry.TABLE_NAME).child(UserInfoEntry.COLUMN_LAST_NAME).setValue(user.getUsername());
        mDatabase.getReference(UserInfoEntry.TABLE_NAME).child(UserInfoEntry.COLUMN_USER_TYPE).setValue(user.getUsername());

    }

//    public void readUserFromDatabase(Context context, String username){
//        UserDatabaseHelper udbHelper = new UserDatabaseHelper(context);
//        SQLiteDatabase db = udbHelper.getReadableDatabase();
//        UserList userList = UserList.getInstance();
//
//        String[] projection = {
//                BaseColumns._ID,
//                UserEntry.COLUMN_USERNAME,
//                UserEntry.COLUMN_PASSWORD,
//                UserEntry.COLUMN_FIRST_NAME,
//                UserEntry.COLUMN_LAST_NAME,
//                UserEntry.COLUMN_USER_TYPE
//        };
//
//        String selection = UserEntry.COLUMN_USERNAME + " = ?";
//        String[] selectionArgs = {username};
//
//        String sortOrder = UserEntry.COLUMN_USERNAME + " DESC";
//
//        Cursor cursor = db.query(UserEntry.TABLE_USERS, projection, selection, selectionArgs, null, null, sortOrder);
//
//    }







}
