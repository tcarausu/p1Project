package dao;

import java.sql.SQLException;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface UserDatabaseI {
    void createNewUser(String username, String password) throws SQLException;

    void deleteUser(int id) throws SQLException;

    void updateUser(int id, String username, String password) throws SQLException;

    void resetPasswordForUserById(int id) throws SQLException;
}
