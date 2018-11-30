package com.seg2105app.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seg2105app.users.ServiceProvider;
import com.seg2105app.users.User;

import java.util.ArrayList;

public class ServiceProviderArrayAdapter extends ArrayAdapter<ServiceProvider> {

    private final Context context;
    private final ArrayList<ServiceProvider> serviceProviders;

    public ServiceProviderArrayAdapter(Context context, ArrayList<ServiceProvider> values) {
        super(context, R.layout.serviceProvider_layout, values);
        this.context = context;
        this.serviceProviders = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServiceProvider currentUser = serviceProviders.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.user_layout, parent, false);
        TextView firstName = rowView.findViewById(R.id.firstName);
        TextView lastName = rowView.findViewById(R.id.lastName);
        ImageView rating = rowView.findViewById(R.id.ratingImage);

        firstName.setText(currentUser.getFirstName());
        lastName.setText(currentUser.getLastName());

//for the Image-Rating-stars-thing-keyword
        int resID = R.drawable.stars_0; //this is a default value

        double value = currentUser.getRating();

 // gets a rating from somewhere, rounds down to nearest int
            if (value < 0.5)
            {
                resID = R.drawable.stars_0;
            }
            else if (value < 1.5)
            {
                resID = R.drawable.stars_1;
            }
            else if (value < 2.5)
            {
                resID = R.drawable.stars_2;
            }
            else if (value < 3.5)
            {
                resID = R.drawable.stars_3;
            }
            else if (value < 4.5)
            {
                resID = R.drawable.stars_4;
            }
            else
                { //(4.5 <= value < 5)
                resID = R.drawable.stars_5;
                }
        rating.setImageResource(resID);
        return rowView;
    }
}
