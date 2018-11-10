package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seg2105app.delieverable1.users.Service;

public class serviceEditorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);

        //gets an intent from the admin welcome screen (the int position of the service you clicked)
        Intent intent = getIntent();
        int serviceIndex = intent.getIntExtra("selectedService",0); //0 is a "default return value"

        //Getting TextFields to update
        final EditText serviceName = (EditText) findViewById(R.id.editServiceName);
        final EditText serviceRate = (EditText) findViewById(R.id.editServiceRate);

        //Select the service to modify
        final Service currentService = serviceManager.getInstance().getServiceAt(serviceIndex);

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

                if (!hasName) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a Service Name.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!hasRate) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter an Hourly Rate.", Toast.LENGTH_SHORT);
                    toast.show();
                } else
                    {
                    currentService.setName(serviceName.getText().toString().trim());
                    currentService.setRate(Double.parseDouble(serviceRate.getText().toString().trim()));

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    }
            }
        });//end of saveButton

        //Button to just return to the AdminWelcome screen
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Button to create a new service rather than just save changes
        Button createService = findViewById(R.id.newServiceButton);
        createService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean hasName = !TextUtils.isEmpty(serviceName.getText());
                boolean hasRate = !TextUtils.isEmpty(serviceRate.getText());

                if (!hasName)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a Service Name.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (!hasRate)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter an Hourly Rate.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                    {
                        serviceManager manager = serviceManager.getInstance();
                        manager.getServiceList().add(currentService); //even though this is a new manager,

                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
            }
        });
    }
        //eventually add a delete button maybe, but be sure not to delete the button if its the last one in the arraylist
}


