package com.seg2105app.users;

import android.content.Context;
import android.content.Intent;

import com.seg2105app.activities.admin.AdminWelcome;
import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.database.DatabaseHandler;

public class Administrator extends User{

    public Administrator(String username, String password, String firstName, String lastName, String type){
        super(username, password, firstName, lastName, type);
    }

    @Override
    public void logIn(Context context, String key) {
        Intent intent = new Intent(context, AdminWelcome.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    public void logOut(Context context){
        Intent intent = new Intent(context, OpeningScreenActivity.class);
        context.startActivity(intent);
    }

    public void createService(){

    }

    public void modifyService(){

    }

    public void deleteService(){

    }
}
