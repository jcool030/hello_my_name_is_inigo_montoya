package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.seg2105app.delieverable1.database.DatabaseHandler;

//A copy of the homeowner welcome screen class
public class AdminWelcome extends AppCompatActivity {

    Button signoutButton, createService;
    ListView userlv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        signoutButton = findViewById(R.id.logOutButton);

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + user + "! You are logged in as: Admin.");

        DatabaseHandler udb = new DatabaseHandler(this);

        Query query = udb.getReferenceToUserTable();

    }//end of onCreate

    //This code is for after a service is modified by serviceEditorActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED);
        else
            {
            ListView listView = findViewById(R.id.userList);
//if it doesnt already show the changes, they need to be shown here
            listView.refreshDrawableState();
            }
    }
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


