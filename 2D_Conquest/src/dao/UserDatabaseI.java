package dao;

import java.sql.SQLException;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface UserDatabaseI {
    public void createNewUser(String username, String password) throws SQLException;
}
