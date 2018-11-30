package com.seg2105app.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.R;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.ServiceList;

import java.util.List;

public class ManageServiceActivity extends AppCompatActivity {
    private ListView listView;
    private ServiceList manager;
    private DatabaseHandler sdbHandler;
    private ServiceArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);
        sdbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.serviceList);

        manager = ServiceList.getInstance();//creates instance of serviceManager if not already exists
        manager.populateServiceList(sdbHandler);

        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
            Intent launchEditorIntent = new Intent(getApplicationContext(), ServiceEditorActivity.class);
            launchEditorIntent.putExtra("selectedService", position);
            startActivity(launchEditorIntent);
            finish();
            }
        });

    }
    public void refreshClick(View v){
        listView = findViewById(R.id.serviceList);
        adapter = new ServiceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                Intent launchEditorIntent = new Intent(getApplicationContext(), ServiceEditorActivity.class);
                launchEditorIntent.putExtra("selectedService", position);
                startActivity(launchEditorIntent);
                finish();
            }
        });
    }
}
