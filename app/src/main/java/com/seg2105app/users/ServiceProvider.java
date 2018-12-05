package com.seg2105app.users;

import android.content.Context;
import android.content.Intent;
import com.seg2105app.activities.serviceProviderRating;
import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.activities.serviceprovider.ServiceProviderWelcome;

import java.util.ArrayList;

public class ServiceProvider extends User {
    private String phoneNumber;
    private String address;
    private String companyName;
    private boolean licensed;
    private String description;
    //for availabilities
    private String[][] availabilities;
    //for rating
    //private double rating;
    //private int numOfRatings;
    private ArrayList<String> comments;
    serviceProviderRating associatedRating;

    public ServiceProvider(String username, String password, String firstName, String lastName, String type){
        super(username, password, firstName, lastName, type);
        availabilities = new String[7][2];
        //rating = 0;
        //numOfRatings = 0;
        comments = null;
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
        Intent intent = new Intent(context, OpeningScreenActivity.class);
        context.startActivity(intent);
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public double getRating()
    {
        return associatedRating.getRating();
    }

    public void addRating(double newAddition)
    {
        associatedRating.addAndCalculateRating(newAddition);
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setLicensed(boolean licensed){
        this.licensed = licensed;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isLicensed() {
        return licensed;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getComments(){ return comments; }

    public void addComment(String newComment){
        comments.add(newComment);
    }

    public void setAvailabilities(int day, int startOrEnd, String time){
        availabilities[day][startOrEnd] = time;
    }

    public String getAvailability(int day, int startOrEnd){
        return availabilities[day][startOrEnd];
    }
}
