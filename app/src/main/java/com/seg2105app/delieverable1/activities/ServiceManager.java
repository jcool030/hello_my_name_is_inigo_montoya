package com.seg2105app.delieverable1.activities;

import com.seg2105app.delieverable1.users.Service;
import java.util.ArrayList;

//class that holds the arraylist
public class ServiceManager {

    private static ServiceManager instance = null;
    private ArrayList<Service> serviceList;

    //when first created, add a dummy Service that doubles as a button to go to the edit tab
    protected ServiceManager() {
        //This Exists to defeat instantiation

        serviceList = new ArrayList<>(); //With the new version of the manageServiceActivity, null
        //entries are ignored. Eventually we chould merge these classes into one file
    }

    //This will be called to check if a list already exists
    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
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

    public void add(Service service){serviceList.add(service);}

    public void clear()
    {
        ArrayList<Service> tmpList = new ArrayList<>();
        this.serviceList = tmpList;
    }
}
