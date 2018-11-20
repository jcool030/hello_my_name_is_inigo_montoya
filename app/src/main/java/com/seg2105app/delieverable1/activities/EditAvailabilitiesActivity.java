package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class EditAvailabilitiesActivity extends AppCompatActivity {

    Spinner mondayStart, mondayEnd;
    Spinner tuesdayStart, tuesdayEnd;
    Spinner wednesdayStart, wednesdayEnd;
    Spinner thursdayStart, thursdayEnd;
    Spinner fridayStart, fridayEnd;
    Spinner saturdayStart, saturdayEnd;
    Spinner sundayStart, sundayEnd;

    String user;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_availabilities);
        mondayStart = findViewById(R.id.mondayStartSpinner);
        mondayEnd = findViewById(R.id.mondayEndSpinner);
        tuesdayStart = findViewById(R.id.tuesdayStartSpinner);
        tuesdayEnd = findViewById(R.id.tuesdayEndSpinner);
        wednesdayStart = findViewById(R.id.wednesdayStartSpinner);
        wednesdayEnd = findViewById(R.id.wednesdayEndSpinner);
        thursdayStart = findViewById(R.id.thursdayStartSpinner);
        thursdayEnd = findViewById(R.id.thursdayEndSpinner);
        fridayStart = findViewById(R.id.fridayStartSpinner);
        fridayEnd = findViewById(R.id.fridayEndSpinner);
        saturdayStart = findViewById(R.id.saturdayStartSpinner);
        saturdayEnd = findViewById(R.id.saturdayEndSpinner);
        sundayStart = findViewById(R.id.sundayStartSpinner);
        sundayEnd = findViewById(R.id.sundayEndSpinner);

        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("username");
    }

    public void onConfirmAvailClick(View v){

        String mondayStartTime = mondayStart.getSelectedItem().toString();
        String mondayEndTime = mondayEnd.getSelectedItem().toString();
        String tuesdayStartTime = tuesdayStart.getSelectedItem().toString();
        String tuesdayEndTime = tuesdayEnd.getSelectedItem().toString();
        String wednesdayStartTime = wednesdayStart.getSelectedItem().toString();
        String wednesdayEndTime = wednesdayEnd.getSelectedItem().toString();
        String thursdayStartTime = thursdayStart.getSelectedItem().toString();
        String thursdayEndTime = thursdayEnd.getSelectedItem().toString();
        String fridayStartTime = fridayStart.getSelectedItem().toString();
        String fridayEndTime = fridayEnd.getSelectedItem().toString();
        String saturdayStartTime = saturdayStart.getSelectedItem().toString();
        String saturdayEndTime = saturdayEnd.getSelectedItem().toString();
        String sundayStartTime = sundayStart.getSelectedItem().toString();
        String sundayEndTime = sundayEnd.getSelectedItem().toString();

        boolean mondayIsEmpty = mondayStartTime.equals("-----") || mondayEndTime.equals("-----");
        boolean tuesdayIsEmpty = tuesdayStartTime.equals("-----") || tuesdayEndTime.equals("-----");
        boolean wednesdayIsEmpty = wednesdayStartTime.equals("-----") || wednesdayEndTime.equals("-----");
        boolean thursdayIsEmpty = thursdayStartTime == "-----" || thursdayEndTime == "-----";
        boolean fridayIsEmpty = fridayStartTime.equals("-----") || fridayEndTime.equals("-----");
        boolean saturdayIsEmpty = saturdayStartTime == "-----" || saturdayEndTime == "-----";
        boolean sundayIsEmpty = sundayStartTime.equals("-----") || sundayEndTime.equals("-----");
        //check for missing inputs
        if (mondayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Monday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (tuesdayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Tuesday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (wednesdayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Wednesday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (thursdayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Thursday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (fridayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Friday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (saturdayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Saturday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else if (sundayIsEmpty){
            Toast emptyTime = Toast.makeText(getApplicationContext(), "Missing a time for Sunday.", Toast.LENGTH_LONG);
            emptyTime.show();
        } else{
            Toast confirm = Toast.makeText(getApplicationContext(), "Availabilities Confirmed", Toast.LENGTH_LONG);
            confirm.show();

            Intent confirmAvail = new Intent(this, ServiceProviderWelcome.class);
            confirmAvail.putExtra("username", user);
            confirmAvail.putExtra("monStartTime", mondayStartTime);
            confirmAvail.putExtra("monEndTime", mondayEndTime);
            confirmAvail.putExtra("tuesStartTime", tuesdayStartTime);
            confirmAvail.putExtra("tuesEndTime", tuesdayEndTime);
            confirmAvail.putExtra("wedStartTime", wednesdayStartTime);
            confirmAvail.putExtra("wedEndTime", wednesdayEndTime);
            confirmAvail.putExtra("thursStartTime", thursdayStartTime);
            confirmAvail.putExtra("thursEndTime", thursdayEndTime);
            confirmAvail.putExtra("friStartTime", fridayStartTime);
            confirmAvail.putExtra("friEndTime", fridayEndTime);
            confirmAvail.putExtra("satStartTime", saturdayStartTime);
            confirmAvail.putExtra("satEndTime", saturdayEndTime);
            confirmAvail.putExtra("sunStartTime", sundayStartTime);
            confirmAvail.putExtra("sunEndTime", sundayEndTime);

            startActivity(confirmAvail);
            finish();
        }

    }
}
