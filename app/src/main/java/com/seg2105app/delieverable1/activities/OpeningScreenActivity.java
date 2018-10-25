package com.seg2105app.delieverable1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.users.*;
import com.seg2105app.delieverable1.activities.R;

import java.util.Iterator;
import java.util.List;

public class OpeningScreenActivity extends AppCompatActivity{
    EditText username;
    EditText password;

    DatabaseReference userDB = FirebaseDatabase.getInstance().getReference();
    List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState){
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
        if(!hasPassword){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT);
            toast.show();
        }

        if (hasUsername && hasPassword){
            boolean validLoginCredentials = false;
            for (Iterator<User> iter = users.iterator(); iter.hasNext();){
                User currentUser = iter.next();
                if (currentUser.getUsername().equals(username.getText().toString().trim())){
                    if (currentUser.getPassword().equals(password.getText().toString().trim())){
                        validLoginCredentials = true;
                    }
                }
            }

            if (!validLoginCredentials){
                Toast toast = Toast.makeText(getApplicationContext(), "Username or Password are incorrect", Toast.LENGTH_SHORT);
                toast.show();

                username.setText("");
                password.setText("");
            }
        }
    }

    public void onNewUserClick(View view){
        Intent newUserIntent = new Intent(this, SignUpScreenActivity.class);
        startActivity(newUserIntent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        userDB.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                users.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                    users.add(user);
                }

                UserList userAdaptor = new UserList(OpeningScreenActivity.this, users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}
