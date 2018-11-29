package com.seg2105app.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.ListIterator;

import static android.content.ContentValues.TAG;

public class ServiceList extends ArrayList<ServiceList.ServiceElement> {

    private static ServiceList instance = null;

    protected class ServiceElement{
        private String key;
        private Service service;

        ServiceElement(String key, Service service){
            this.key = key;
            this.service = service;
        }
    }

    //when first created, add a dummy Service that doubles as a button to go to the edit tab
    private ServiceList() {
        //This Exists to defeat instantiation
        super();
    }

    //This will be called to check if a list already exists
    public static ServiceList getInstance() {
        if (instance == null) {
            instance = new ServiceList();
        }
        return instance;
    }

    public ArrayList<Service> getServiceList()
    {
        ArrayList<Service> list = new ArrayList<>();
        for(ListIterator<ServiceElement> iter = instance.listIterator(); iter.hasNext();){
            list.add(iter.next().service);
        }

        return list;
    }

    public void populateServiceList(DatabaseHandler sdbHandler) {
        instance.clear();

        sdbHandler.getReferenceToServiceTable().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Service service = snapshot.getValue(Service.class);

                    instance.add(new ServiceList.ServiceElement(snapshot.getKey(), service));

                    if (service == null){
                        Log.d(TAG, "service is null :" + key);
                    }else {
                        Log.d(TAG, "service is added to list : " + key);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Service getServiceAt(int index){
        return this.get(index).service;
    }
}
