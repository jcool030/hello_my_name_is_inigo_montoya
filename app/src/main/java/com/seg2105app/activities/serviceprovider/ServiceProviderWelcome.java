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

import com.seg2105app.activities.OpeningScreenActivity;
import com.seg2105app.activities.serviceprovider.EditAvailabilitiesActivity;
import com.seg2105app.activities.R;
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

        Bundle bundle = getIntent().getExtras();
        String userKey = bundle.getString("key");
        usernameText.setText("Welcome, " + contractor.getUsername() + " you are logged in as a Service Provider.");

        String monStart = bundle.getString("monStartTime");
        String monEnd = bundle.getString("monEndTime");
        String tuesStart = bundle.getString("tuesStartTime");
        String tuesEnd = bundle.getString("tuesEndTime");
        String wedStart = bundle.getString("wedStartTime");
        String wedEnd = bundle.getString("wedEndTime");
        String thursStart = bundle.getString("thursStartTime");
        String thursEnd = bundle.getString("thursEndTime");
        String friStart = bundle.getString("friStartTime");
        String friEnd = bundle.getString("friEndTime");
        String satStart = bundle.getString("satStartTime");
        String satEnd = bundle.getString("satEndTime");
        String sunStart = bundle.getString("sunStartTime");
        String sunEnd = bundle.getString("sunEndTime");
        availText.setText(new StringBuilder()
                .append("Monday: "+ monStart +" - " + monEnd + "\n")
                .append("Tuesday: " + tuesStart + " - " + tuesEnd + "\n")
                .append("Wednesday: " + wedStart + " - " + wedEnd + "\n")
                .append("Thursday: " + thursStart + " - " + thursEnd + "\n")
                .append("Friday: " + friStart + " - " + friEnd + "\n")
                .append("Saturday: " + satStart + " - " + satEnd + "\n")
                .append("Sunday: " + sunStart + " - " + sunEnd + "\n")
                .toString());
    }

    public void signOut(View v) {
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
            license = licenseTrue.isSelected();
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

            bundle.putString("company", getCompanyName);
            bundle.putString("description", description);
            bundle.putString("address", getAddress);
            bundle.putString("phone", getPhoneNum);
            bundle.putBoolean("license", license);

            Toast toast = Toast.makeText(getApplicationContext(), "Info Updated Successfully", Toast.LENGTH_LONG);
            toast.show();

        }

    }
    public void onEditAvailClick(View v){
        Intent editAvailIntent = new Intent(this, EditAvailabilitiesActivity.class);
        editAvailIntent.putExtra("username", user);
        startActivity(editAvailIntent);
    }

}