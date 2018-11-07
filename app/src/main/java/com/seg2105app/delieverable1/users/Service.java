package com.seg2105app.delieverable1.users;

public class Service {

    private String name;
    private double rate;

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
