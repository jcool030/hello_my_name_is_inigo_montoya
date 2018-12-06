package com.seg2105app.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceListing;

import java.util.ArrayList;

public class ServiceListingArrayAdapter extends ArrayAdapter <ServiceListing> {

    private final Context context;
    private final ArrayList<ServiceListing> servicesListings;

    public ServiceListingArrayAdapter(Context context, ArrayList<ServiceListing> values) {
        super(context, R.layout.service_layout, values);
        this.context = context;
        this.servicesListings = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServiceListing currentService = servicesListings.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_layout, parent, false);
        TextView serviceName = rowView.findViewById(R.id.serviceName);
        TextView serviceRate = rowView.findViewById(R.id.serviceRate);

        serviceName.setText(currentService.getService().getName());
        serviceRate.setText(String.valueOf(currentService.getService().getRate()));

        return rowView;
    }
}
