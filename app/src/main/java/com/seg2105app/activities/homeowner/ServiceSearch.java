package com.seg2105app.activities.homeowner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.R;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.Service;
import com.seg2105app.services.ServiceList;
import com.seg2105app.users.User;
import com.seg2105app.users.UserList;
import com.seg2105app.users.ServiceProvider;

import java.util.ArrayList;

public class ServiceSearch extends AppCompatActivity {

    private DatabaseHandler sdbHandler;
    private ListView listView;
    private ServiceList serviceManager;
    private ServiceArrayAdapter adapter;
    private EditText serviceSearch;
    private ArrayList<Service> arrayList;
    private UserList userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_services);
        sdbHandler = new DatabaseHandler(this);

        listView = findViewById(R.id.serviceList);
        serviceSearch = findViewById(R.id.search);

        serviceManager = ServiceList.getInstance();//creates instance of serviceManager if not already exists
        serviceManager.populateServiceList(sdbHandler);

        userManager = UserList.getInstance();
        userManager.populateUserList(sdbHandler);

        adapter = new ServiceArrayAdapter(this, serviceManager.getServiceList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, final View view, int position, long id){

            }
        });
        listView = findViewById(R.id.serviceList);
        adapter = new ServiceArrayAdapter(this, serviceManager.getServiceList());
        listView.setAdapter(adapter);

    }
    public void searchClick(View v){
        final String search = serviceSearch.getText().toString().trim();
        arrayList = new ArrayList<>();
        for (Service s : serviceManager.getServiceList()) {
            if (s.getName().equals(search)){
                arrayList.add(s);
            }else {

            }
        }
        for (User u : userManager.getUserList()) {
            if (u.getType().equals("ServiceProvider")) {
                ServiceProvider serviceProvider = (ServiceProvider)u;
                if (serviceProvider.getRating() == Double.parseDouble(search) || serviceProvider.getFirstName().equals(search)) {
                    //for (Service s : serviceProvider.getServiceListings()){
                    //arrayList.add(serviceProvider.get)
                    //}


                }
            }
        }

        if (arrayList.size() == 0){
            Toast noResults = Toast.makeText(ServiceSearch.this, "No Results found", Toast.LENGTH_SHORT);
            noResults.show();
        }
        adapter = new ServiceArrayAdapter(this, arrayList);
        listView.setAdapter(adapter);



    }
    public void refreshClick(View v){
        listView = findViewById(R.id.serviceList);
        adapter = new ServiceArrayAdapter(this, serviceManager.getServiceList());
        listView.setAdapter(adapter);
    }


}
