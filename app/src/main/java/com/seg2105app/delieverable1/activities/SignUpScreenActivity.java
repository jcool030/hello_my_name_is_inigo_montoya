package com.seg2105app.delieverable1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
//import com.seg2105app.delieverable1.activities.R;
import com.seg2105app.delieverable1.users.*;

public class SignUpScreenActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton adminBtn, userBtn, contractorBtn;
    Button signupBtn;
    EditText nameSignup, passwordSignup;
    RadioGroup toggleGroup;
    boolean adminSelected, userSelected, contractorSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        adminBtn = findViewById(R.id.adminBtn);
        userBtn = findViewById(R.id.userBtn);
        contractorBtn = findViewById(R.id.contractorBtn);
        signupBtn = findViewById(R.id.signupBtn);

        nameSignup = findViewById(R.id.nameSignup);
        passwordSignup = findViewById((R.id.passwordSignup));

        toggleGroup = findViewById(R.id.toggleGroup);

        //check which toggle is selected (maybe change to RadioButtons later to make easier)
        adminBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adminSelected = true;
                } else {
                    adminSelected = false;
                }
            }
        });
        userBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    userSelected = true;
                } else {
                    userSelected = false;
                }
            }
        });
        contractorBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    contractorSelected = true;
                } else {
                    contractorSelected = false;
                }
            }
        });

        signupBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String username = nameSignup.getText().toString();
        String password = passwordSignup.getText().toString();

        if (adminSelected){

            new Administrator ( username, password);

        }else if (contractorSelected){

            new ServiceProvider ( username, password);

        }else if (userSelected){

            new HomeOwner ( username, password);
            Intent intent = new Intent(this, HomeOwnerWelcome.class);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "No user type selected", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClick(View v) {




    }
}

