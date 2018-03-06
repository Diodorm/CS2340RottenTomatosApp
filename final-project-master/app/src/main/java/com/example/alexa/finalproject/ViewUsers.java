package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ViewUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        final ListView lvViewUsers = (ListView) findViewById(R.id.lv_view_users);

        final Button exitNewDVDs = (Button) findViewById(R.id.exit_view_users);
        exitNewDVDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewUsers.this, NavigationalMenuAdmin.class));
            }
        });

        final ViewUsersAdapter mCustomListAdapter = new ViewUsersAdapter(ViewUsers.this, UserManager.getUsers());
        lvViewUsers.setAdapter(mCustomListAdapter);

        lvViewUsers.setClickable(true);
        lvViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final User user = (User) lvViewUsers.getItemAtPosition(position);
                UserManager.setUserToView(user);
                startActivity(new Intent(ViewUsers.this, ProfileViewAdmin.class));
            }
        });
    }

}
