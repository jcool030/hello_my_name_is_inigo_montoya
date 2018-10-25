package com.seg2105app.delieverable1.users;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.activities.R;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserList implements Serializable { //extends ArrayAdapter<User> {
//    private Activity context;
//    List<User> users;
//
//    public UserList(Activity context, List<User> users) {
//        super(context, R.layout.activity_opening_screen, users);
//        this.context = context;
//        this.users = users;
//    }
    private LinkedList<User> users;

    public UserList(){
        users = new LinkedList<User>();
    }

    public User getNext(int currentIndex) {
        User next = users.get(currentIndex + 1);
        return next;
    }

    public boolean hasNext(int currentIndex){
        try{
            users.get(currentIndex+1);
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public User getFirst(){
        return users.getFirst();
    }

    public void add(User toAdd){
        users.add(toAdd);
    }
}
