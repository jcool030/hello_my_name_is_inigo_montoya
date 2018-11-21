package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//A copy of the homeowner welcome screen class
public class AdminWelcome extends AppCompatActivity {

    Button signoutButton, createService;
    ListView userlv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        signoutButton = findViewById(R.id.logOutButton);

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + user + "! You are logged in as: Admin.");

        userlv = findViewById(R.id.userList);
        UserManager manager = UserManager.getInstance();//creates instance of userManager if not already exists

        UserArrayAdapter adapter = new UserArrayAdapter(this, manager.getUserList());
        userlv.setAdapter(adapter);
//        userlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
//                Intent launchEditorIntent = new Intent(getApplicationContext(), serviceEditorActivity.class);
//                launchEditorIntent.putExtra("selectedService", position);
//                startActivityForResult(launchEditorIntent, 0);
//            }
//        });

    }//end of onCreate


    public void createServiceClick(View v){
        Intent createServiceIntent = new Intent(this, CreateServiceActivity.class);
        startActivity(createServiceIntent);
    }

    public void manageServiceClick(View v){
        Intent manageServiceIntent = new Intent(this, ManageServiceActivity.class);
        startActivity(manageServiceIntent);
        Toast toast = Toast.makeText(getApplicationContext(), "If the list of services does not appear immediately, hit the 'back' button to refresh the page", Toast.LENGTH_LONG);
        toast.show();
    }


    public void logoutClick(View v) {
        Intent signoutIntent = new Intent(this, OpeningScreenActivity.class);
        startActivity(signoutIntent);
        finish();
    }
}


