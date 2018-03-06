package com.example.alexa.finalproject;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest extends TestCase {

    // Fox Kiester's method test
    public void testGetAverageRatingForMajor() throws Exception {
        UserManager.createUser("Matt", "pass");
        UserManager.logInUser("Matt", "pass");
        Movie movie = new Movie("movie_id", "The Movie", "A mediocre movie", 1984);


        // should give 0 if no one has rated the movie
        assert(movie.getAverageRatingForMajor(User.Major.CS) == 0.0);

        User matt = UserManager.getLoggedInUser();
        matt.setMajor(User.Major.CS);
        Rating rating = new Rating(1, "This movie was awful." ,"movie_id");
        matt.addRating(rating);

        assert(movie.getAverageRatingForMajor(User.Major.CS) == 1.0);

        UserManager.createUser("Jim", "pass");
        UserManager.logInUser("Jim", "pass");
        User jim = UserManager.getLoggedInUser();
        jim.setMajor(User.Major.CS);
        Rating rating2 = new Rating(3, "This movie was awfully mediocre", "movie_id");
        jim.addRating(rating2);

        assert(movie.getAverageRatingForMajor(User.Major.CS) == 2.0);

        UserManager.createUser("Jack", "pass");
        UserManager.logInUser("Jack", "pass");
        User jack = UserManager.getLoggedInUser();
        jack.setMajor(User.Major.CS);
        Rating rating3 = new Rating(5, "This movie was awfully good", "movie_id");
        jack.addRating(rating3);

        assert(movie.getAverageRatingForMajor(User.Major.CS) == 3.0);

        // non CS Major; rating should not affect average
        UserManager.createUser("Jill", "pass");
        UserManager.logInUser("Jill", "pass");
        User jill = UserManager.getLoggedInUser();
        jill.setMajor(User.Major.CHEME);
        Rating rating4 = new Rating(5, "What even is a movie?", "movie_id");
        jill.addRating(rating4);

        assert(movie.getAverageRatingForMajor(User.Major.CS) == 3.0);
    }
}