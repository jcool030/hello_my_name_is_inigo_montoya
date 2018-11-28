package com.seg2105app.delieverable1.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
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
        final String serviceName = getServiceName.getText().toString().trim();
        final String hourlyRateString = getHourlyRate.getText().toString().trim();

        try { //try-catch block is for checking validity of hourlyRate input

            if (TextUtils.isEmpty(serviceName)) { //if serviceName is empty, prompt for input
                Toast noServiceName = Toast.makeText(CreateServiceActivity.this, "Service name is empty.", Toast.LENGTH_SHORT);
                noServiceName.show();

            } else if (TextUtils.isEmpty(hourlyRateString)) { //if hourlyRate is empty, prompt for input
                Toast noHourlyRate = Toast.makeText(CreateServiceActivity.this, "Hourly rate is empty.", Toast.LENGTH_SHORT);
                noHourlyRate.show();

            } else if(serviceName.length()>30) {
                Toast nameTooLong = Toast.makeText(CreateServiceActivity.this, "Service name is too long", Toast.LENGTH_SHORT);
                nameTooLong.show();
            }else if(hourlyRateString.length()>10){
                Toast rateTooLong = Toast.makeText(CreateServiceActivity.this, "Rate is too long.", Toast.LENGTH_SHORT);
                rateTooLong.show();

            }else{ //if both fields are filled out, checks if the name already exists before creating new service

                sdbHandler.getReferenceToServiceTable().addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean nameAlreadyExists = false;

                        for (DataSnapshot ds : dataSnapshot.getChildren()) { //THIS CHECK IS SO THAT THE NAME DOESNT ALREADY EXIST IN THE DB
                            if (ds.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class).equals(serviceName)) {
                                nameAlreadyExists = true;
                                break;
                            }
                        }
                        if (nameAlreadyExists) { //if exists, creates error
                            Toast toast = Toast.makeText(getApplicationContext(), "That service name already exists! Please try another", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            //for the database
                            sdbHandler.createService(new Service(serviceName, Double.parseDouble(hourlyRateString))); //if hourlyRate invalid, throws NumberFormatException
                            Toast toast = Toast.makeText(getApplicationContext(), "Service Created Successfully with name: "+serviceName+" and rate: "+hourlyRateString, Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        }
        catch (NumberFormatException e) { //hourly rate input is invalid (can't be parsed as a double)
            Toast noHourlyRate = Toast.makeText(CreateServiceActivity.this, "Hourly rate input is invalid.", Toast.LENGTH_SHORT);
            noHourlyRate.show();
        }
    }
}
