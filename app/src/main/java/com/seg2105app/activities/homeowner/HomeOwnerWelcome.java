package com.seg2105app.activities.homeowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import com.seg2105app.delieverable1.activities.R;
import com.seg2105app.activities.OpeningScreenActivity;

import com.seg2105app.activities.R;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.HomeOwner;

public class HomeOwnerWelcome extends AppCompatActivity {

    HomeOwner homeOwner = (HomeOwner)CurrentUser.getCurrentUser();
    Button signoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_welcome);
        signoutButton = findViewById(R.id.signoutButton);
        Bundle bundle = getIntent().getExtras();
        //String userKey = bundle.getString("key");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Welcome, " + homeOwner.getUsername() + " you are logged in as a Home Owner.");

    }

    public void logoutClick(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        CurrentUser.logOut();
        startActivity(signoutIntent);
        finish();
    }
    public void searchForProviderClick(View v) {
        Intent searchServicesIntent = new Intent(this, ServiceSearch.class);
        startActivity(searchServicesIntent);
    }

}
