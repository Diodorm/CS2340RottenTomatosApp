package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Activity for displaying new DVDs
 */
public class NewDVDs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dvds);

        final ListView lvNewDVDs = (ListView) findViewById(R.id.lv_new_dvds);

        final Button exitNewDVDs = (Button) findViewById(R.id.exit_new_dvds);
        exitNewDVDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewDVDs.this, NavigationalMenu.class));
            }
        });

        try {
            MovieQuery.newDVDReleases(getApplicationContext(), new MovieQuery.VolleyCallback() {
                @Override
                public void onSuccess(List<Movie> movies) {

                    // Creating instance of our MovieListAdapter
                    final MovieListAdapter mCustomListAdapter = new MovieListAdapter(NewDVDs.this, movies);

                    // Attaching the list view to the adapter
                    lvNewDVDs.setAdapter(mCustomListAdapter);

                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("FinalProject", "Unsupported encoding");
        }

        lvNewDVDs.setClickable(true);
        lvNewDVDs.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final Movie movie = (Movie) lvNewDVDs.getItemAtPosition(position);
                MovieManager.setMovie(movie);
                startActivity(new Intent(NewDVDs.this, MovieView.class));
            }
        });
    }

}
