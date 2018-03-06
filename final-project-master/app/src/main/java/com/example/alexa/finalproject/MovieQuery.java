package com.example.alexa.finalproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for performing movie searches
 */
public final class MovieQuery {
    // url for movies
    private static String apikey = "yedukp76ffytfuy24zsqk7f5";
    private static String moviesUrl = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + apikey;
    private static String newReleasesUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=" + apikey;
    private static String newDVDReleasesUrl = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + apikey;

    /**
     * Don't allow instantiation
     */
    private MovieQuery() {

    }

    /**
     * @param url      url to obtain the json data from
     * @param context  the context
     * @param callback a volley callback
     * @return a list of movies obtained from the query
     * @throws UnsupportedEncodingException
     */
    private static List<Movie> urlQuery(String url, Context context,
                                        final VolleyCallback callback)
            throws UnsupportedEncodingException {
        final List<Movie> movies = new ArrayList<>();


        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        try {
                            final JSONArray array = resp.getJSONArray("movies");
                            for (int i = 0; i < array.length(); i++) {

                                try {
                                    // for each array element, we have to create an object
                                    final JSONObject jsonObject = array.getJSONObject(i);
                                    assert jsonObject != null;
                                    final String id = jsonObject.optString("id");
                                    final String title = jsonObject.optString("title");
                                    final String summary = jsonObject.optString("synopsis");
                                    // List.. String genre = jsonObject.optString();
                                    // int year = Integer.parseInt(jsonObject.optString("year").trim());
                                    final int year = 1400;
                                    final String thumbnailUrl = jsonObject.getJSONObject("posters").optString("thumbnail");
                                    final Movie movie = new Movie(id, title, summary, year, thumbnailUrl);
                                    //save the object for later
                                    MovieManager.addMovie(movie);
                                    movies.add(movie);

                                } catch (JSONException e) {
                                    Log.d("VolleyApp", "Failed to get JSON object");
                                    Log.d("FinalProject", e.getMessage());
                                }
                            }
                        } catch (JSONException e) {
                            Log.d("FinalProject", e.getMessage());
                        }
                        callback.onSuccess(movies);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Volley Test", "Error: " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);

        return movies;
    }

//    /**
//     * @param url url to obtain the json data from
//     * @param context the context
//     * @param callback a volley callback
//     * @return a movie obtained from the query
//     * @throws UnsupportedEncodingException
//     */
//    private static List<Movie> idQuery(String url, Context context,
//                                        final VolleyCallback callback)
//            throws UnsupportedEncodingException {
//        // so the same onSuccess can be used
//        final List<Movie> movies = new ArrayList<>();
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject resp) {
//                        // not really necessary
//                        String id = resp.optString("id");
//                        String title = resp.optString("title");
//                        String summary = resp.optString("synopsis");
//                        // List.. String genre = resp.optString();
//                        // int year = Integer.parseInt(resp.optString("year"));
//                        int year = 1400;
//                        final Movie movie = new Movie(id, title, summary, year);
//                        movies.add(movie);
//                        callback.onSuccess(movies);
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d("Volley Test", "Error: " + error.getMessage());
//                    }
//                });
//
//        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
//        return movies;
//    }

    /**
     * @param title    the movie title search term
     * @param context  the context
     * @param callback a volley callback
     * @return a list of movies obtained from the query
     * @throws UnsupportedEncodingException
     */
    public static List<Movie> searchTitle(String title, Context context,
                                          final VolleyCallback callback)
            throws UnsupportedEncodingException {
        final String url = moviesUrl + "&q=" + URLEncoder.encode(title, "UTF-8");
        return urlQuery(url, context, callback);
    }

    /**
     * @param context  the context
     * @param callback a volley callback
     * @return a list of movies obtained from the query
     * @throws UnsupportedEncodingException
     */
    public static List<Movie> newReleases(Context context, final VolleyCallback callback)
            throws UnsupportedEncodingException {
        return urlQuery(newReleasesUrl, context, callback);
    }

    /**
     * @param context  the context
     * @param callback a volley callback
     * @return a list of movies obtained from the query
     * @throws UnsupportedEncodingException
     */
    public static List<Movie> newDVDReleases(Context context,
                                             final VolleyCallback callback)
            throws UnsupportedEncodingException {
        return urlQuery(newDVDReleasesUrl, context, callback);
    }

//    /**
//     * @param id id of the Movie to find the information for
//     * @param context the context
//     * @param callback a volley callback
//     * @return the movie matching the id
//     * @throws UnsupportedEncodingException
//     */
//    public static Movie movieByID(String id, Context context,
//                                  final VolleyCallback callback)
//    throws UnsupportedEncodingException {
//        String movieIDURL =
//                "http://api.rottentomatoes.com/api/public/v1.0/movies/"
//                        + id + ".json?apikey=" + apikey;
//        // will only contain one movie
//        List<Movie> movies = idQuery(movieIDURL, context, callback);
//        return movies.get(0);
//    }

    /**
     * Return a list of movies on success for callback
     */
    public interface VolleyCallback {
        void onSuccess(List<Movie> movies);
    }
}