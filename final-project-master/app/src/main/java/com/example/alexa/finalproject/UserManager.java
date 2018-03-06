package com.example.alexa.finalproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage users
 */
public final class UserManager {
    private static List<User> users = new ArrayList<>();
    private static User loggedInUser;
    private static User userToView;

    /**
     * Don't allow instantiation
     */
    private UserManager() {

    }

    /**
     * @param userName username to check
     * @return whether the user already exists
     */
    public static boolean userExists(String userName) {
        for (final User user : users) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new user.
     *
     * @param userName the username of the new user
     * @param password the password for the new user
     * @throws UserExistsException
     */
    public static void createUser(String userName, String password)
            throws UserExistsException {
        // check that there is no other user with the same username
        if (userExists(userName)) {
            throw new UserExistsException("The username "
                    + userName + " is already taken.");
        } else {
            users.add(new User(userName, password));
        }
    }

    /**
     * Logs in a new user.
     *
     * @param userName the username of the user to log in
     * @param password the password for the user to log in
     */
    public static void logInUser(String userName, String password)
            throws UserDoesNotExistException, UserIsBannedException, UserIsLockedException {
        final User testingUser = new User(userName, password);
        // in case user hits back instead of logout button; ensure no user is
        // logged in
        loggedInUser = null;

        for (final User user : users) {
            if (user.equals(testingUser)) {
                loggedInUser = user;
            }
        }

        if (loggedInUser == null) {
            throw new UserDoesNotExistException(
                    "The given password and/or username is incorrect.");
        }

        if (loggedInUser.getStatus().equals(User.Status.BANNED)) {
            throw new UserIsBannedException(
                    "The given user is banned.");
        }

        if (loggedInUser.getStatus().equals(User.Status.LOCKED)) {
            throw new UserIsLockedException(
                    "The given user is locked.");
        }
    }

    /**
     * Logs out the current user.
     */
    public static void logOutUser() {
        loggedInUser = null;
    }

    /**
     * @param user sets the user whose profile will be viewed
     */
    public static void setUserToView(User user) {
        userToView = user;
    }

    /**
     * @return the user whose profile will be viewed
     */
    public static User getUserToView() {
        return userToView;
    }

    /**
     * @return the user that is currently logged
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * @param id the id of movie
     * @return all the ratings for the movie of the id with the logged-in
     * user's rating first
     */
    public static List<Rating> getRatingsForID(String id) {
        final List<Rating> ratings = new ArrayList<>();
        // add the rating of the logged in user first
        try {
            final Rating rating = loggedInUser.geRatingForID(id);
            ratings.add(rating);
        } catch (User.UserHasNotRatedMovieException e) {
            // just don't add anything
        }
        for (final User user : users) {
            if (!user.equals(loggedInUser)) {
                try {
                    final Rating rating = user.geRatingForID(id);
                    ratings.add(rating);
                } catch (User.UserHasNotRatedMovieException e) {
                    // just don't add anything
                }
            }
        }
        return ratings;
    }

    /**
     * @param userName the new username for the logged-in user
     */
    public static void setUserName(String userName) {
        loggedInUser.setUserName(userName);
    }

    /**
     * @param password the new password for the logged-in user
     */
    public static void setPassword(String password) {
        loggedInUser.setPassword(password);
    }

    /**
     * @param status the new status for the logged-in user
     */
    public static void setStatus(User.Status status) {
        loggedInUser.setStatus(status);
    }

    /**
     * @param major the new major for the logged-in user
     */
    public static void setMajor(User.Major major) {
        loggedInUser.setMajor(major);
    }

    /**
     * @param interests the new interests of the logged-in user
     */
    public static void setInterests(String interests) {
        loggedInUser.setInterests(interests);
    }

    /**
     * @return the list of users
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * @param newUsers new list of users
     */
    public static void setUsers(List<User> newUsers) {
        users = newUsers;
    }

    // I don't think getters will be needed for the logged-in user
//    /**
//     * @return the logged-in user's name
//     */
//    public static String getUserName() {
//        return loggedInUser.getUserName();
//    }
//
//    /**
//     * @return the logged-in user's password
//     */
//    public static String getPassword() {
//        return loggedInUser.getPassword();
//    }
//
//    /**
//     * @return the logged-in user's status
//     */
//    public static User.Status getStatus() {
//        return loggedInUser.getStatus();
//    }
//
//    /**
//     * @return the logged-in user's major
//     */
//    public static User.Major getMajor() {
//        return loggedInUser.getMajor();
//    }
//
//    /**
//     * @return the logged-in user's interests
//     */
//    public static String getInterests() {
//        return loggedInUser.getInterests();
//    }


    /**
     * A checked exception for when a userName is taken.
     * Only thrown from UserManager.
     */
    public static class UserExistsException extends Exception {
        /**
         * @param s the error message
         */
        public UserExistsException(String s) {
            super(s);
        }
    }

    /**
     * A checked exception for when the given information does not match a
     * registered user. Only thrown from UserManager.
     */
    public static class UserDoesNotExistException extends Exception {
        /**
         * @param s the error message
         */
        public UserDoesNotExistException(String s) {
            super(s);
        }
    }

    /**
     * A checked exception for when the user is banned. Only thrown from UserManager.
     */
    public static class UserIsBannedException extends Exception {
        /**
         * @param s the error message
         */
        public UserIsBannedException(String s) {
            super(s);
        }
    }

    /**
     * A checked exception for when the user is locked. Only thrown from UserManager.
     */
    public static class UserIsLockedException extends Exception {
        /**
         * @param s the error message
         */
        public UserIsLockedException(String s) {
            super(s);
        }
    }
}
