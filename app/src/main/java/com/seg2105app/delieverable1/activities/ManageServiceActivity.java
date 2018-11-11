package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ManageServiceActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);


        listView = findViewById(R.id.serviceList);
        serviceManager manager = serviceManager.getInstance();//creates instance of serviceManager if not already exists

        serviceArrayAdapter adapter = new serviceArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
            Intent launchEditorIntent = new Intent(getApplicationContext(), serviceEditorActivity.class);
            launchEditorIntent.putExtra("selectedService", position);
            startActivityForResult(launchEditorIntent, 0);//POSITION IS THE PROBLEM
            }
        });

    }


}
