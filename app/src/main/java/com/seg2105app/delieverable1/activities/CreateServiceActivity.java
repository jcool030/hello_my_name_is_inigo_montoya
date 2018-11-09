package com.seg2105app.delieverable1.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;

import com.seg2105app.delieverable1.users.Service;

public class CreateServiceActivity extends AppCompatActivity {
    private EditText getHourlyRate, getServiceName;

    public void createService(View view){
        getHourlyRate = findViewById(R.id.HourlyRate);
        getServiceName = findViewById(R.id.ServiceName);

        new Service( getServiceName.getText().toString().trim() , Double.parseDouble(getHourlyRate.getText().toString().trim()));
    }


}
