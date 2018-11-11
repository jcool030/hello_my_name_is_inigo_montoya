package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.users.Service;

public class ManageServiceActivity extends AppCompatActivity {
    private ListView listView;
    private ServiceManager manager;
    private DatabaseHandler sdbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);
        sdbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.serviceList);
        manager = ServiceManager.getInstance();//creates instance of serviceManager if not already exists

        sdbHandler.getReferenceToServiceTable().addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class);
                Double rate = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_RATE).getValue(Double.class);
                manager.add(new Service(name, rate));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class);
                Double rate = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_RATE).getValue(Double.class);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       ServiceArrayAdapter adapter = new ServiceArrayAdapter(this, manager.getServiceList());
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
