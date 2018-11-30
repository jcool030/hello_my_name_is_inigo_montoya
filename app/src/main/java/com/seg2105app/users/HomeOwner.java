package com.seg2105app.users;

import android.content.Context;
import android.content.Intent;

import com.seg2105app.activities.homeowner.HomeOwnerWelcome;

public class HomeOwner extends User{


    public HomeOwner(String username, String password, String firstName, String lastName, String type){
        super(username, password, firstName, lastName, type);
    }

    @Override
    public void logIn(Context context, String key) {
        Intent intent = new Intent(context, HomeOwnerWelcome.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    public void logOut(Context context){

    }

}
