package com.example.alexa.finalproject;

import java.util.List;

/**
 * Class that represents a Movie
 */
public class Movie {
    private String title;
    private String summary;
    // private String genre;
    private int year;
    // unique movie id
    private String id;
    private String thumbnailUrl;
    private List<Rating> ratings;

    /**
     * @param id the unique id of the movie
     * @param title the title of the movie
     * @param summary a summary of the movie
     * @param year the year the movie was released
     */
    public Movie(String id, String title, String summary, int year,
                 String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        // this.genre = genre;
        this.year = year;
        this.thumbnailUrl = thumbnailUrl;
        // pull in all existing ratings for the movie based on its id
        ratings = UserManager.getRatingsForID(id);
    }

    /**
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the summary of the movie
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @return the id of the movie
     */
    public String getId() {
        return id;
    }

    /**
     * @return the year of the movie
     */
    public int getYear() {
        return year;
    }

//    /**
//     * @return the genre of the movie
//     */
//    public String getGenre() {
//        return genre;
//    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Update ratings for the movie
     */
    public void updateRatings() {
        ratings = UserManager.getRatingsForID(id);
    }

    /**
     * @return a list of all ratings that have been made for the movie
     */
    public List<Rating> getRatings() {
        return this.ratings;
    }

    /**
     * @param major the major to get the average ratings for
     * @return the average rating for this movie
     */
    public float getAverageRatingForMajor(User.Major major) {
        updateRatings();
        float count = 0;
        float total = 0;
        for (final Rating rating : ratings) {
            if (rating.getMajor().equals(major)) {
                total += rating.getRating();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return total / count;
        }
    }
}
