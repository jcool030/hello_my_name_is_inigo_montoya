package com.seg2105app.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.database.DatabaseHandler;

import static android.support.constraint.Constraints.TAG;

public class CurrentUser{
    private static User currentUser;
    private static String currentKey;

    private static boolean logInSuccessful = false;

    public static User getCurrentUser(){
        if (currentUser == null){
            return null;
        }else{
            return currentUser;
        }
    }

    public static String getCurrentKey(){
        if (currentKey == null){
            return null;
        }else{
            return currentKey;
        }
    }

    public static boolean isLogInSuccessful() {
        return logInSuccessful;
    }

    public static void requestLogIn(String username, String password){

    }

    public boolean logIn(Context context){
        if (isLogInSuccessful()){
            currentUser.logIn(context, currentKey);
            return true;
        }else{
            return false;
        }

    }

    public static void setCurrentLogIn(User user, String key){
        Log.d(TAG, "User : " + user.toString() + ", Key : " + key);
        if (currentUser == null) {
            currentUser = user;
            currentKey = key;
            logInSuccessful = true;
        }
    }

    private static void logOut(){
        currentUser = null;
        currentKey = null;
    }
}
