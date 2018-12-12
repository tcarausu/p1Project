package dao;

import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
@SuppressWarnings("Duplicates")
public class QuestionDao implements QuestionDatabaseI {

    private Connection conn;

    /**
     * This constructor initiates the Driver and registers it
     *
     * @throws SQLException in case that there is no way to connect
     *                      it sets up the connection for the whole QuestionDao Class
     */
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

    /**
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @throws SQLException
     */
    @Override
    public void createNewQuestion(String subject,
                                  String typeOfQuestion, String difficultyLevel,
                                  String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "Insert into  p1Project.questions(subject," +
                    "typeOfQuestion,difficultylevel,region) values(?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, subject);
            st.setString(2, typeOfQuestion);
            st.setString(3, difficultyLevel.toLowerCase());
            st.setString(4, region);
            st.execute();
        } finally {
            conn.close();

        }


    }

    /**
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @return
     * @throws SQLException
     */
    @Override
    public boolean verifyIntroducedQuestion(String subject,
                                            String typeOfQuestion, String difficultyLevel,
                                            String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.questions where subject ='" + subject + "' " +
                    " AND typeOfQuestion ='" + typeOfQuestion + "'" +
                    " AND difficultylevel ='" + difficultyLevel + "'" +
                    " AND region ='" + region + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();

        } finally {
            conn.close();

        }

    }


    /**
     * @param difficulty
     * @param region
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT quest.subject FROM p1Project.questions as quest " +
                    "where quest.difficultylevel like '" + difficulty + "' " +
                    "and quest.region like '" + region + "'";

            List<String> result = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            int numCols = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int i = 1; i <= numCols; i++) {
                    // loop through the ArrayList and add results accordingly
                    result.add(rs.getString(i));
                }
            }
            return result;

        } finally {
            conn.close();

        }
    }

    /**
     * @param difficultyLevel
     * @param region
     * @param question
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> getAnQuestionAnswerList(String difficultyLevel, String region, String question) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT ans.givenanswer FROM p1Project.answer as ans " +
                    "join p1project.questions_answers as qAnsw on ans.id = qAnsw.answersid " +
                    "join p1project.questions as quest on quest.id = qAnsw.questionid " +
                    "where ans.difficultylevel like '" + difficultyLevel + "' and quest.subject = '" + question + "' " +
                    "and quest.region like '" + region + "' " +
                    "limit 4";


            List<String> result = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            int numCols = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int i = 1; i <= numCols; i++) {  // loop through the ArrayList and add results accordingly
                    result.add(rs.getString(i));
                }
            }
            return result;
        } finally {
            conn.close();

        }

    }

    /**
     * @param region
     * @return
     * @throws SQLException
     */
    @Override
    public String getRegion(String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT quest.region FROM p1Project.questions as quest " +
                    "where quest.region ='" + region.toLowerCase() + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<String> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getString("region")
                );
            }
            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return null;
        } finally {
            conn.close();

        }


    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteQuestionByIdFromQuestionAnswer(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {

            String SQL =
                    "delete from p1Project.questions_answers as qAnsw " +
                            "where qAnsw.questionid = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }

    }

    /**
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteQuestionById(int id) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {

            String SQL = "delete from p1Project.questions as quest " +
                    "where id = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }

    }

    /**
     * @param id
     * @param subject
     * @param typeOfQ
     * @param diffLevel
     * @param region
     * @throws SQLException
     */
    @Override
    public void updateQuestionById(int id, String subject, String typeOfQ,
                                   String diffLevel, String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "update p1project.questions " +
                    "set  subject  = '" + subject + "' ," +
                    "  typeofquestion  = '" + typeOfQ + "' ," +
                    "  difficultylevel  = '" + diffLevel + "' ," +
                    "  region  = '" + region + "' " +
                    "where id  = '" + id + "' ";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }

    }

}