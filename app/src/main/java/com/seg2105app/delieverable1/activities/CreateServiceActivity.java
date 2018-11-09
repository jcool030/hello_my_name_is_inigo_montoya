package com.seg2105app.delieverable1.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.seg2105app.delieverable1.users.*;
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


    }



    public void onClick(View view){

        sdbHandler.createService(new Service( getServiceName.getText().toString().trim() , Double.parseDouble(getHourlyRate.getText().toString().trim())));
    }


}
