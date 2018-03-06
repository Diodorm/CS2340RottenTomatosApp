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
 * Activity for displaying new releases
 */
public class NewReleases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_releases);

        final ListView lvNewReleases = (ListView) findViewById(R.id.lv_new_releases);

        final Button exitNewReleases = (Button) findViewById(R.id.exit_new_releases);
        exitNewReleases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewReleases.this, NavigationalMenu.class));
            }
        });
        // temporary test
        try {
            MovieQuery.newReleases(getApplicationContext(), new MovieQuery.VolleyCallback() {
                @Override
                public void onSuccess(List<Movie> movies) {

                    // Creating instance of our MovieListAdapter
                    final MovieListAdapter mCustomListAdapter =
                            new MovieListAdapter(NewReleases.this, movies);

                    // Attaching the list view to the adapter
                    lvNewReleases.setAdapter(mCustomListAdapter);

                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.d("FinalProject", "Unsupported encoding");
        }

        lvNewReleases.setClickable(true);
        lvNewReleases.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final Movie movie = (Movie) lvNewReleases.getItemAtPosition(position);
                MovieManager.setMovie(movie);
                startActivity(new Intent(NewReleases.this, MovieView.class));
            }
        });
    }

}
