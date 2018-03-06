package com.example.alexa.finalproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class
 */
public class User implements Serializable {
    private String userName;
    private String password;

    /**
     * Enum for a user's app access status
     */
    public enum Status {
        ACTIVE("ACTIVE"),
        LOCKED("LOCKED"),
        BANNED("BANNED");

        private String value;

        /**
         * @param value the new status
         */
        Status(final String value) {
            this.value = value;
        }

        /**
         * @return the status
         */
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }
    private Status status = User.Status.ACTIVE;

    /**
     * Enum representing a user's major
     */
    public enum Major {
        CS("CS"),
        EE("EE"),
        ME("ME"),
        IYSE("IYSE"),
        MATH("MATH"),
        PHYS("PHYS"),
        CHEM("CHEM"),
        CHEME("CHEME"),
        UNDEC("UNDEC");

        private String value;

        /**
         * @param value the major
         */
        Major(final String value) {
            this.value = value;
        }

        /**
         * @return the major
         */
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }
    private Major major = User.Major.UNDEC;

    private String interests = "";
    // all the ratings a user has made
    private List<Rating> ratings = new ArrayList<>();

    /**
     * The constructor only requires the username and password at first.
     *
     * @param userName the person's username
     * @param password the person's password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // compares based on password and userName only
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User user = (User) o;

        return getUserName().equals(user.getUserName())
                && getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    /**
     * @param userName the new username for the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param password the new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param status the new status for the user
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @param major the new major for the user
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     *
     * @param interests the new interests of the user
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * Adds a rating to the user's ratings. Will not add multiple ratings for
     * the same movie and will keep unspecified data the same as the previous
     * rating.
     * @param rating a rating the user made
     */
    public void addRating(Rating rating) {
        final String id = rating.getId();
        Rating previousRating;
        try {
            previousRating = geRatingForID(id);
            if (!"".equals(rating.getComment())) {
                previousRating.setComment(rating.getComment());
            }
            if (rating.getRating() != 0) {
                previousRating.setRating(rating.getRating());
            }

        } catch (UserHasNotRatedMovieException e) {
            // this is the first time the user has rated the movie
            ratings.add(rating);
        }
    }

    /**
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the user's status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return the user's major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * @return the user's interests
     */
    public String getInterests() {
        return interests;
    }

    /**
     * @return all the ratings a user has made
     */
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * @param id the id of the movie
     * @return the user's rating for the movie
     * @throws UserHasNotRatedMovieException when the user has not rated the movie
     */
    public Rating geRatingForID(String id) throws UserHasNotRatedMovieException {
        for (final Rating r : ratings) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        throw new UserHasNotRatedMovieException("User has not rated movie "
                + id);
    }

    /**
     * A checked exception for when a user has not made a rating for a
     * particular movie. Only thrown from User.
     */
    public static class UserHasNotRatedMovieException extends Exception {
        /**
         * @param s error mesage
         */
        public UserHasNotRatedMovieException(String s) {
            super(s);
        }
    }

}
