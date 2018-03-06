package com.example.alexa.finalproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Stores all movies searchead as well as the selected movie to be viewed
 * in the MovieView.
 */
public final class MovieManager {
    private static Movie movie;
    private static List<Movie> movies = new ArrayList<>();

    /**
     * Don't allow instantiation
     */
    private MovieManager() {

    }

    /**
     * @param m set m as the movie to view in MovieView
     */
    public static void setMovie(Movie m) {
        movie = m;
    }

    /**
     * @return get the last movie set to be viewed in MovieView
     */
    public static Movie getMovie() {
        return movie;
    }

    /**
     * @param id movie id to check
     * @return whether the movie is already stored
     */
    public static boolean movieStored(String id) {
        for (final Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Store a movie if it is not already stored.
     *
     * @param movie the movie object to add
     */
    public static void addMovie(Movie movie) {
        if (!movieStored(movie.getId())) {
            movies.add(movie);
        }
    }

    /**
     * @param id the id of the movie to search for
     * @return the movie if it is stored or null
     */
    public static Movie getMovieById(String id) {
        for (final Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * @param major the major to find highly rated movies for
     * @return a sorted list of movies
     */
    public static List<Movie> getRecommendationsByMajor(User.Major major) {
        final List<Movie> movieList = new ArrayList<>();
        for (final Movie movie : movies) {
            movie.updateRatings();
            for (final Rating rating : movie.getRatings()) {
                if (rating.getMajor().equals(major)) {
                    movieList.add(movie);
                    break;
                }
            }
        }
        final User.Major theMajor = major;
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                final float difference = o2.getAverageRatingForMajor(theMajor)
                        - o1.getAverageRatingForMajor(theMajor);
                if (difference < 0) {
                    return (int) Math.floor(difference);
                } else {
                    return (int) Math.ceil(difference);
                }
            }
        });
        return movieList;
    }

}
