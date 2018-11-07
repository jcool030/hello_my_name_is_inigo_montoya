package com.seg2105app.delieverable1.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.content.Intent;
//import com.seg2105app.delieverable1.activities.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seg2105app.delieverable1.users.*;

public class SignUpScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private ToggleButton adminBtn, userBtn, contractorBtn;
    private Button signupBtn;
    private EditText nameSignup, passwordSignup, firstNameSignup, lastNameSignup;
    private RadioGroup toggleGroup;
    private boolean adminSelected, userSelected, contractorSelected;
    private static boolean adminAlreadyExists;

    UserList users;

    DatabaseHandler udbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup_screen);
        //users = (UserList) getIntent().getSerializableExtra("userList");
        adminSelected = true; //default to have this be red, matching the default visual
                                //this way, you won't have to double click admin to create the first account
        adminBtn = findViewById(R.id.adminBtn);
        userBtn = findViewById(R.id.userBtn);
        contractorBtn = findViewById(R.id.contractorBtn);
        signupBtn = findViewById(R.id.signupBtn);

        nameSignup = findViewById(R.id.nameSignup);
        passwordSignup = findViewById(R.id.passwordSignup);
        firstNameSignup = findViewById(R.id.firstNameSignup);
        lastNameSignup = findViewById(R.id.lastNameSignup);

        toggleGroup = findViewById(R.id.toggleGroup);

        udbHandler = new DatabaseHandler(this);

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

        users = UserList.getInstance();

        signupBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        String username = nameSignup.getText().toString().trim();
        String password = passwordSignup.getText().toString().trim();
        String firstName = firstNameSignup.getText().toString().trim();
        String lastName = lastNameSignup.getText().toString().trim();

        boolean hasUsername = !TextUtils.isEmpty(username);
        boolean hasPassword = !TextUtils.isEmpty(password);
        boolean hasFirstName = !TextUtils.isEmpty(firstName);
        boolean hasLastName = !TextUtils.isEmpty(lastName);



        //verify that user filled out all info
        if(!hasUsername){
            Toast noUserName = Toast.makeText(SignUpScreenActivity.this, "Please enter a username.", Toast.LENGTH_LONG);
            noUserName.show();
        }else if(!hasFirstName){
            Toast noFirstName = Toast.makeText(SignUpScreenActivity.this, "Please enter a first name.", Toast.LENGTH_LONG);
            noFirstName.show();
        }else if(!hasLastName){
            Toast noLastName = Toast.makeText(SignUpScreenActivity.this, "Please enter a last name.", Toast.LENGTH_LONG);
            noLastName.show();
        }else if(!hasPassword){
            Toast noPassword = Toast.makeText(SignUpScreenActivity.this, "Please enter a password.", Toast.LENGTH_LONG);
            noPassword.show();
        }
        else {
            UserFactory userFactory = new UserFactory();

            if (adminSelected && !contractorSelected && !userSelected) { //Admin selected

                if(adminAlreadyExists)
                {
                    Toast adminAlreadyExists = Toast.makeText(SignUpScreenActivity.this, "An Admin account already exists.", Toast.LENGTH_LONG);
                    adminAlreadyExists.show();
                }
                else
                    {
                        Administrator admin = (Administrator)userFactory.getUser(username, password, firstName, lastName, "Administrator");
                        users.add(admin);
                        udbHandler.createUserWithUsernameAndPassword(this,admin);
                        adminAlreadyExists = true;//after making the first one, it stores it permanently to prevent creating another
                    }
            } else if (contractorSelected && !adminSelected && !userSelected) { //ServiceProvider selected

                ServiceProvider contractor = (ServiceProvider)userFactory.getUser(username, password, firstName, lastName, "ServiceProvider");
                users.add(contractor);
                udbHandler.createUserWithUsernameAndPassword(this,contractor);

            } else if (userSelected && !adminSelected && !contractorSelected) { //HomeOwner selected

                HomeOwner homeOwner = (HomeOwner)userFactory.getUser(username, password, firstName, lastName, "HomeOwner");
                users.add(homeOwner);
                udbHandler.createUserWithUsernameAndPassword(this,homeOwner);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid user type selection", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            Intent returnIntent = new Intent(this, OpeningScreenActivity.class);
            startActivity(returnIntent);
            finish();
        }
    }

}

