package com.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by aakture on 9/17/15.
 */
public class UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDao.class);

    public void createUser(String firstName, String lastName) {

        Connection conn = null;
        try {
            conn = ConnectionPool.INSTANCE.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("insert into user (first_name, last_name) values ('" + firstName + "', '" + lastName + "')");

        }catch(Exception ex) {
            log.error("error in createUser", ex);
        }finally {
            if(conn != null) {
                try {
                    conn.close();
                }catch (Exception ex) {
                    log.error("error closing connection", ex);
                }
            }
        }
    }

    public void deleteUser(String lastName) {

        Connection conn = null;
        try {
            conn = ConnectionPool.INSTANCE.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("delete from user where last_name = '" + lastName + "'");

        }catch(Exception ex) {
            log.error("error in deleteUser", ex);
        }finally {
            if(conn != null) {
                try {
                    conn.close();
                }catch (Exception ex) {
                    log.error("error closing connection", ex);
                }
            }
        }
    }

    public String getUserByLastName(String lastName) {

        String user = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.INSTANCE.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery("select first_name, last_name from user where last_name = '" + lastName + "'");
            while(rs.next()) {
                user = rs.getString("first_name") + " " + rs.getString("last_name");
            }
        }catch(Exception ex) {
            log.error("error in createUser", ex);
        }finally {
            if(statement != null) {
                try {
                    statement.close();
                }catch (Exception ex) {
                    log.error("error closing statement", ex);
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                }catch (Exception ex) {
                    log.error("error closing result set", ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                }catch (Exception ex) {
                    log.error("error closing connection", ex);
                }
            }
        }
        return user;
    }


}
