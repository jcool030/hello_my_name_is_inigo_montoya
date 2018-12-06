package com.seg2105app.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.activities.UserArrayAdapter;

import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.activities.R;
import com.seg2105app.users.Administrator;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.lists.UserList;

//A copy of the homeowner welcome screen class
public class AdminWelcome extends AppCompatActivity {

    String userKey;
    Administrator admin = (Administrator)CurrentUser.getCurrentUser();

    private ListView listView;//these 4 are part of user list code
    private UserList userList = UserList.getInstance();
    private DatabaseHandler udbHandler;
    private UserArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        udbHandler = new DatabaseHandler(this); //this is part of user list code

        Bundle bundle = getIntent().getExtras();
        userKey = bundle.getString("key");
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + admin.getUsername() + "! You are logged in as: Admin.");


        listView = findViewById(R.id.userList);
        userList = UserList.getInstance();

        userList.populateUserList(udbHandler);
        adapter = new UserArrayAdapter(getApplicationContext(), userList.getUserList());
        listView.setAdapter(adapter);

        Button serviceRefreshButton = findViewById(R.id.refreshButton);
        serviceRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView = findViewById(R.id.userList);
                adapter = new UserArrayAdapter(getApplicationContext(), userList.getUserList());
                listView.setAdapter(adapter);
            }
        });
    }//end of on create

        public void logoutClick(View v) {
            Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
            CurrentUser.logOut();
            startActivity(signoutIntent);
            finish();
        }

    public void createServiceClick(View v){
        Intent createServiceIntent = new Intent(this, CreateServiceActivity.class);
        createServiceIntent.putExtra("key", userKey);
        startActivity(createServiceIntent);
    }

    public void manageServiceClick(View v){
        Intent manageServiceIntent = new Intent(this, ManageServiceActivity.class);
        manageServiceIntent.putExtra("key", userKey);
        startActivity(manageServiceIntent);
    }
}


