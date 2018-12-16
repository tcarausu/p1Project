package dao;

import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public class QuestionDao implements QuestionDatabaseI {

    private Connection conn;

    /**
     * This constructor initiates the Driver and registers it
     * <p>
     * throws SQLException in case that there is no way to connect
     * it sets up the connection for the whole QuestionDao Class
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
     * This method creates an question by using insert query
     * it will set the id for an incremental default(of type serial in database/int)
     *
     * @param subject         representing the subject introduced by the admin
     * @param typeOfQuestion  representing the typeOfQuestion introduced by the admin
     * @param difficultyLevel representing the difficultyLevel introduced by the admin
     * @param region          representing the region introduced by the admin
     *                        as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void createNewQuestion(String subject,
                                  String typeOfQuestion, String difficultyLevel,
                                  String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        try {
            String SQL = "Insert into  p1Project.questions(id,subject," +
                    "typeOfQuestion,difficultylevel,region) values(default,?,?,?,?)";

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
     * This method verifies an question that user tries to add by using
     * and select query to check for each of the parameters to be present in the database
     *
     * @param subject         representing the subject introduced by the admin
     * @param typeOfQuestion  representing the typeOfQuestion introduced by the admin
     * @param difficultyLevel representing the difficultyLevel introduced by the admin
     * @param region          representing the region introduced by the admin
     *                        as parameters it can
     * @return true if the question is already in the database
     * and false if there is no such entry
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
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
     * This method updates a question by using
     * and update query to change/adjust
     * by using the id as a focal point
     *
     * @param id        representing the id of said question
     * @param subject   representing the subject of said question
     * @param typeOfQ   representing the typeOfQ of said question
     * @param diffLevel representing the diffLevel of said question
     * @param region    representing the region of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
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

    /**
     * This method deletes a question by using
     * delete query with
     * the id as a focal point
     *
     * @param id representing the id of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
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
     * This method deletes a question_answer entry by using
     * delete query with
     * it's question id as a focal point
     *
     * @param id representing the id of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void deleteQuestionAnswerByQuestionIdFromQuestionAnswer(int id) throws SQLException {
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
     * This method extracts a region  from the Question table by using
     * a select query with
     *
     * @param region representing the region of said question
     * @return a region from the question table
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public String getRegion(String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT quest.region FROM p1Project.questions as quest " +
                    "where quest.region ='" + region + "'";

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
     * This method extracts a list of questions subjects that by using
     * an select query to check for each of the parameters to be present in the database
     * thus creating a list of subjects for the specific
     *
     * @param difficulty representing the difficultyLevel of said question
     * @param region     representing the region of said question
     *                   as parameters it can
     * @return a list of questions subjects
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT quest.subject FROM p1Project.questions as quest " +
                    "where quest.difficultylevel like '" + difficulty + "' " +
                    "and quest.region like '" + region + "'";

            return getStrings(SQL);

        } finally {
            conn.close();

        }
    }

    /**
     * This method extracts a list of Answers specific for a question that by using
     * an select query to check for each of the parameters to be present in the database
     * thus creating a list of answers for the specific
     * <p>
     * The number of answer is going to be limited to 4
     *
     * @param question        representing the question that the list is supposed to gain
     *                        its results from
     * @param difficultyLevel representing the difficultyLevel of said question
     * @param region          representing the region of said question
     *                        as parameters it can
     * @return a list of Answers specific for a question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
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


            return getStrings(SQL);
        } finally {
            conn.close();

        }

    }

    private List<String> getStrings(String SQL) throws SQLException {
        List<String> result = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement(SQL);
        st.execute();
        ResultSet rs = st.getResultSet();
        int numCols = rs.getMetaData().getColumnCount();

        while (rs.next()) {

            for (int i = 1; i <= numCols; i++) {
                result.add(rs.getString(i));
            }
        }
        return result;
    }

}