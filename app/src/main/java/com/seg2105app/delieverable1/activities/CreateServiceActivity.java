package com.seg2105app.delieverable1.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.seg2105app.delieverable1.database.*;

import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.users.Service;

public class CreateServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText getHourlyRate, getServiceName;
    private DatabaseHandler sdbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        getHourlyRate = findViewById(R.id.HourlyRate);
        getServiceName = findViewById(R.id.ServiceName);
        sdbHandler = new DatabaseHandler(this);
    }



    public void onClick(View view) {
        String serviceName = getServiceName.getText().toString().trim();
        String hourlyRateString = getHourlyRate.getText().toString().trim();

        try { //try-catch block is for checking validity of hourlyRate input

            if (TextUtils.isEmpty(serviceName)) { //if serviceName is empty, prompt for input
                Toast noServiceName = Toast.makeText(CreateServiceActivity.this, "Service name is empty.", Toast.LENGTH_LONG);
                noServiceName.show();

            }else if(TextUtils.isEmpty(hourlyRateString)){ //if hourlyRate is empty, prompt for input
                Toast noHourlyRate = Toast.makeText(CreateServiceActivity.this, "Hourly rate is empty.", Toast.LENGTH_LONG);
                noHourlyRate.show();

            }else { //if both fields are filled out, create new service
                sdbHandler.createService(new Service(serviceName, Double.parseDouble(hourlyRateString))); //if hourlyRate invalid, throws NumberFormatException
            }

        }catch(NumberFormatException e){ //hourly rate input is invalid (can't be parsed as a double)
            Toast noHourlyRate = Toast.makeText(CreateServiceActivity.this, "Hourly rate input is invalid.", Toast.LENGTH_LONG);
            noHourlyRate.show();
        }
    }

}
