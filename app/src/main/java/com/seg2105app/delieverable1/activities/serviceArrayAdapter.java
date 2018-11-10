package com.seg2105app.delieverable1.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.seg2105app.delieverable1.users.Service;
import java.util.ArrayList;

public class serviceArrayAdapter extends ArrayAdapter<Service> {

    private final Context context;
    private final ArrayList<Service> services;

    public serviceArrayAdapter(Context context, ArrayList<Service> values) {
        super(context, R.layout.service_layout, values);
        this.context = context;
        this.services = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Service currentService = services.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_layout, parent, false);
        TextView serviceName = (TextView) rowView.findViewById(R.id.serviceName);
        TextView serviceRate = (TextView) rowView.findViewById(R.id.serviceRate);

        serviceName.setText(currentService.getName());
        serviceRate.setText(String.valueOf(currentService.getRate()));

        return rowView;
    }
}
