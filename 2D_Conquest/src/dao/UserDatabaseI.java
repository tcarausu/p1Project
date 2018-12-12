package dao;

import java.sql.SQLException;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface UserDatabaseI {
    /**
     * This method creates an user by using
     *
     * @param username representing the username introduced by the admin
     * @param password representing the password introduced by the admin
     *                 as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void createNewUser(String username, String password) throws SQLException;

    /**
     * This method deletes an user by using
     *
     * @param id representing the id introduced by the admin
     *           as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void deleteUser(int id) throws SQLException;

    /**
     * This method updates an user by using
     *
     * @param id       representing the id introduced by the admin
     *                 as catalyst to update
     * @param username representing the username introduced by the admin
     * @param password representing the password introduced by the admin
     *                 as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void updateUser(int id, String username, String password) throws SQLException;

    /**
     * This method resets an user password by using
     *
     * @param id representing the id introduced by the admin
     *           as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void resetPasswordForUserById(int id) throws SQLException;
}
