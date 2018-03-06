package com.example.alexa.finalproject;

import java.io.Serializable;

/**
 * Class that represents a rating for a movie
 */
public class Rating implements Serializable {
    private int rating;
    private User user;
    private String comment;
    private String id;

    /**
     * Constructor for Rating
     * @param rating the user's rating for the movie
     * @param comment an optional comment
     * @param id the id for the movie associated with the rating
     */
    public Rating(int rating, String comment, String id) {
        this.rating = rating;
        this.comment = comment;
        this.id = id;
        // can only rate as the user logged in as
        user = UserManager.getLoggedInUser();
    }

    /**
     * @return the number value of the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @return the comment for the rating
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the id of the movie the rating is for
     */
    public String getId() {
        return id;
    }

    /**
     * @return the user name of the user who created the rating
     */
    public String getUserName() {
        return user.getUserName();
    }

    /**
     * @return the major of the user who created the rating
     */
    public User.Major getMajor() {
        return user.getMajor();
    }

    /**
     * @param rating set a new number as the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @param comment update or create a new comment for the rating
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
