package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;


public class Recommendations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        final ListView lvRecommendations = (ListView) findViewById(R.id.lv_recommendations);

        final Spinner spinnerMajors = (Spinner) findViewById(R.id.spinner);
        spinnerMajors.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, User.Major.values()));

        final Button exitRecommendations = (Button) findViewById(R.id.exit_recommendations);
        exitRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Recommendations.this, NavigationalMenu.class));
            }
        });

        spinnerMajors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final User.Major major = (User.Major) parent.getItemAtPosition(position);

                final List<Movie> majorMovies = MovieManager.getRecommendationsByMajor(major);
                if (majorMovies != null) {
                    // Creating instance of our MovieListAdapter
                    final MovieListAdapter mCustomListAdapter = new MovieListAdapter(Recommendations.this, majorMovies);

                    // Attaching the list view to the adapter
                    lvRecommendations.setAdapter(mCustomListAdapter);
                }

                lvRecommendations.setClickable(true);
                lvRecommendations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                        final Movie movie = (Movie) lvRecommendations.getItemAtPosition(position);
                        MovieManager.setMovie(movie);
                        startActivity(new Intent(Recommendations.this, MovieView.class));
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                final List<Movie> majorMovies = MovieManager.getRecommendationsByMajor(User.Major.UNDEC);
                if (majorMovies != null) {
                    // Creating instance of our MovieListAdapter
                    final MovieListAdapter mCustomListAdapter =
                            new MovieListAdapter(Recommendations.this, majorMovies);

                    // Attaching the list view to the adapter
                    lvRecommendations.setAdapter(mCustomListAdapter);
                }
            }
        });


    }

}
