package com.seg2105app.activities.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.seg2105app.activities.R;
import com.seg2105app.activities.ServiceArrayAdapter;
import com.seg2105app.activities.ServiceProviderArrayAdapter;
import com.seg2105app.activities.UserArrayAdapter;
import com.seg2105app.database.DatabaseHandler;
import com.seg2105app.services.ServiceList;
import com.seg2105app.users.ServiceProviderList;
import com.seg2105app.users.UserList;

public class ListOfCurrentUsersActivity extends AppCompatActivity {

    private ListView listView;//2 is for user, 1 is for service provider
    private ListView listView2;
    private ServiceProviderArrayAdapter adapter;
    private UserArrayAdapter adapter2;
    private UserList userList = UserList.getInstance();
    private ServiceProviderList sProviderList = ServiceProviderList.getInstance();

    private DatabaseHandler udbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        udbHandler = new DatabaseHandler(this);


        //top list 1; service provider
        listView = findViewById(R.id.sProviderList);
        sProviderList = ServiceProviderList.getInstance();

        userList.populateUserList(udbHandler);
        adapter = new ServiceProviderArrayAdapter(this, sProviderList.getUserList());
        listView.setAdapter(adapter);


        //bottom list 2; users
        listView = findViewById(R.id.userList);
        userList = UserList.getInstance();

        userList.populateUserList(udbHandler);
        adapter2 = new UserArrayAdapter(this, userList.getUserList());
        listView.setAdapter(adapter2);
    }




   /* public void refreshClick(View v){
        listView = findViewById(R.id.serviceList);
        adapter2 = new userArrayAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                Intent launchEditorIntent = new Intent(getApplicationContext(), ServiceEditorActivity.class);
                launchEditorIntent.putExtra("selectedService", position);
                startActivity(launchEditorIntent);
                finish();
            }
        });
    }  */
}
