package dao;

import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
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
                    " AND correctanswer ='" + correctAnswer + "'" +
                    " AND typeOfQuestion ='" + typeOfQuestion + "'" +
                    " AND difficultylevel ='" + difficultylevel + "'" +
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
    public String getAnEasyQuestion() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.questions " +
                    "where difficultylevel like 'easy'" +
                    "ORDER BY random()" +
                    "LIMIT 1";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<String> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add("The Question is : "
                        + rs.getString("subject")
                );
            }
            return arr.get(0);

        } finally {
            conn.close();

        }

    }

    @Override
    public String getAnEasyQuestionCorrectAnswer() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT quest.correctanswer FROM p1Project.questions as quest " +
                    "join p1project.questions_answers as qAnsw on quest.correctanswer = qAnsw.correctanswer " +
                    "where quest.difficultylevel like 'easy' and  " +
                    "qAnsw.correctanswer = quest.correctanswer " +
                    "ORDER BY random()" +
                    "LIMIT 1";


            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<String> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getString("correctAnswer")
                );
            }
            return arr.get(0);

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
            return Collections.singletonList(rs);

        } finally {
            conn.close();

        }
    }

}
