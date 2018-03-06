package com.example.alexa.finalproject;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    // Alexa Carleo's method test
    @BeforeClass
    public static void setup() throws Exception {
        // executed only once before the first test
        UserManager.createUser("Alexa", "password");
        UserManager.createUser("Anna", "password");
        UserManager.createUser("Ryan", "password");
        UserManager.createUser("Paul", "password");
        UserManager.createUser("Larry", "password");
    }

    @Test
    public void firstUserExists() throws Exception {

        //checking fist user in list
        //should be true, because the userName "Alexa" exists
        assertTrue(UserManager.userExists("Alexa"));

    }

    @Test
    public void userDoesNotExists() throws Exception {

        //should be false, because usernames are case-sensitive
        assertFalse(UserManager.userExists("alexa"));

    }

    @Test
    public void middleUserExists() throws Exception {

        //checking user in middle of list
        //should be true
        assertTrue(UserManager.userExists("Ryan"));
    }

    @Test
    public void lastUserExists() throws Exception {

        //checking last user in list
        //should be true
        assertTrue(UserManager.userExists("Larry"));
    }
}