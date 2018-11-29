package com.seg2105app.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.activities.admin.ManageServiceActivity;
import com.seg2105app.delieverable1.activities.R;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.ServiceList;
import com.seg2105app.services.Service;

public class ServiceEditorActivity extends AppCompatActivity
{
    //ctrl+f:    In some worlds    for notes on a future change
    private DatabaseHandler sdbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);
        sdbHandler = new DatabaseHandler(this);

        //gets an intent from the admin welcome screen (the int position of the service you clicked)
        Intent intent = getIntent();
        int serviceIndex = intent.getIntExtra("selectedService",0);

        //Getting TextFields to update
        final EditText serviceName = (EditText) findViewById(R.id.editServiceName);
        final EditText serviceRate = (EditText) findViewById(R.id.editServiceRate);

        //Select the service to modify
        final Service currentService = ServiceList.getInstance().getServiceAt(serviceIndex);

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
                        final String newName = serviceName.getText().toString();
                        final String newRate = serviceRate.getText().toString();

                        sdbHandler.getReferenceToServiceTable().addListenerForSingleValueEvent(new ValueEventListener(){

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                String key = "";
                                boolean nameAlreadyExists = false;

                                if(!newName.equals(currentService.getName())) {       //so if name was changed, it does the check
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) { //THIS CHECK IS SO THAT THE NAME DOESNT ALREADY EXIST IN THE DB
                                        if (ds.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class).equals(newName)) {
                                            nameAlreadyExists = true;
                                            break;
                                        }
                                    }
                                }
                                if(newName.length()>30) {
                                    Toast nameTooLong = Toast.makeText(getApplicationContext(), "Service name is too long (no more than 30 characters)", Toast.LENGTH_SHORT);
                                    nameTooLong.show();
                                }else if(newRate.length()>10) {
                                    Toast rateTooLong = Toast.makeText(getApplicationContext(), "Rate is too long.", Toast.LENGTH_SHORT);
                                    rateTooLong.show();
                                }
                                else if (nameAlreadyExists) { //if exists, creates error
                                    Toast toast = Toast.makeText(getApplicationContext(), "That service name already exists! Please try another", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else                      //In some worlds    Startpoint
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    if (ds.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class).equals(currentService.getName())) {
                                        key = ds.getKey();
                                        break;
                                        }                  //In some worlds    ENDS HERE, but needs the event listener bit too
                                    }
                                    if (key != "")
                                    {           //precaution - not sure if there's a time when the key is not there (maybe someone else modifies database at same time as you?)
                                        currentService.setName(newName);
                                        currentService.setRate( Double.parseDouble(newRate));
                                        sdbHandler.updateService(currentService, key);
                                        Toast toast = Toast.makeText(getApplicationContext(), "Service Saved Successfully", Toast.LENGTH_SHORT);
                                        toast.show();

                                        Intent ServiceListIntent = new Intent(getApplicationContext(), ManageServiceActivity.class);
                                        finish();
                                        startActivity(ServiceListIntent);
                                    }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                catch(NumberFormatException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Hourly rate input is invalid", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }); //END OF SAVE BUTTON

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
                //In some worlds, this code doesnt need to exist (it is duplicate of stuff in save button).
                //Until we move the code to the start of this file it is still necessary.
                sdbHandler.getReferenceToServiceTable().addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.child(DatabaseHandler.ServiceEntry.COLUMN_SERVICE_NAME).getValue(String.class).equals(currentService.getName())) {
                                ds.getRef().removeValue();

                                Toast toast = Toast.makeText(getApplicationContext(), "Service Sucessfully Deleted", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent ServiceListIntent = new Intent(getApplicationContext(), ManageServiceActivity.class);
                                finish();
                                startActivity(ServiceListIntent);
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                    });
            }
            });
    }
}


