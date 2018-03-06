package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        final Button logoutButton = (Button) findViewById(R.id.goto_login);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this, LoginActivity.class));
            }
        });

        try {
            final FileInputStream fileInputStream = openFileInput("users.bin");
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            @SuppressWarnings("unchecked")
            final List<User> users = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
            UserManager.setUsers(users);
        } catch (IOException | ClassNotFoundException e) {
            Log.d("FinalProject", e.getMessage());
        }
    }
}
