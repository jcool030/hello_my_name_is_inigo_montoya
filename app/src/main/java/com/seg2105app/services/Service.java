package com.seg2105app.services;
import java.util.List;

public class Service {

    private String name = "name not yet defined";
    private double rate = 0.0;
    private List<Service> subservices;

    public Service(){
        name = "name not yet defined";
        rate = 0.0;
    }

    public Service(String serviceName, double hourlyRate){
        this.name = serviceName;
        this.rate = hourlyRate;
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
}
