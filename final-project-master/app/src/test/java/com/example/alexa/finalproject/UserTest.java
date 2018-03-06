package com.example.alexa.finalproject;

import junit.framework.TestCase;

import org.junit.rules.ExpectedException;

/**
 * Created by Leonard Chen on 4/4/2016.
 */
public class UserTest extends TestCase {

    //Leo's Test Case for geRatingForID method
    //Yes, this method has a typo and is listed in code evaluation. :/
    public void testGeRatingForID() throws Exception {
        //Sample user login setup
        UserManager.createUser("Tom", "pass");
        UserManager.logInUser("Tom", "pass");
        User tom = UserManager.getLoggedInUser();
        Movie movie1 = new Movie("movie_id1", "The Princess Bride", "A mediocre movie", 1984);

        //should return ratings if ID of the movie is found
        Rating rating1 = new Rating(1, "This movie was awful." ,"movie_id1");
        tom.addRating(rating1);
        assert(tom.geRatingForID(movie1.getId()).getId().equals(rating1.getId()));

        //should return new rating of same movie
        Rating rating2 = new Rating(3, "This movie was dank." ,"movie_id1");
        tom.addRating(rating2);
        assert(tom.geRatingForID(movie1.getId()).getId().equals(rating2.getId()));

        //should return new rating new movie
        Movie movie2 = new Movie("movie_id2", "Zootopia", "2016 Best Movie", 2016);
        Rating rating3 = new Rating(5, "This movie was the best." ,"movie_id2");
        tom.addRating(rating3);
        assert(tom.geRatingForID(movie2.getId()).getId().equals(rating3.getId()));

        //test new user login
        UserManager.createUser("Jerry", "pass");
        UserManager.logInUser("Jerry", "pass");
        User jerry = UserManager.getLoggedInUser();
        Movie movie3 = new Movie("movie_id3", "Much Memes", "Ayy Lmao", 2016);

        //should return new rating of new movie
        Rating rating4 = new Rating(4, "WHY U NO RATE 5 STARS!" ,"movie_id3");
        jerry.addRating(rating4);
        assert(jerry.geRatingForID(movie3.getId()).getId().equals(rating4.getId()));

        //test if user has not rated movie
        Movie movie4 = new Movie("movie_id4", "Paranormal",
                "This Movie doesn't exist... or does it?", 2194);

        try {
            jerry.geRatingForID(movie4.getId());
        } catch (User.UserHasNotRatedMovieException anUserHasNotRatedMovieException) {
            assert(anUserHasNotRatedMovieException.getMessage().equals((
                    "User has not rated movie " + movie4.getId())));
        }
    }
}