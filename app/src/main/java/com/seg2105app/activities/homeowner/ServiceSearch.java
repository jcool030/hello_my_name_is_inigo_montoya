package com.seg2105app.activities.homeowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.R;
import com.seg2105app.activities.admin.ServiceEditorActivity;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceList;

import java.util.ArrayList;

public class ServiceSearch extends AppCompatActivity {

    private DatabaseHandler sdbHandler;
    private ListView listView;
    private ServiceList manager;
    private ServiceArrayAdapter adapter;
    private EditText serviceSearch;
    private ArrayList<Service> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_services);
        sdbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.serviceList);
        serviceSearch = findViewById(R.id.search);

        manager = ServiceList.getInstance();//creates instance of serviceManager if not already exists
        manager.populateServiceList(sdbHandler);

        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, final View view, int position, long id){

            }
        });
        listView = findViewById(R.id.serviceList);
        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);

    }
    public void searchClick(View v){
        listView = findViewById(R.id.serviceList);
        for (Service s : manager.getServiceList()) {
            System.out.println(s.getName());
            if (s.getName().equals(serviceSearch.toString().trim()) || s.getRate() == Double.parseDouble(serviceSearch.toString().trim())) {
                arrayList.add(s);
            }
        }
        if (arrayList.size() == 0){
            Toast noResults = Toast.makeText(ServiceSearch.this, "No Results found", Toast.LENGTH_SHORT);
            noResults.show();
        }
        adapter = new ServiceArrayAdapter(this, arrayList);
        listView.setAdapter(adapter);



    }
    public void refreshClick(View v){
        listView = findViewById(R.id.serviceList);
        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);
    }


}
