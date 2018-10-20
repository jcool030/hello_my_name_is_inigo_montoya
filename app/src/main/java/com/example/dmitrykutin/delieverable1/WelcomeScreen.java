package com.example.dmitrykutin.delieverable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    ToggleButton adminBtn, userBtn, contractorBtn;
    Button signupBtn;
    int accountType;
    EditText nameSignup, passwordSignup;



    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        ((RadioGroup) findViewById(R.id.toggleGroup)).setOnCheckedChangeListener(ToggleListener);

        adminBtn = findViewById(R.id.adminBtn);
        userBtn = findViewById(R.id.userBtn);
        contractorBtn = findViewById(R.id.contractorBtn);
        signupBtn = findViewById(R.id.signupBtn);

        nameSignup = findViewById(R.id.nameSignup);
        passwordSignup = findViewById((R.id.passwordSignup));




        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accountType = 0;

            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                accountType = 1;

            }
        });

        contractorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                accountType = 2;

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                String username = nameSignup.getText().toString();
                String password =  nameSignup.getText().toString();

                if (accountType == 0){

                    new Admin( username, password );

                }else if (accountType == 1){

                    new User( username, password );

                }else if (accountType == 2){

                    new Contractor( username, password );

                }
            }
        });

    }

    @Override
    public void onClick(View v) {



    }
}
