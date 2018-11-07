package com.seg2105app.delieverable1.users;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.seg2105app.delieverable1.activities.R;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class UserList extends LinkedList<User> implements Serializable {
    private static UserList instance = null;
    private ListIterator<User> iterator;

    private UserList(){
        super();
    }

    public static UserList getInstance(){
        if (instance == null){
            instance = new UserList();
        }

        return instance;
    }

    public User getNext() {
        return iterator.next();
    }

    public boolean hasNext(){
        return iterator.hasNext();
    }

    public ListIterator<User> listIterator(){
        return this.listIterator();
    }
}
