package com.seg2105app.delieverable1.users;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.activities.R;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserList implements Serializable {
    private static LinkedList<User> users;
    private Iterator<User> iterator;

    public UserList(){

        users = new LinkedList<User>();
        iterator = users.iterator();
    }

    public User getNext() {
        return iterator.next();
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }

    public User getFirst(){
        return users.getFirst();
    }

    public void add(User toAdd){
        users.add(toAdd);
    }


}
