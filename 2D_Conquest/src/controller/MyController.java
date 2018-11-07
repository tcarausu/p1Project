package controller;

import java.sql.*;

public class MyController {
    private String username;
    private String password;

    public MyController(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public MyController(){

    }
    public void jdbcConnectForLogin(String username, String password) {
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {
//            System.out.println("Java JDBC PostgreSQL Example");
//            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
//            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//            //          Class.forName("org.postgresql.Driver");
//            System.out.println("Connected to PostgreSQL database!");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT su." + username + ",su." + password +
//                    "FROM t_user su where su.username LIKE 'first%'");
//            while (resultSet.next()) {
//                System.out.printf(resultSet.getString("username"),
//                        resultSet.getString("password"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Connection failure.");
//            e.printStackTrace();
//        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres")) {
            System.out.println("Java JDBC PostgreSQL Example");
            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT su.username,su.password "+
                    "FROM t_user su where su.username LIKE 'first%'");
            while (resultSet.next()) {
                System.out.printf(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

}
