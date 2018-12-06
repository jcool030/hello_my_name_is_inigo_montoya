package com.seg2105app.activities.serviceprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.R;
import com.seg2105app.activities.ServiceListingArrayAdapter;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.lists.ServiceList;
import com.seg2105app.services.ServiceListing;
import com.seg2105app.lists.ServiceListingList;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.ServiceProvider;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AddServicesActivity extends AppCompatActivity {
    ServiceProvider contractor = (ServiceProvider)CurrentUser.getCurrentUser();
    private ListView listViewCurrent, listViewAvail;
    private ServiceList manager;
    private ServiceListingList listingsManager;
    private DatabaseHandler sdbHandler;
    private ServiceArrayAdapter adapter;
    private ServiceListingArrayAdapter adapter2;
    private ArrayList<ServiceListing> currentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_manage_service);
        listingsManager = ServiceListingList.getInstance(this);



        currentList = listingsManager.getServiceListingsOfProvider(contractor.getUsername());
        sdbHandler = new DatabaseHandler(this);

        listViewAvail = findViewById(R.id.listViewAvail);
        listViewCurrent = findViewById(R.id.listViewCurrent);

        manager = ServiceList.getInstance(this);//creates instance of serviceManager if not already exists
        manager.populateServiceList(sdbHandler);

        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listViewAvail.setAdapter(adapter);

        listViewAvail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                ServiceList.ServiceElement service = manager.get(position);
                if (contractor.hasListing(service.getService())){
                    Toast duplicate = Toast.makeText(AddServicesActivity.this, "Service Listing Already Exists", Toast.LENGTH_SHORT);
                    duplicate.show();
                }else {

                    currentList.addAll(listingsManager.getServiceListingList());
                    contractor.addListing(service.getService());
                    sdbHandler.createListing(service.getKey(), CurrentUser.getCurrentKey());
                }
            }
        });

        currentList = new ArrayList<>();

        adapter2 = new ServiceListingArrayAdapter(this, currentList);
        listViewCurrent.setAdapter(adapter2);

        listViewCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                currentList.remove(position);
            }
        });



    }
    public void refreshClick(View v) {
        listViewAvail = findViewById(R.id.listViewAvail);
        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listViewAvail.setAdapter(adapter);

        listViewAvail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                ServiceList.ServiceElement service = manager.get(position);
                if (contractor.hasListing(service.getService())){
                    Toast duplicate = Toast.makeText(AddServicesActivity.this, "Service Listing Already Exists", Toast.LENGTH_SHORT);
                    duplicate.show();
                }else {
                    currentList.add(new ServiceListing(service.getService(), contractor));
                    contractor.addListing(service.getService());
                    sdbHandler.createListing(service.getKey(), CurrentUser.getCurrentKey());
                }
            }
        });
    }
    public void refreshClick2(View v) {
        listViewCurrent = findViewById(R.id.listViewCurrent);
        adapter2 = new ServiceListingArrayAdapter(this, currentList);
        listViewCurrent.setAdapter(adapter2);

        listViewCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                currentList.remove(position);
            }
        });

    }


}
