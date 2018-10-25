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
    EditText nameSignup, passwordSignup, firstNameSignup, lastNameSignup;
    RadioGroup toggleGroup;
    boolean adminSelected, userSelected, contractorSelected;
    UserList users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        users = (UserList) getIntent().getSerializableExtra("userList");
        adminBtn = findViewById(R.id.adminBtn);
        userBtn = findViewById(R.id.userBtn);
        contractorBtn = findViewById(R.id.contractorBtn);
        signupBtn = findViewById(R.id.signupBtn);

        nameSignup = findViewById(R.id.nameSignup);
        passwordSignup = findViewById(R.id.passwordSignup);
        firstNameSignup = findViewById(R.id.firstNameSignup);
        lastNameSignup = findViewById(R.id.lastNameSignup);

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
        String firstName = firstNameSignup.getText().toString();
        String lastName = lastNameSignup.getText().toString();

        if (adminSelected && !contractorSelected && !userSelected){ //Admin selected

            Administrator admin = new Administrator (username, password, firstName, lastName, "Admin");
            users.add(admin);

        }else if (contractorSelected && !adminSelected && !userSelected){ //ServiceProvider selected

            ServiceProvider contractor = new ServiceProvider (username, password, firstName, lastName, "ServiceProvider");
            users.add(contractor);
            //Creating a return intent to pass info to the service provider page
            Intent returnIntent = new Intent();
            //Adding stuff to the return intent
            returnIntent.putExtra("usernameData", username);
            setResult(RESULT_OK, returnIntent);
            //Finish this activity to save memory
            finish();

        }else if (userSelected && !adminSelected && !contractorSelected){ //HomeOwner selected

            HomeOwner homeOwner = new HomeOwner (username, password, firstName, lastName, "HomeOwner");
            users.add(homeOwner);
            Intent intent = new Intent(this, HomeOwnerWelcome.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid user type selection", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}

