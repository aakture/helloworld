package com.dao;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by aakture on 9/17/15.
 */
public class UserDaoTest {

    @Test
    public void testCreateUser() throws Exception {
        UserDao userDao = new UserDao();
        userDao.createUser("Alper", "Akture");
        String user = userDao.getUserByLastName("Akture");
        assertEquals("names did not match", user, "Alper Akture");
        userDao.deleteUser("Akture");
        user = userDao.getUserByLastName("Akture");
        assertNull(user);

    }
}
