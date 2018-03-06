package com.example.alexa.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MovieView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);

        final Movie movie = MovieManager.getMovie();

        // Movie TextView's
        final TextView movieTitle = (TextView) findViewById(R.id.tv_movie_title);
        final TextView movieDescription = (TextView) findViewById(R.id.tv_movie_description);

        // Movie rating edit's
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.rb_rate_movie);
        final EditText ratingComments = (EditText) findViewById(R.id.et_movie_comments);

        movieTitle.setText(movie.getTitle());
        movieDescription.setText(movie.getSummary());

        final ListView lvRatings = (ListView) findViewById(R.id.lv_movie_ratings);

        // Creating instance of our MovieListAdapter
        final RatingListAdapter mCustomListAdapter = new RatingListAdapter(MovieView.this, MovieManager.getMovie().getRatings());

        lvRatings.setAdapter(mCustomListAdapter);

        final Button updateRating = (Button) findViewById(R.id.btn_update_rating);
        updateRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Movie movie = MovieManager.getMovie();

                final Rating rating = new Rating(
                        (int) ratingBar.getRating(),
                        ratingComments.getText().toString(),
                        movie.getId());

                final User user = UserManager.getLoggedInUser();
                user.addRating(rating);

                try {
                    final FileOutputStream outputStream = openFileOutput("users.bin",
                            Context.MODE_PRIVATE);
                    final ObjectOutputStream objectOutputStream =
                            new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(UserManager.getUsers());
                    objectOutputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    Log.d("FinalProject", e.getMessage());
                }

                movie.updateRatings();
                MovieManager.setMovie(movie);

                mCustomListAdapter.notifyDataSetChanged();

                startActivity(new Intent(MovieView.this, MovieView.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, NavigationalMenu.class);
        startActivity(intent);
    }

}
