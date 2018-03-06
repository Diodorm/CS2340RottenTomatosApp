package com.example.alexa.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Class for filling a list view with ratings.
 */
public class RatingListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Rating> ratings;

    /**
     * Constructor for creating the rating list adapter
     * @param activity the activity
     * @param ratings the list of ratings to use
     */
    public RatingListAdapter(Activity activity, List<Rating> ratings) {
        this.activity = activity;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int location) {
        return ratings.get(location);
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
            convertView = inflater.inflate(R.layout.rating_list_row, null);
        }

        final TextView username = (TextView) convertView.findViewById(R.id.rating_username);
        final RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating_number);
        final TextView major = (TextView) convertView.findViewById(R.id.rating_major);
        final TextView comment = (TextView) convertView.findViewById(R.id.rating_comment);

        final Rating rating = ratings.get(position);

        username.setText(rating.getUserName());
        ratingBar.setRating(rating.getRating());
        major.setText(rating.getMajor().toString());
        comment.setText(rating.getComment());

        return convertView;
    }
}
