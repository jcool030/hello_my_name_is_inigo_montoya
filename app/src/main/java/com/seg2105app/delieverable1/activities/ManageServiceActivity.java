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
    private ServiceArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);
        sdbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.serviceList);
        manager = ServiceManager.getInstance();//creates instance of serviceManager if not already exists
        manager.clear(); //prevents duplicate entries from multiple instances of this class affecting a static list

        sdbHandler.getReferenceToServiceTable().addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 String name = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class);
                 Double rate = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_RATE).getValue(Double.class);

                 //dataSnapshot.getRef().removeValue();// REMOVE THE COMMENT TO PURGE THE CURRENT DATABASE
                 if(name != null && rate != null)
                 {
                     Service newService = new Service(name, rate);
                     manager.add(newService);
                 }//potentially add "else; dataSnapshot.getRef().removeValue();" to remove problem values
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class);
                Double rate = dataSnapshot.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_RATE).getValue(Double.class);

                if(name != null && rate != null)
                {
                    Service newService = new Service(name, rate);
                    manager.add(newService);
                }
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
