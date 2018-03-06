package com.example.alexa.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProfileViewAdmin extends AppCompatActivity {

    /**
     * Persist users
     */
    private void saveUsers() {
        try {
            final FileOutputStream outputStream =
                    openFileOutput("users.bin",
                            Context.MODE_PRIVATE);
            final ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(UserManager.getUsers());
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.d("FinalProjct", e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view_admin);

        final User userToView = UserManager.getUserToView();

        final TextView userName = (TextView) findViewById(R.id.user_name_adm);
        userName.setText(userToView.getUserName());

        final TextView status = (TextView) findViewById(R.id.status_adm);
        status.setText(userToView.getStatus().toString());

        final TextView major = (TextView) findViewById(R.id.major_adm);
        if (userToView.getMajor() != null) {
            major.setText(userToView.getMajor().toString());
        }

        final TextView interests = (TextView) findViewById(R.id.interests_adm);
        interests.setText(userToView.getInterests());

        final Button exitViewUsers = (Button) findViewById(R.id.btn_close_prf_admin);
        exitViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileViewAdmin.this, NavigationalMenuAdmin.class));
            }
        });

        final Button makeActive = (Button) findViewById(R.id.btn_status_active);
        makeActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userToView.setStatus(User.Status.ACTIVE);
                saveUsers();
                startActivity(new Intent(ProfileViewAdmin.this, ProfileViewAdmin.class));
            }
        });

        final Button banUser = (Button) findViewById(R.id.btn_status_ban);
        banUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userToView.setStatus(User.Status.BANNED);
                saveUsers();
                startActivity(new Intent(ProfileViewAdmin.this, ProfileViewAdmin.class));
            }
        });

        final Button lockUser = (Button) findViewById(R.id.btn_status_lock);
        lockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userToView.setStatus(User.Status.LOCKED);
                saveUsers();
                startActivity(new Intent(ProfileViewAdmin.this, ProfileViewAdmin.class));
            }
        });

    }
}
