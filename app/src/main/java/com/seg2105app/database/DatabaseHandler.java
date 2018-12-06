package com.seg2105app.database;


import android.content.Context;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.Callback;
import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceListing;
import com.seg2105app.users.ServiceProvider;
import com.seg2105app.users.User;


import java.util.ArrayList;

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
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_USER_TYPE = "type";
    }

    public static class ServiceEntry implements BaseColumns {
        public static final String TABLE_SERVICES = "services";
        public static final String COLUMN_SERVICE_NAME = "name";
        public static final String COLUMN_SERVICE_RATE = "rate";
    }

    public static class ServiceProviderEntry implements BaseColumns{
        public static final String COLUMN_PHONE_NUM = "phonenumber";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_COMPANY = "company";
        public static final String COLUMN_LICENSED = "licenced";
        public static final String COLUMN_DESCRIPTION = "description";
    }

    public static class ServiceListingEntry implements BaseColumns {
        public static final String TABLE_SERVICE_LISTINGS = "listings";
        public static final String COLUMN_PROVIDER = "serviceProvider";
        public static final String COLUMN_SERVICE = "service";
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

    public void updateServiceProvider(ServiceProvider user, String key){
        updateServiceProviderStringFields(user.getAddress(), key, ServiceProviderEntry.COLUMN_ADDRESS);
        updateServiceProviderStringFields(user.getCompanyName(), key, ServiceProviderEntry.COLUMN_COMPANY);
        updateServiceProviderStringFields(user.getDescription(), key, ServiceProviderEntry.COLUMN_DESCRIPTION);
        updateServiceProviderStringFields(user.getPhoneNumber(), key, ServiceProviderEntry.COLUMN_PHONE_NUM);
        updateServiceProviderBooleanFields(user.isLicensed(), key, ServiceProviderEntry.COLUMN_LICENSED);
    }

    private void updateServiceProviderStringFields(String field, String key, String fieldKey){
        firebaseDatabase.getReference(UserEntry.TABLE_USERS).child(key).child(fieldKey).setValue(field);
    }

    private void updateServiceProviderBooleanFields(boolean field, String key, String fieldKey){
        firebaseDatabase.getReference(UserEntry.TABLE_USERS).child(key).child(fieldKey).setValue(field);
    }


    public void createService(Service service){
        String uid = mRef.push().getKey();
        firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).child(uid).setValue(service);
    }

    public void updateService(Service service, String key){
        firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).child(key).setValue(service);
    }

    public void createListing(String serviceKey, String providerKey){
        String uid = mRef.push().getKey();
        firebaseDatabase.getReference(ServiceListingEntry.TABLE_SERVICE_LISTINGS).child(uid).child(ServiceListingEntry.COLUMN_SERVICE).setValue(serviceKey);
        firebaseDatabase.getReference(ServiceListingEntry.TABLE_SERVICE_LISTINGS).child(uid).child(ServiceListingEntry.COLUMN_PROVIDER).setValue(providerKey);
    }

    public void deleteListing(String key){
        firebaseDatabase.getReference(ServiceListingEntry.TABLE_SERVICE_LISTINGS).child(key).removeValue();
    }

    public void deleteService(String key){
        firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).child(key).removeValue();
    }

    public Query getReferenceToUserTable(){
        return firebaseDatabase.getReference(UserEntry.TABLE_USERS).orderByChild(UserEntry.COLUMN_USERNAME);
    }

    public Query getReferenceToServiceTable (){
        return firebaseDatabase.getReference(ServiceEntry.TABLE_SERVICES).orderByChild(ServiceEntry.COLUMN_SERVICE_NAME);
    }

    public Query getReferenceToServiceListingTable (){
        return firebaseDatabase.getReference(ServiceListingEntry.TABLE_SERVICE_LISTINGS);
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

    public DataSnapshot validateUsernameAndPassword(DataSnapshot snapshot, String username, String password){
        for (DataSnapshot ds: snapshot.getChildren()) {
            String retrievedUsername = ds.child(UserEntry.COLUMN_USERNAME).getValue(String.class);
            String retrievedPassword = ds.child(UserEntry.COLUMN_PASSWORD).getValue(String.class);
            try {
                Log.d(TAG, "Testing username match: " + retrievedUsername + ", " + username + ", " + retrievedUsername.equalsIgnoreCase(username));
                Log.d(TAG, "Testing password match: " + retrievedUsername + ", " + username + ", " + retrievedUsername.equals(username));

            }catch (Exception e){

            }

            if (retrievedUsername.equals(username) && retrievedPassword.equals(password)) {
                return ds;
            }
        }

        return null;
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
