package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.users.ServiceProvider;
import com.seg2105app.delieverable1.users.User;

//A copy of the homeowner welcome screen class
public class AdminWelcome extends AppCompatActivity {
    private ListView listView;//these 4 are part of user list code
    private UserManager manager;
    private DatabaseHandler sdbHandler;
    private UserArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        sdbHandler = new DatabaseHandler(this); //this is part of user list code

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + user + "! You are logged in as: Admin.");

        //////Start of user list code

        listView = findViewById(R.id.userList);
        manager = UserManager.getInstance();
        manager.clear();

        sdbHandler.getReferenceToUserTable().addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String username = dataSnapshot.child(DatabaseHandler.UserEntry.COLUMN_USERNAME).getValue(String.class);
                String password = dataSnapshot.child(DatabaseHandler.UserEntry.COLUMN_PASSWORD).getValue(String.class);
                String firstname = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
                String lastname = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
                String type = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);

                //dataSnapshot.getRef().removeValue();// REMOVE THE COMMENT TO PURGE THE CURRENT DATABASE
                if(username != null && password != null && firstname != null && lastname != null && type != null)
                {
                    User newUser = new User(username, password, firstname, lastname, type) {
                    };
                    manager.add(newUser);
                }
                //else{ dataSnapshot.getRef().removeValue();} to remove problem values (currently will delete all since noone has names)
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String username = dataSnapshot.child(DatabaseHandler.UserEntry.COLUMN_USERNAME).getValue(String.class);
                String password = dataSnapshot.child(DatabaseHandler.UserEntry.COLUMN_PASSWORD).getValue(String.class);
                String firstname = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
                String lastname = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
                String type = dataSnapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);

                if(username != null && password != null && firstname != null && lastname != null && type != null)
                {
                    User newUser = new User(username, password, firstname, lastname, type){
                    };
                    manager.add(newUser);
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

        adapter = new UserArrayAdapter(this, manager.getUserList());
        listView.setAdapter(adapter); //end of user list code

    }//end of onCreate

    public void createServiceClick(View v){
        Intent createServiceIntent = new Intent(this, CreateServiceActivity.class);
        startActivity(createServiceIntent);
    }

    public void manageServiceClick(View v){
        Intent manageServiceIntent = new Intent(this, ManageServiceActivity.class);
        startActivity(manageServiceIntent);
    }

    public void logoutClick(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
        finish();
    }

}


