package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.seg2105app.deliverable1.activities.R;

public class OpeningScreenActivity extends AppCompatActivity{
    EditText username;
    EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        username =  (EditText)findViewById(R.id.username_textfield);
        password = (EditText)findViewById(R.id.password_textfield);
    }

    public void onLoginClick(View view){
        boolean hasUsername = username.getText().length() > 0;
        boolean hasPassword = password.getText().length() > 0;

        if(!hasUsername){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a username.", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(!hasPassword){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onNewUserClick(View view){
        Intent newUserIntent = new Intent(this, SignUpScreenActivity.class);
        startActivity(newUserIntent);
    }
}
