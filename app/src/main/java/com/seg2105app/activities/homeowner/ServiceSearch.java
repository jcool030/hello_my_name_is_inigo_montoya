package com.seg2105app.activities.homeowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.admin.ServiceEditorActivity;
import com.seg2105app.activities.serviceprovider.EditAvailabilitiesActivity;
import com.seg2105app.activities.R;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.ServiceList;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.ServiceProvider;
import com.seg2105app.services.Service;

import java.util.ArrayList;

public class ServiceSearch extends AppCompatActivity {

    DatabaseHandler sdbHandler;
    ListView listView;
    ServiceList manager;
    ServiceArrayAdapter adapter, adapter2;
    EditText serviceSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
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

    }
    public void searchClick(View v){
        ArrayList<Service> arrayList = new ArrayList<>();
        for (Service s : manager.getServiceList()){
            if (s.getName().equals(serviceSearch.toString().trim()) || s.getRate() == Double.parseDouble(serviceSearch.toString().trim())) {
                arrayList.add(s);
            }
        }
        adapter2 = new ServiceArrayAdapter(this, arrayList);
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, final View view, int position, long id){

            }
        });


    }


}
