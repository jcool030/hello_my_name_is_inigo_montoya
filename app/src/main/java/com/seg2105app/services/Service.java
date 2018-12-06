package com.seg2105app.services;
import android.os.Parcelable;

import com.seg2105app.users.Rating;
import com.seg2105app.users.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private String name = "name not yet defined";
    private double rate = 0.0;
    private ArrayList<ServiceListing> listings;
    private List<Service> subservices;
    private ArrayList<String> comments;
    private Rating associatedRating;

    public Service(){
        name = "name not yet defined";
        rate = 0.0;
        associatedRating = new Rating(this);
        comments = new ArrayList<String>();
    }

    public Service(String serviceName, double hourlyRate){
        this.name = serviceName;
        this.rate = hourlyRate;
        associatedRating = new Rating(this);
        comments = new ArrayList<String>();
    }

    //Getters
    public String getName(){
        return this.name;
    }

    public double getRate(){
        return this.rate;
    }
    //Setters
    public void setName(String newName){
        this.name = newName;
    }

    public void setRate(double newRate){
        this.rate = newRate;
    }

    public double getRating()
    {
        return associatedRating.getRating();
    }

    public void addRating(double newAddition)
    {
        associatedRating.addAndCalculateRating(newAddition);
    }

    public ArrayList<String> getComments(){ return comments; }

    public void addComment(String newComment){
        comments.add(newComment);
    }

    public boolean equals(Service service){
        if (service.name.equals(this.name)){
            return true;
        }
        return false;
    }

    public void addListing(ServiceProvider provider){
        listings.add(new ServiceListing(this, provider));
    }

    public ArrayList<ServiceListing> getListings(){
        return listings;
    }

    public boolean hasListing(ServiceProvider provider){
        for (ServiceListing listing : listings){
            if (listing.getService().equals(provider)){
                return true;
            }
        }
        return false;
    }
}
