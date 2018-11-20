package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class ServiceProviderWelcome extends AppCompatActivity {

    Button signoutButton, updateBtn;
    TextView usernameText;
    EditText phoneNum, address, companyName;
    TextInputEditText textInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_welcome);

        signoutButton = findViewById(R.id.signoutButton);
        updateBtn = findViewById(R.id.updateInfoBtn);

        usernameText = findViewById(R.id.usernameText);
        phoneNum = findViewById(R.id.phoneNum);
        address = findViewById(R.id.address);
        companyName = findViewById(R.id.companyName);

        textInput = findViewById(R.id.textInput);


        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");

        usernameText.setText("Welcome, " + user + " you are logged in as a Service Provider.");
    }

    public void signOut(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
        finish();
    }
    public void updateInfo(View  v){

    }

}
