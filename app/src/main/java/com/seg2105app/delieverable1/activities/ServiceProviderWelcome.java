package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceProviderWelcome extends AppCompatActivity implements View.OnClickListener {

    Button signoutButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_welcome);
        signoutButton = findViewById(R.id.signoutButton);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        TextView textView = (TextView) findViewById(R.id.usernameText);
        textView.setText("Welcome, " + user + " you are logged in as a Service Provider.");

        signoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
    }
}
