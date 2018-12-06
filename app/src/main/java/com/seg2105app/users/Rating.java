package com.seg2105app.users;

import android.support.v7.app.AppCompatActivity;

import com.seg2105app.services.Service;
import com.seg2105app.users.ServiceProvider;

import java.util.ArrayList;

public class Rating {

    private double rating = 0.0; //default
    private ArrayList<Double> listOfRatings;
    private Service associatedService;

    public Rating(Service associatedService)
    {
        this.associatedService = associatedService;
        listOfRatings = new ArrayList<>();
        listOfRatings.add(0.0);//so that it doesn't divide by 0 when calculating size of array
    }

    public double getRating()
    {
        return this.rating;
    }


    public Service getService()
    {
        return this.associatedService;
    }

    //takes a new addition, adds it to the existing list, then calculates the new average among
    //all numbers in the list
    public void addAndCalculateRating(double newAddition)
    {
        listOfRatings.add(newAddition);

        //to calc
        double newRating = 0;
        for(int i=0; i< listOfRatings.size(); i++)
        {
            newRating = newRating + listOfRatings.get(i);
        }
        rating = newRating / listOfRatings.size();
        System.out.println("New rating of provider: "+rating); //just for minor testing
    }
}//end of class
