package com.example.alexa.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProfileView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        final User userToView = UserManager.getUserToView();

        final TextView userName = (TextView) findViewById(R.id.user_name);
        userName.setText(userToView.getUserName());

        final TextView major = (TextView) findViewById(R.id.major);
        if (userToView.getMajor() != null) {
            major.setText(userToView.getMajor().toString());
        }

        final TextView interests = (TextView) findViewById(R.id.interests);
        interests.setText(userToView.getInterests());
    }
}
