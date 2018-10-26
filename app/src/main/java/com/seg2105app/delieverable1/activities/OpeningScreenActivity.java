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

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class OpeningScreenActivity extends AppCompatActivity{
    EditText username;
    EditText password;

    //DatabaseReference userDB = FirebaseDatabase.getInstance().getReference();
    UserList users = new UserList();

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
        else if(!hasPassword){
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            boolean validLoginCredentials = false;
            //Iterator<User> iter = users.iterator();
            //User firstUser = users.getFirst();

            while (users.hasNext()){
                User currentUser = users.getNext();
                if (currentUser.getUsername().equals(username.getText().toString().trim())){
                    if (currentUser.getPassword().equals(password.getText().toString().trim())){
                        validLoginCredentials = true;
                        //finds which welcome screen to go to
                        if(currentUser instanceof Administrator){
                            Intent intent = new Intent(this, AdminWelcome.class);
                             intent.putExtra("username", currentUser.getUsername());
                           startActivity(intent);
                           return;
                        }
                        else if(currentUser instanceof ServiceProvider){
                            Intent intent = new Intent(this, ServiceProviderWelcome.class);
                            intent.putExtra("username", currentUser.getUsername());
                            startActivity(intent);
                            return;
                        }
                        else if(currentUser instanceof HomeOwner){
                            Intent intent = new Intent(this, HomeOwnerWelcome.class);
                            intent.putExtra("username", currentUser.getUsername());
                            startActivity(intent);
                            return;
                        }
                        break;
                    }
                    else //if username found, no password found (bug testing)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username found, password incorrect", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else // if no username found
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
                //this below code is obsolete with the toast edits - to be removed later
            if (!validLoginCredentials) {
                Toast toast = Toast.makeText(getApplicationContext(), "Username or Password are incorrect", Toast.LENGTH_SHORT);
                toast.show();

                username.setText("");
                password.setText("");
            }
        }
    }

    public void onNewUserClick(View view){
        Intent newUserIntent = new Intent(OpeningScreenActivity.this, SignUpScreenActivity.class);
        startActivity(newUserIntent);
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        userDB.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//                users.clear();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                    User user = postSnapshot.getValue(User.class);
//                    users.add(user);
//                }
//
//                UserList userAdaptor = new UserList(OpeningScreenActivity.this, users);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError){
//
//            }
//        });
//    }
}
