package dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public class UserDao implements UserDatabaseI {

    private Connection conn;

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

    @Override
    public void updateUser(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "update p1project.user " +
                    "set  password  = '"+ "' "+
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }


    }

    @Override
    public void resetPasswordForUserById(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "update p1project.user " +
                    "set  password  = 'password' "+
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }


    }


}
