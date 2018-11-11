package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seg2105app.delieverable1.users.Service;

public class ServiceEditorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);

        //gets an intent from the admin welcome screen (the int position of the service you clicked)
        Intent intent = getIntent();
        int serviceIndex = intent.getIntExtra("selectedService",0);

        //Getting TextFields to update
        final EditText serviceName = (EditText) findViewById(R.id.editServiceName);
        final EditText serviceRate = (EditText) findViewById(R.id.editServiceRate);

        //Select the service to modify
        final Service currentService = ServiceManager.getInstance().getServiceAt(serviceIndex);

        //Updating contents in this screen
        serviceName.setText(currentService.getName());
        serviceRate.setText(String.valueOf(currentService.getRate()));

        //Button to save changes
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                //Testing if fields are filled (duplicate of login screen)
                boolean hasName = !TextUtils.isEmpty(serviceName.getText());
                boolean hasRate = !TextUtils.isEmpty(serviceRate.getText());

                try
                {
                    if (!hasName) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please enter a Service Name.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (!hasRate) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please enter an Hourly Rate.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        currentService.setName(serviceName.getText().toString().trim());
                        currentService.setRate(Double.parseDouble(serviceRate.getText().toString().trim()));
                        Intent ServiceListIntent = new Intent(getApplicationContext(), ManageServiceActivity.class);
                        Toast toast = Toast.makeText(getApplicationContext(), "Service Saved Successfully", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        startActivity(ServiceListIntent);
                    }
                }
                catch(NumberFormatException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Hourly rate input is invalid", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Button to just return to the list of services
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ServiceListIntent = new Intent(getApplicationContext(), ManageServiceActivity.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Changes Cancelled", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(ServiceListIntent);
            }
        });

        //Button to delete an existing service
        Button deleteService = findViewById(R.id.deleteButton);
        deleteService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //STILL NEEDS TO BE REWORKED TO WORK WITH DATABASE
                //maybe include a different toast for if it fails to delete, since right now it just says it deletes it without checking if it's the last service in the arraylist
                //in fact, it doesnt even delete it yet
                Intent ServiceListIntent = new Intent(getApplicationContext(), ManageServiceActivity.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(ServiceListIntent);
            }
            });
    }
}


