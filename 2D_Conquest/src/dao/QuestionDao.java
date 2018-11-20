package dao;

import org.postgresql.Driver;

import java.sql.*;
import java.util.Collections;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public class QuestionDao implements QuestionDatabaseI {

    private Connection conn;

    public QuestionDao() {

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


    public void createNewQuestion(String subject, String correctAnswer,
                                  String typeOfQuestion, String difficultylevel,
                                  String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "Insert into  p1Project.questions(subject,correctAnswer," +
                    "typeOfQuestion,difficultylevel,region) values(?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, subject);
            st.setString(2, correctAnswer);
            st.setString(3, typeOfQuestion);
            st.setString(4, difficultylevel.toLowerCase());
            st.setString(5, region);
            st.execute();
        } finally {
            conn.close();

        }


    }

    @Override
    public boolean verifyIntroducedQuestion(String subject, String correctAnswer,
                                            String typeOfQuestion, String difficultylevel,
                                            String region
    ) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.questions where subject ='" + subject + "' " +
                    " AND correctanswer ='" + correctAnswer + "'"+
                    " AND typeOfQuestion ='" + typeOfQuestion + "'"+
                    " AND difficultylevel ='" + difficultylevel + "'"+
                    " AND region ='" + region + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();

        } finally {
            conn.close();

        }

    }

    @Override
    public List getAll() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.questions ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            List<ResultSet> resultSets = Collections.singletonList(rs);
            return resultSets;

        } finally {
            conn.close();

        }
    }

}
