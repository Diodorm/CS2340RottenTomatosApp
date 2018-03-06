package com.example.alexa.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Activity for showing movies found from search term
 */
public class SearchMovies extends AppCompatActivity {
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final SearchView searchMovies = (SearchView) findViewById(R.id.sv_movies);
        final ListView lvSearchMovies = (ListView) findViewById(R.id.lv_movies);

        final Button exitSearch = (Button) findViewById(R.id.goto_nav_menu);
        exitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchMovies.this, NavigationalMenu.class));
            }
        });

        searchMovies.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try {
                            MovieQuery.searchTitle(query,
                                    getApplicationContext(),
                                    new MovieQuery.VolleyCallback() {
                                        @Override
                                        public void onSuccess(List<Movie> movies) {

                                            // Creating instance of MovieListAdapter
                                            final MovieListAdapter mCustomListAdapter =
                                                    new MovieListAdapter(
                                                            SearchMovies.this, movies);

                                            // Attaching the list view to the adapter
                                            lvSearchMovies.setAdapter(mCustomListAdapter);

                                        }
                                    });
                        } catch (UnsupportedEncodingException e) {
                            Log.d("FinalProject", "Unsupported encoding");
                        }
                        return true;
                    }
                });

        lvSearchMovies.setClickable(true);
        lvSearchMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final Movie movie = (Movie) lvSearchMovies.getItemAtPosition(position);
                MovieManager.setMovie(movie);
                startActivity(new Intent(SearchMovies.this, MovieView.class));
            }
        });
    }

}
