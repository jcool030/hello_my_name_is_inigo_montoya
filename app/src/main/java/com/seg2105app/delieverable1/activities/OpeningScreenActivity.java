package com.seg2105app.delieverable1.activities;

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
import com.seg2105app.delieverable1.users.*;

import java.util.ListIterator;

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
        username =  (EditText)findViewById(R.id.username_textfield);
        password = (EditText)findViewById(R.id.password_textfield);
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
                    User user = udbHandler.validateUsernameAndPassword(dataSnapshot, username.getText().toString().trim(), password.getText().toString().trim());

                    if (user == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username or Password are incorrect", Toast.LENGTH_LONG);
                        toast.show();

                        username.setText("");
                        password.setText("");
                    } else {
                        if (user.getType().equals("Administrator")) {
                            Intent intent = new Intent(OpeningScreenActivity.this, AdminWelcome.class);
                            intent.putExtra("username", user.getUsername());
                            startActivity(intent);
                            finish();
                        } else if (user.getType().equals("ServiceProvider")) {
                            Intent intent = new Intent(OpeningScreenActivity.this, ServiceProviderWelcome.class);
                            intent.putExtra("username", user.getUsername());
                            startActivity(intent);
                            finish();
                        } else if (user.getType().equals("HomeOwner")) {
                            Intent intent = new Intent(OpeningScreenActivity.this, HomeOwnerWelcome.class);
                            intent.putExtra("username", user.getUsername());
                            startActivity(intent);
                            finish();
                        }
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
