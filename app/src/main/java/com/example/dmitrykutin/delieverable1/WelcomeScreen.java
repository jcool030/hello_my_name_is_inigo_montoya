package com.example.dmitrykutin.delieverable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    ToggleButton adminBtn, userBtn, contractorBtn;
    Button signupBtn;
    EditText nameSignup, passwordSignup;
    RadioGroup toggleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        adminBtn = findViewById(R.id.adminBtn);
        userBtn = findViewById(R.id.userBtn);
        contractorBtn = findViewById(R.id.contractorBtn);
        signupBtn = findViewById(R.id.signupBtn);

        nameSignup = findViewById(R.id.nameSignup);
        passwordSignup = findViewById((R.id.passwordSignup));

        toggleGroup = findViewById(R.id.toggleGroup);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = nameSignup.getText().toString();
                String password = nameSignup.getText().toString();

                int id = toggleGroup.getCheckedRadioButtonId();

                if (id == -1){

                }else{
                    if (adminBtn.isChecked()){

                        new Admin ( username, password);

                    }else if (contractorBtn.isChecked()){

                        new Contractor ( username, password);

                    }else if (userBtn.isChecked()){

                        new User ( username, password);

                    }
                }


            }
        });


    }

    public void onClick(View v) {




    }
}

