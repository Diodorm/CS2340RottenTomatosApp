package com.example.alexa.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Class for filling list views with movies
 */
public class ViewUsersAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<User> users;

    /**
     * Constructor for creating the movie list adapter
     *
     * @param activity the activity
     * @param users    the list of movies to use
     */
    public ViewUsersAdapter(Activity activity, List<User> users) {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int location) {
        return users.get(location);
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
            convertView = inflater.inflate(R.layout.view_users_row, null);
        }

        final TextView userName = (TextView) convertView.findViewById(R.id.user_name);
        final TextView status = (TextView) convertView.findViewById(R.id.user_name_status);

        final User user = users.get(position);

        userName.setText(user.getUserName());
        status.setText(user.getStatus().toString());

        return convertView;
    }
}
