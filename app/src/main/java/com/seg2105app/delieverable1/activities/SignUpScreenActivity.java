package com.seg2105app.delieverable1.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.content.Intent;
//import com.seg2105app.delieverable1.activities.R;
import com.seg2105app.delieverable1.users.*;

import org.w3c.dom.Text;

public class SignUpScreenActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton adminBtn, userBtn, contractorBtn;
    Button signupBtn;
    EditText nameSignup, passwordSignup, firstNameSignup, lastNameSignup;
    RadioGroup toggleGroup;
    boolean adminSelected, userSelected, contractorSelected;

    UserList users = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        //users = (UserList) getIntent().getSerializableExtra("userList");
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
        String username = nameSignup.getText().toString().trim();
        String password = passwordSignup.getText().toString().trim();
        String firstName = firstNameSignup.getText().toString().trim();
        String lastName = lastNameSignup.getText().toString().trim();

        boolean hasUsername = !TextUtils.isEmpty(username);
        boolean hasPassword = !TextUtils.isEmpty(password);
        boolean hasFirstName = !TextUtils.isEmpty(firstName);
        boolean hasLastName = !TextUtils.isEmpty(lastName);


        while (users.hasNext()) { //check to see if username is taken
            User checkedUser = users.getNext();

            if (username.equals(checkedUser.getUsername())) {
                Toast userAlreadyExists = Toast.makeText(SignUpScreenActivity.this, "Please select another username.", Toast.LENGTH_LONG);
                userAlreadyExists.show();
                return;
            }
        }


        if (adminSelected && !contractorSelected && !userSelected){ //Admin selected

            Administrator admin = new Administrator (username, password, firstName, lastName, "Admin");
            users.add(admin);

        }else if (contractorSelected && !adminSelected && !userSelected){ //ServiceProvider selected

            ServiceProvider contractor = new ServiceProvider (username, password, firstName, lastName, "ServiceProvider");
            users.add(contractor);



        }else if (userSelected && !adminSelected && !contractorSelected){ //HomeOwner selected

            HomeOwner homeOwner = new HomeOwner (username, password, firstName, lastName, "HomeOwner");
            users.add(homeOwner);
            finish();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid user type selection", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Intent returnIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(returnIntent);
    }


}

