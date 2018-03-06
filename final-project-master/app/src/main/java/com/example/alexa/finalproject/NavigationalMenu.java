package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NavigationalMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigational_menu);

        final Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.logOutUser();
                startActivity(new Intent(NavigationalMenu.this, WelcomeScreen.class));
            }
        });

        final Button viewProfileButton = (Button) findViewById(R.id.view_profile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.setUserToView(UserManager.getLoggedInUser());
                startActivity(new Intent(NavigationalMenu.this, ProfileView.class));
            }
        });

        final Button editProfileButton = (Button) findViewById(R.id.goto_edit_profile);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationalMenu.this, ProfileEditor.class));
            }
        });

        final Button viewSearchButton = (Button) findViewById(R.id.goto_search);
        viewSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationalMenu.this, SearchMovies.class));
            }
        });

        final Button viewNewReleases = (Button) findViewById(R.id.goto_new);
        viewNewReleases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationalMenu.this, NewReleases.class));
            }
        });

        final Button viewNewDVDs = (Button) findViewById(R.id.goto_new_dvds);
        viewNewDVDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationalMenu.this, NewDVDs.class));
            }
        });

        final Button viewRecommendations = (Button) findViewById(R.id.goto_recommendations);
        viewRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationalMenu.this, Recommendations.class));
            }
        });
    }
}
