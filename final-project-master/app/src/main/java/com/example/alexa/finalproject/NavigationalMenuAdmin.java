package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NavigationalMenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigational_menu_admin);

        final Button logoutButton = (Button) findViewById(R.id.logout_button_adm);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.logOutUser();
                startActivity(new Intent(NavigationalMenuAdmin.this, WelcomeScreen.class));
            }
        });

        final Button viewUsers = (Button) findViewById(R.id.view_users);
        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.setUserToView(UserManager.getLoggedInUser());
                startActivity(new Intent(NavigationalMenuAdmin.this, ViewUsers.class));
            }
        });

    }
}
