package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.seg2105app.delieverable1.activities.R;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;

public class ServiceProviderWelcome extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_welcome);
    }

    //This is to make the text greet you by username when you log in
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED) return; //for later
        String username = data.getStringExtra("usernameData");
        TextView eText = findViewById(R.id.usernameText);
        eText.setText("Welcome, "+ username + ". You are logged in as: Service Provider");
    }
}
