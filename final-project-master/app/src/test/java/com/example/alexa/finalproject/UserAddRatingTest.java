package com.example.alexa.finalproject;

import junit.framework.TestCase;

// JUnit tests by polds3
public class UserAddRatingTest extends TestCase {

    public void testAddRating() throws Exception {
        UserManager.createUser("Adam", "Pass1");
        UserManager.logInUser("Adam", "Pass1");
        Movie theMovie = new Movie("movie_01", "The Movie", "An okay movie", 1990);
        Movie theBetterMovie = new Movie("movie_02", "The Better Movie", "A better movie", 1995);

        // Good rating; no other rating(s) created yet
        User adam = UserManager.getLoggedInUser();
        adam.setMajor(User.Major.CS);
        Rating rating1 = new Rating(2, "This movie was aight.", "movie_01");
        adam.addRating(rating1);

        assert(adam.getRatings().size() == 1);
        assert(adam.geRatingForID("movie_01").getRating() == 2);
        assert(adam.geRatingForID("movie_01").getComment().equals("This movie was aight."));
        assert(adam.geRatingForID("movie_01").getMajor() == User.Major.CS);

        // Another, different rating added
        Rating rating2 = new Rating(5, "This movie was AMAZING." ,"movie_02");
        adam.addRating(rating2);

        assert(adam.getRatings().size() == 2);
        assert(adam.geRatingForID("movie_01").getRating() == 2);
        assert(adam.geRatingForID("movie_01").getComment().equals("This movie was aight."));
        assert(adam.geRatingForID("movie_01").getMajor() == User.Major.CS);
        assert(adam.geRatingForID("movie_02").getRating() == 5);
        assert(adam.geRatingForID("movie_02").getComment().equals("This movie was AMAZING."));
        assert(adam.geRatingForID("movie_02").getMajor() == User.Major.CS);

        // The following tests pertain to change(s) to current ratings

        // If (!"".equals(rating.getComment())) ==> Change comment
        Rating ratingNonEmptyComment = new Rating(5, "This movie was REALLY AMAZING.", "movie_02");
        adam.addRating(ratingNonEmptyComment);
        assertTrue(adam.geRatingForID("movie_02").getComment().equals("This movie was REALLY AMAZING."));

        // If ("".equals(rating.getComment())) ==> Don't change comment
        Rating ratingEmptyComment = new Rating(5, "", "movie_02");
        adam.addRating(ratingEmptyComment);
        assertTrue(adam.geRatingForID("movie_02").getComment().equals("This movie was REALLY AMAZING."));

        // If (rating.getRating() != 0) ==> Change rating
        Rating ratingNewValue = new Rating(3, "This movie was REALLY AMAZING.", "movie_02");
        adam.addRating(ratingNewValue);
        assertTrue(adam.geRatingForID("movie_02").getRating() == 3);

        // If (rating.getRating() == 0) ==> Don't change rating
        Rating ratingZeroValue = new Rating(0, "This movie was REALLY AMAZING.", "movie_02");
        adam.addRating(ratingZeroValue);
        assertTrue(adam.geRatingForID("movie_02").getRating() == 3);

    }
}