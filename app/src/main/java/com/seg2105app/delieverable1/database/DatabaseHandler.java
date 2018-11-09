package com.seg2105app.delieverable1.database;


import android.content.Context;
import android.provider.BaseColumns;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.seg2105app.delieverable1.users.Service;
import com.seg2105app.delieverable1.users.User;
import com.seg2105app.delieverable1.users.UserFactory;
import com.seg2105app.delieverable1.users.UserList;


import static android.content.ContentValues.TAG;

public final class DatabaseHandler{

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;

    /////////////NESTED CLASSES//////////////////////////////////////////
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_USERS = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static class UserInfoEntry implements BaseColumns {
        private static final String COLUMN_FIRST_NAME = "firstname";
        private static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_USER_TYPE = "type";
    }

    private static class ServiceEntry implements BaseColumns {
        public static final String TABLE_SERVICES = "services";
        public static final String COLUMN_SERVICE_NAME = "name";
        public static final String COLUMN_SERVICE_RATE = "rate";
    }

    ////////DEFAULT CONSTRUCTOR///////////////////////////////////
    public DatabaseHandler(Context context){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
    }

    //////METHODS//////////////////////////////////////////////////
    public void createUserWithUsernameAndPassword(User user, String userID){
        String uid = mRef.push().getKey();
        firebaseDatabase.getReference(UserEntry.TABLE_USERS).child(uid).setValue(user);
    }

    public void createService(Service service){
        String uid = mRef.push().getKey();
        firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).child(uid).setValue(service);
    }

    public Query getReferenceToUserTable(){
        return firebaseDatabase.getReference(UserEntry.TABLE_USERS).orderByChild(UserEntry.COLUMN_USERNAME);
    }

    public Query getReferenceToServiceTable (){
        return firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).orderByChild(ServiceEntry.COLUMN_SERVICE_NAME);
    }
    public Query getReferenceToServiceTable(String orderPath){
        return firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).orderByChild(orderPath);
    }

    public boolean valuePresentInDatabase(DataSnapshot snapshot, String value, String columnKey){
        boolean valueIsPresent = false;
        for (DataSnapshot ds: snapshot.getChildren()) {
            String retrievedValue = ds.child(columnKey).getValue(String.class);
            Log.d(TAG, "Testing username against usernameRef: " + retrievedValue + ", " + value + ", " + retrievedValue.equalsIgnoreCase(value));


            if (retrievedValue.equalsIgnoreCase(value)) {
            valueIsPresent = true;
            }
        }

        return valueIsPresent;
    }

    public User validateUsernameAndPassword(DataSnapshot snapshot, String username, String password){
        for (DataSnapshot ds: snapshot.getChildren()) {
            String retrievedUsername = ds.child(UserEntry.COLUMN_USERNAME).getValue(String.class);
            String retrievedPassword = ds.child(UserEntry.COLUMN_PASSWORD).getValue(String.class);
            try {
                Log.d(TAG, "Testing username match: " + retrievedUsername + ", " + username + ", " + retrievedUsername.equalsIgnoreCase(username));
                Log.d(TAG, "Testing password match: " + retrievedUsername + ", " + username + ", " + retrievedUsername.equals(username));

            }catch (Exception e){

            }


            if (retrievedUsername.equalsIgnoreCase(username) && retrievedPassword.equals(password)) {
                String mUsername = ds.child(UserEntry.COLUMN_USERNAME).getValue(String.class);
                String mPassword = ds.child(UserEntry.COLUMN_PASSWORD).getValue(String.class);
                String mFirstName = ds.child(UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
                String mLastName = ds.child(UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
                String mType = ds.child(UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);
                UserFactory userFactory = new UserFactory();

                User user = userFactory.getUser(mUsername,mPassword,mFirstName,mLastName,mType);;

                return user;
            }
        }

        return null;
    }

    public void produceListOfElementsFromSnapshot(DataSnapshot snapshot){
        UserList userList = UserList.getInstance();

        for (DataSnapshot ds: snapshot.getChildren()){
            String mUsername = ds.child(UserEntry.COLUMN_USERNAME).getValue(String.class);
            String mPassword = ds.child(UserEntry.COLUMN_PASSWORD).getValue(String.class);
            String mFirstName = ds.child(UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
            String mLastName = ds.child(UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
            String mType = ds.child(UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);
            UserFactory userFactory = new UserFactory();

            User user = userFactory.getUser(mUsername,mPassword,mFirstName,mLastName,mType);

            if (!userList.contains(user)){
                userList.add(user);
            }
        }
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
