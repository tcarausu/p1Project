package dao;

import org.postgresql.Driver;

import java.sql.*;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public class Database implements DatabaseI {
    private static Connection conn;

    public Database() {

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
    public boolean verifyUserLogin(String username, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.user where username ='" + username + "'  AND password ='" + password + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();

        } finally {
            conn.close();

        }

    }

    public boolean verifyAdminLogin(String username, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.admin where username ='" + username + "'  AND password ='" + password + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();

        } finally {
            conn.close();

        }

    }

    public static ResultSet getData() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.questions";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
}
