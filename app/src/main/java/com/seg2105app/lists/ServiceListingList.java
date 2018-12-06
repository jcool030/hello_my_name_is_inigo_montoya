package com.seg2105app.lists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.Callback;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceListing;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.ServiceProvider;

import java.util.ArrayList;
import java.util.ListIterator;

import static android.content.ContentValues.TAG;

public class ServiceListingList extends ArrayList<ServiceListingList.ServiceListingElement> {

    private static ServiceListingList instance = null;
    private Context context;

    public class ServiceListingElement{
        private String key;
        private String providerKey;
        private String serviceKey;
        private ServiceListing listing;


        ServiceListingElement(String key, String providerKey, String serviceKey){
            this.key = key;
            this.providerKey = providerKey;
            this.serviceKey = serviceKey;
            this.listing = new ServiceListing();
            this.setListing();
        }

        public String getKey(){
            return key;
        }

        public void setListing(){
            DatabaseHandler db = new DatabaseHandler(context);
            db.getService(serviceKey, new Callback<Service>() {
                @Override
                public void callback(Service data) {
                    listing.setService(data);
                }
            });
            listing.setProvider((ServiceProvider)CurrentUser.getCurrentUser());
        }

        public ServiceListing getServiceListing(){


            return listing;
        }
    }

    //when first created, add a dummy Service that doubles as a button to go to the edit tab
    private ServiceListingList() {
        //This Exists to defeat instantiation
        super();
    }

    private ServiceListingList(Context context) {
        //This Exists to defeat instantiation
        super();
        this.context = context;
    }

    //This will be called to check if a list already exists
    public static ServiceListingList getInstance() {
        if (instance == null) {
            instance = new ServiceListingList();
        }
        return instance;
    }

    public static ServiceListingList getInstance(Context context) {
        if (instance == null) {
            instance = new ServiceListingList(context);
        }
        if (instance.context != null){
            instance.populateServiceListingList(new DatabaseHandler(instance.context));
        }
        return instance;
    }

    public ArrayList<ServiceListing> getServiceListingList()
    {
        ArrayList<ServiceListing> list = new ArrayList<>();
        for(ListIterator<ServiceListingList.ServiceListingElement> iter = instance.listIterator(); iter.hasNext();){
            list.add(iter.next().listing);
        }

        return list;
    }

    public void populateServiceListingList(DatabaseHandler db) {
        instance.clear();

        db.getReferenceToServiceListingTable().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    instance.add(new ServiceListingElement(ds.getKey(), ds.child("service").getValue(String.class), ds.child("serviceProvider").getValue(String.class)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<ServiceListing> getServiceListingsOfProvider(String providerName){
        ArrayList<ServiceListing> listings = new ArrayList<>();
        for (ServiceListingElement listing : instance){
            String providerUsername = listing.getServiceListing().getServiceProvider().getUsername();
            if (providerUsername.equals(providerName)){
                listings.add(listing.getServiceListing());
                Log.d(TAG, "Adding Listing to " + providerName);
            }
        }
        return listings;
    }

//
//        sdbHandler.getReferenceToServiceListingTable().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String key = snapshot.getKey();
//                    String serviceKey = snapshot.child(DatabaseHandler.ServiceListingEntry.COLUMN_SERVICE).getValue(String.class);
//                    String providerKey = snapshot.child(DatabaseHandler.ServiceListingEntry.COLUMN_SERVICE).getValue(String.class);
//
//
//
//
//                    instance.add(new ServiceListingElement(snapshot.getKey()));
//
//                    if (listing == null){
//                        Log.d(TAG, "service is null :" + key);
//                    }else {
//                        Log.d(TAG, "service is added to list : " + key);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}

