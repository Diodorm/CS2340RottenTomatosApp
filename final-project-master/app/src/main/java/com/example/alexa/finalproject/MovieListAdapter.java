package com.example.alexa.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Class for filling list views with movies
 */
public class MovieListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movies;

    /**
     * Constructor for creating the movie list adapter
     *
     * @param activity the activity
     * @param movies   the list of movies to use
     */
    public MovieListAdapter(Activity activity, List<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int location) {
        return movies.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.movie_list_row, null);
        }
        final ImageLoader imageLoader = VolleySingleton.getInstance(parent.getContext())
                .getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

        final TextView title = (TextView) convertView.findViewById(R.id.movie_title);

        final Movie movie = movies.get(position);
        thumbNail.setImageUrl(movie.getThumbnailUrl(), imageLoader);

        title.setText(movie.getTitle());

        return convertView;
    }
}
