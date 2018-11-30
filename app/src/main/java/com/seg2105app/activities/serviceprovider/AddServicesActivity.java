package com.seg2105app.activities.serviceprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.R;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceList;

import java.util.ArrayList;
import java.util.List;

public class AddServicesActivity extends AppCompatActivity {
    private ListView listViewCurrent, listViewAvail;
    private ServiceList manager;
    private DatabaseHandler sdbHandler;
    private ServiceArrayAdapter adapter, adapter2;
    private ArrayList<Service> currentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_manage_service);
        sdbHandler = new DatabaseHandler(this);

        listViewAvail = findViewById(R.id.listViewAvail);
        listViewCurrent = findViewById(R.id.listViewCurrent);

        manager = ServiceList.getInstance();//creates instance of serviceManager if not already exists
        manager.populateServiceList(sdbHandler);

        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listViewAvail.setAdapter(adapter);

        listViewAvail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                if (currentList.contains(manager.getServiceList().get(position))){
                    Toast duplicate = Toast.makeText(AddServicesActivity.this, "Service Already Added", Toast.LENGTH_SHORT);
                    duplicate.show();
                }else {
                    currentList.add(manager.getServiceList().get(position));
                }

            }
        });

        currentList = new ArrayList<>();

        adapter2 = new ServiceArrayAdapter(this, currentList);
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
                if (currentList.contains(manager.getServiceList().get(position))){
                    Toast duplicate = Toast.makeText(AddServicesActivity.this, "Service Already Added", Toast.LENGTH_SHORT);
                    duplicate.show();

                }else {
                    currentList.add(manager.getServiceList().get(position));
                }
            }
        });
    }
    public void refreshClick2(View v) {
        listViewCurrent = findViewById(R.id.listViewCurrent);
        adapter2 = new ServiceArrayAdapter(this, currentList);
        listViewCurrent.setAdapter(adapter2);

        listViewCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                currentList.remove(position);
            }
        });

    }


}
