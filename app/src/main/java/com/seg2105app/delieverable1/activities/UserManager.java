package com.seg2105app.delieverable1.activities;

import com.seg2105app.delieverable1.users.HomeOwner;
import com.seg2105app.delieverable1.users.User;

import java.util.ArrayList;

//class that holds the arraylist
public class UserManager {

    private static UserManager instance = null;
    private ArrayList<User> userList;

    //when first created, add a dummy Service that doubles as a button to go to the edit tab
    protected UserManager() {
        //This Exists to defeat instantiation

        userList = new ArrayList<>();
    }

    //This will be called to check if a list already exists
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public ArrayList<User> getUserList()
    {
        return userList;
    }

    public User getUserAt(int index)
    {
        return userList.get(index);
    }

    public void add(User user){userList.add(user);}

    public void clear()
    {
        ArrayList<User> tmpList = new ArrayList<>();
        this.userList = tmpList;
    }
}
