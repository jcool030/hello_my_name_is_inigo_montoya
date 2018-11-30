package com.seg2105app.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.seg2105app.activities.R;
import com.seg2105app.services.Service;
import java.util.ArrayList;

public class ServiceArrayAdapter extends ArrayAdapter<Service> {

    private final Context context;
    private final ArrayList<Service> services;

    public ServiceArrayAdapter(Context context, ArrayList<Service> values) {
        super(context, R.layout.service_layout, values);
        this.context = context;
        this.services = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Service currentService = services.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_layout, parent, false);
        TextView serviceName = rowView.findViewById(R.id.serviceName);
        TextView serviceRate = rowView.findViewById(R.id.serviceRate);

        serviceName.setText(currentService.getName());
        serviceRate.setText(String.valueOf(currentService.getRate()));

        return rowView;
    }
}
