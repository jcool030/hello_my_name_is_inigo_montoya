package com.seg2105app.delieverable1.activities;

import com.seg2105app.delieverable1.users.Service;
import java.util.ArrayList;

//class that holds the arraylist
public class serviceManager {

    private static serviceManager instance = null;
    private ArrayList<Service> serviceList;

    //when first created, add a dummy Service that doubles as a button to go to the edit tab
    protected serviceManager() {
        //This Exists to defeat instantiation

        serviceList = new ArrayList<Service>();
        Service newService = new Service("Default Service (Click here to edit)", 0.0);
        serviceList.add(newService);
    }

    //This will be called to check if a list already exists
    public static serviceManager getInstance() {
        if (instance == null) {
            instance = new serviceManager();
        }
        return instance;
    }

    public ArrayList<Service> getServiceList()
    {
        return serviceList;
    }

    public Service getServiceAt(int index)
    {
        return serviceList.get(index);
    }
}
