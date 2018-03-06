package com.example.alexa.finalproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Activity for editing the logged-in user's profile
 */
public class ProfileEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        final Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean noErrors = true;

                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;

                final String newMajor = ((EditText) findViewById(R.id.major))
                        .getText().toString();
                if (!"".equals(newMajor)) {
                    try {
                        User.Major.valueOf(newMajor.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        final Toast toast = Toast.makeText(context,
                                e.getMessage(), duration);
                        toast.show();
                        noErrors = false;
                    }
                }

                final String newUserName = ((EditText) findViewById(R.id.user_name))
                        .getText().toString();
                if (UserManager.userExists(newUserName)) {
                    final String message = "The username " + newUserName
                            + " is already taken.";
                    final Toast toast = Toast.makeText(context,
                            message, duration);
                    toast.show();
                    noErrors = false;
                }

                final String newPassword = ((EditText) findViewById(R.id.password))
                        .getText().toString();
                final String newInterests = ((EditText) findViewById(R.id.interests))
                        .getText().toString();
                if (noErrors) {
                    if (!"".equals(newUserName)) {
                        UserManager.setUserName(newUserName);
                    }

                    if (!"".equals(newPassword)) {
                        UserManager.setPassword(newPassword);
                    }

                    if (!"".equals(newMajor)) {
                        UserManager.setMajor(User.Major.valueOf(newMajor
                                .trim().toUpperCase()));
                    }

                    if (!"".equals(newInterests)) {
                        UserManager.setInterests(newInterests);
                    }

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
                        Log.d("FinalProject", e.getMessage());
                    }

                    final Toast toast = Toast.makeText(context,
                            "Successfully updated information.", duration);
                    toast.show();
                }
            }
        });
    }

}
