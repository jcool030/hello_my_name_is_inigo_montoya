package com.seg2105app.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.seg2105app.database.DatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import static android.content.ContentValues.TAG;

public class UserList extends ArrayList<UserList.UserElement> implements Serializable {
    private static UserList instance = null;
    private DatabaseHandler udbHandler;

    private UserList(){
        super();
    }

    public class UserElement{
       String key;
       User user;

       public UserElement(String key, User user){
           this.key = key;
           this.user = user;
       }
    }

    public static UserList getInstance(){
        if (instance == null){
            instance = new UserList();
        }

        return instance;
    }

    public static User getUser(String key){
        if (instance == null){
            return null;
        } else {
            for (ListIterator<UserElement> iter = instance.listIterator(); iter.hasNext(); ) {
                UserElement userE = iter.next();
                if (userE.key.equals(key)) {
                    return userE.user;
                }
            }
            return null;
        }
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> list = new ArrayList<>();

        for(ListIterator<UserElement> iter = instance.listIterator(); iter.hasNext();){
            list.add(iter.next().user);
        }

        return list;
    }

    public void populateUserList(DatabaseHandler udbHandler) {
        instance.clear();

        udbHandler.getReferenceToUserTable().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    String username = snapshot.child(DatabaseHandler.UserEntry.COLUMN_USERNAME).getValue(String.class);
                    String password = snapshot.child(DatabaseHandler.UserEntry.COLUMN_PASSWORD).getValue(String.class);
                    String firstname = snapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_FIRST_NAME).getValue(String.class);
                    String lastname = snapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_LAST_NAME).getValue(String.class);
                    String type = snapshot.child(DatabaseHandler.UserInfoEntry.COLUMN_USER_TYPE).getValue(String.class);

                    UserFactory factory = new UserFactory();
                    User user = factory.getUser(username, password, firstname,lastname, type);
                    instance.add(new UserElement(snapshot.getKey(), user));

                    if (user == null){
                        Log.d(TAG, "user is null: " + username + " " + type);
                    }else {
                        Log.d(TAG, "Adding user from database : " + user.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
