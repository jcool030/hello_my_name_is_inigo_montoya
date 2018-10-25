package com.seg2105app.delieverable1.users;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;
import com.seg2105app.delieverable1.database.DatabaseHandler;
import com.seg2105app.delieverable1.activities.R;

import java.util.List;

public class UserList extends ArrayAdapter<User> {
private Activity context;
    List<User> users;

    public UserList(Activity context, List<User> users) {
        super(context, R.layout.activity_opening_screen, users);
        this.context = context;
        this.users = users;
    }
}
