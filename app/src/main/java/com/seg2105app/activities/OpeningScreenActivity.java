package com.seg2105app.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.database.DatabaseHandler;

import com.seg2105app.activities.R;
import com.seg2105app.users.CurrentUser;
import com.seg2105app.users.ServiceProvider;
import com.seg2105app.users.User;
import com.seg2105app.users.UserFactory;
import com.seg2105app.users.UserList;

public class OpeningScreenActivity extends AppCompatActivity{
    EditText username;
    EditText password;

    //DatabaseReference userDB = FirebaseDatabase.getInstance().getReference();
    UserList users = UserList.getInstance();
    DatabaseHandler udbHandler = new DatabaseHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState){
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        username =  findViewById(R.id.username_textfield);
        password = findViewById(R.id.password_textfield);
    }

    public void onLoginClick(View view){
        boolean hasUsername = !TextUtils.isEmpty(username.getText());
        boolean hasPassword = !TextUtils.isEmpty(password.getText());

        if(!hasUsername){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a username.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(!hasPassword){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Query query = udbHandler.getReferenceToUserTable();
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    DataSnapshot ds = udbHandler.validateUsernameAndPassword(dataSnapshot, username.getText().toString().trim(), password.getText().toString().trim());

                    if (ds == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username or Password are incorrect", Toast.LENGTH_SHORT);
                        toast.show();

                        username.setText("");
                        password.setText("");
                    } else {
                        String username = ds.child(DatabaseHandler.UserEntry.COLUMN_USERNAME).getValue(String.class);
                        String password = ds.child(DatabaseHandler.UserEntry.COLUMN_PASSWORD).getValue(String.class);
                        String firstName = ds.child(DatabaseHandler.UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
                        String lastName = ds.child(DatabaseHandler.UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
                        String type = ds.child(DatabaseHandler.UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);


                        UserFactory factory = new UserFactory();
                        User user = factory.getUser(username, password, firstName, lastName, type);

                        if (type.equals("ServiceProvider")){
                            String phoneNumber = null, address = null, description = null, company = null;
                            boolean licensed = false;

                            if (ds.hasChild(DatabaseHandler.ServiceProviderEntry.COLUMN_PHONE_NUM))
                                phoneNumber = ds.child(DatabaseHandler.ServiceProviderEntry.COLUMN_PHONE_NUM).getValue(String.class);
                            if (ds.hasChild(DatabaseHandler.ServiceProviderEntry.COLUMN_ADDRESS))
                                address = ds.child(DatabaseHandler.ServiceProviderEntry.COLUMN_ADDRESS).getValue(String.class);
                            if (ds.hasChild(DatabaseHandler.ServiceProviderEntry.COLUMN_DESCRIPTION))
                                description = ds.child(DatabaseHandler.ServiceProviderEntry.COLUMN_DESCRIPTION).getValue(String.class);
                            if (ds.hasChild(DatabaseHandler.ServiceProviderEntry.COLUMN_COMPANY))
                            company = ds.child(DatabaseHandler.ServiceProviderEntry.COLUMN_COMPANY).getValue(String.class);
                            if (ds.hasChild(DatabaseHandler.ServiceProviderEntry.COLUMN_LICENSED))
                                licensed = ds.child(DatabaseHandler.ServiceProviderEntry.COLUMN_LICENSED).getValue(Boolean.class);

                            ServiceProvider serviceProvider = (ServiceProvider)user;
                            serviceProvider.setDescription(description);
                            serviceProvider.setLicensed(licensed);
                            serviceProvider.setCompanyName(company);
                            serviceProvider.setAddress(address);
                            serviceProvider.setPhoneNumber(phoneNumber);


                            user = serviceProvider;
                        }


                        CurrentUser.setCurrentLogIn(user, ds.getKey());
                        CurrentUser currentUser = new CurrentUser();
                        currentUser.logIn(OpeningScreenActivity.this);
                        finish();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    public void onNewUserClick(View view){
        Intent newUserIntent = new Intent(OpeningScreenActivity.this, SignUpScreenActivity.class);
        startActivity(newUserIntent);
    }
}
