package com.seg2105app.users;

import android.content.Context;
import android.content.Intent;
import com.seg2105app.activities.serviceProviderRating;
import com.seg2105app.activities.serviceprovider.ServiceProviderWelcome;

public class ServiceProvider extends User {
    private serviceProviderRating associatedRating;

    public ServiceProvider(String username, String password, String firstName, String lastName, String type){
        super(username, password, firstName, lastName, type);
        associatedRating = new serviceProviderRating(this);
    }

    @Override
    public void logIn(Context context, String key) {
        Intent intent = new Intent(context, ServiceProviderWelcome.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    public void logOut(Context context){

    }

    public double getRating()
    {
        return associatedRating.getRating();
    }

    public void addRating(double newAddition)
    {
        associatedRating.addAndCalculateRating(newAddition);
    }
}
