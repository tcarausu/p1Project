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

    @Override
    public void createNewQuestion(String subject,
                                  String typeOfQuestion, String difficultylevel,
                                  String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "Insert into  p1Project.questions(subject," +
                    "typeOfQuestion,difficultylevel,region) values(?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, subject);
            st.setString(2, typeOfQuestion);
            st.setString(3, difficultylevel.toLowerCase());
            st.setString(4, region);
            st.execute();
        } finally {
            conn.close();

        }


    }

    @Override
    public boolean verifyIntroducedQuestion(String subject,
                                            String typeOfQuestion, String difficultylevel,
                                            String region
    ) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.questions where subject ='" + subject + "' " +
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
    public String getAnEasyQuestionCorrectAnswer(
    ) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT ans.givenanswer FROM p1Project.answer as ans " +
                    "join p1project.questions_answers as qAnsw on ans.id = qAnsw.answersid " +
                    "join p1project.questions as quest on quest.id = qAnsw.questionsid " +
                    "where ans.difficultylevel like 'easy' and qAnsw.valityofanswer = true " +
                    "ORDER BY random()" +
                    "LIMIT 1";


            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<String> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getString("givenanswer")
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
