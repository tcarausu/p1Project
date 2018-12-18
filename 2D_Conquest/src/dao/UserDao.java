package dao;

import org.postgresql.Driver;

import java.sql.*;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public class UserDao implements UserDatabaseI {

    private Connection conn;

    /**
     * This constructor initiates the Driver and registers it
     * <p>
     * throws SQLException in case that there is no way to connect
     * it sets up the connection for the whole UserDao Class
     */
    public UserDao() {

        Driver driver = new Driver();
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method creates an user by using the insert
     *
     * @param username representing the username introduced by the admin
     * @param password representing the password introduced by the admin
     *                 as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void createNewUser(String username, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "Insert into  p1Project.user(username,password) values(?,?)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, username);
            st.setString(2, password);
            st.execute();
        } finally {
            conn.close();

        }

    }

    /**
     * This method deletes an user by using the delete query
     *
     * @param id representing the id introduced by the admin
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void deleteUser(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "delete from p1Project.user " +
                    "where id = '" + id + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }


    }

    /**
     * This method updates an user by using update query
     *
     * @param id       representing the id introduced by the admin
     *                 as catalyst to update
     * @param username representing the username introduced by the admin
     * @param password representing the password introduced by the admin
     *                 as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void updateUser(int id, String username, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "update p1project.user " +
                    "set  username  = '" + username + "' ," +
                    "password  = '" + password + "' " +
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }


    }

    /**
     * This method resets an user password by using update query using
     *
     * @param id representing the id introduced by the admin
     *           as parameters and setting the password to 'password" for initial value
     *           it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void resetPasswordForUserById(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "update p1project.user " +
                    "set  password  = 'password' " +
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }


    }

    /**
     * This method resets an user password by using update query using
     *
     * @param id representing the id introduced by the admin
     *           as parameters and setting the password to 'password" for initial value
     *           it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public boolean checkUserIdValidity(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "SELECT * FROM p1Project.user " +
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();
        } finally {
            conn.close();

        }
    }
}
