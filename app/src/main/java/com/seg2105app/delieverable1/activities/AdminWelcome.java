package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//A copy of the homeowner welcome screen class
public class AdminWelcome extends AppCompatActivity implements View.OnClickListener {

    Button signoutButton, createService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        signoutButton = findViewById(R.id.logOutButton);
        createService = findViewById(R.id.newService);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + user + " you are logged in as Admin.");

        createService.setOnClickListener(this);
        signoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
    }
    public void logoutClick(View v) {
//        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
//        startActivity(signoutIntent);
    }
    public void createServiceClick (View v){

    }
    public void manageServiceClick (View v){

    }

}

