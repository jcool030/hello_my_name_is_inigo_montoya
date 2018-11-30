package com.seg2105app.activities.serviceprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.seg2105app.activities.R;
import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.activities.serviceprovider.EditAvailabilitiesActivity;

import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.ServiceProvider;
import com.seg2105app.users.UserList;

public class ServiceProviderWelcome extends AppCompatActivity {

    ServiceProvider contractor = (ServiceProvider)CurrentUser.getCurrentUser();

    Button signoutButton, updateBtn, editAvailBtn;
    TextView usernameText, availText;
    EditText phoneNum, address, companyName;
    TextInputEditText textInput;
    RadioGroup radioGroup;
    RadioButton licenseTrue;
    RadioButton licenseFalse;
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_welcome);

        signoutButton = findViewById(R.id.signoutButton);
        updateBtn = findViewById(R.id.updateInfoBtn);
        editAvailBtn = findViewById(R.id.editAvailBtn);

        availText = findViewById(R.id.availList);
        usernameText = findViewById(R.id.usernameText);
        phoneNum = findViewById(R.id.phoneNum);
        address = findViewById(R.id.address);
        companyName = findViewById(R.id.companyName);

        textInput = findViewById(R.id.textInput);
        radioGroup = findViewById(R.id.licenseBoolean);

        licenseTrue = findViewById(R.id.Yes);
        licenseFalse = findViewById(R.id.No);

//        radioGroup.addView(licenseTrue,0);
//        radioGroup.addView(licenseFalse,1);

        //Bundle bundle = getIntent().getExtras();
        //String userKey = bundle.getString("key");
        usernameText.setText("Welcome, " + contractor.getUsername() + " you are logged in as a Service Provider.");

        if(contractor.getPhoneNumber() != null){
            phoneNum.setText(contractor.getPhoneNumber());
        }
        if(contractor.getAddress() != null){
            address.setText(contractor.getAddress());
        }
        if(contractor.getCompanyName()!=null){
            companyName.setText(contractor.getCompanyName());
        }
        if(contractor.getDescription()!= null){
            textInput.setText(contractor.getDescription());
        }
        if(contractor.isLicensed()){
            licenseTrue.toggle();
        }else{
            licenseFalse.toggle();
        }
        //update availabilities displayed
        availText.setText(new StringBuilder()
                .append("Monday: "+ contractor.getAvailability(0,0) +" - " + contractor.getAvailability(0,1) + "\n")
                .append("Tuesday: " + contractor.getAvailability(1,0) + " - " + contractor.getAvailability(1,1) + "\n")
                .append("Wednesday: " + contractor.getAvailability(2,0) + " - " + contractor.getAvailability(2,1) + "\n")
                .append("Thursday: " + contractor.getAvailability(3,0) + " - " + contractor.getAvailability(3,1) + "\n")
                .append("Friday: " + contractor.getAvailability(4,0) + " - " + contractor.getAvailability(4,1) + "\n")
                .append("Saturday: " + contractor.getAvailability(5,0) + " - " + contractor.getAvailability(5,1) + "\n")
                .append("Sunday: " + contractor.getAvailability(6,0) + " - " + contractor.getAvailability(6,1) + "\n")
                .toString());
    }

    public void signOut(View v) {
        CurrentUser.logOut();
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
        finish();
    }
    public void updateInfo(View  v){
        Bundle bundle = getIntent().getExtras();

        String getPhoneNum = phoneNum.getText().toString().trim();
        String getAddress= address.getText().toString().trim();
        String getCompanyName = companyName.getText().toString().trim();
        String description = textInput.getText().toString().trim();

        int checked = radioGroup.getCheckedRadioButtonId();
        boolean license = false;

        if (checked == -1){
            Toast toast = Toast.makeText(getApplicationContext(), "Please Check a Radio Button", Toast.LENGTH_LONG);
            toast.show();
        } else {
            license = (licenseFalse.isActivated() && !licenseTrue.isActivated());
        }

        if (getPhoneNum.equals("") || !(getPhoneNum.length() == 10)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_LONG);
            toast.show();
        }
        else if (getAddress.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_LONG);
            toast.show();
        }
        else if (getCompanyName.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid Company Name", Toast.LENGTH_LONG);
            toast.show();
        }
        else if (description.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a description", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            contractor.setPhoneNumber(getPhoneNum);
            contractor.setAddress(getAddress);
            contractor.setCompanyName(getCompanyName);
            contractor.setLicensed(license);
            contractor.setDescription(description);
            contractor.notifyCurrentUser(getApplicationContext());

            Toast toast = Toast.makeText(getApplicationContext(), "Info Updated Successfully", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void onEditAvailClick(View v){
        Intent editAvailIntent = new Intent(this, EditAvailabilitiesActivity.class);
        //editAvailIntent.putExtra("username", user);
        startActivity(editAvailIntent);
    }
    public void pickUpServicesClick(View v){
        Intent addServicesInent = new Intent(this, AddServicesActivity.class);
        startActivity(addServicesInent);
    }

}
