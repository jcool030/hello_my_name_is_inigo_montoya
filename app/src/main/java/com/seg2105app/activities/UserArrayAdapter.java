package com.seg2105app.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.seg2105app.delieverable1.activities.R;
import com.seg2105app.users.User;

import java.util.ArrayList;

public class UserArrayAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final ArrayList<User> users;

    public UserArrayAdapter(Context context, ArrayList<User> values) {
        super(context, R.layout.user_layout, values);
        this.context = context;
        this.users = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User currentUser = users.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.user_layout, parent, false);
        TextView userName = rowView.findViewById(R.id.userName);
        TextView firstName = rowView.findViewById(R.id.firstName);
        TextView lastName = rowView.findViewById(R.id.lastName);
        TextView userType = rowView.findViewById(R.id.userType);

        userName.setText(currentUser.getUsername());
        firstName.setText(currentUser.getFirstName());
        lastName.setText(currentUser.getLastName());
        userType.setText(currentUser.getType());

        return rowView;
    }
}
