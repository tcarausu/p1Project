package dao;

import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public class Database implements DatabaseI {
    private Connection conn;

    /**
     * This constructor initiates the Driver and registers it
     * <p>
     * throws SQLException in case that there is no way to connect
     * it sets up the connection for the whole Database Dao Class
     */
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

    /**
     * This method checks the database for an user with
     * use of a select query by using these parameters
     *
     * @param username representing the username that is supposed
     *                 to be verified in the user table
     * @param password representing the username that is supposed
     *                 to be verified in the user table
     *                 will return
     * @return true if there is such an entry in the user table
     * and false if there is no user in the user table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
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

    /**
     * This method checks the database for an admin with
     * use of a select query by using these parameters
     *
     * @param username representing the username that is supposed
     *                 to be verified in the admin table
     * @param password representing the username that is supposed
     *                 to be verified in the admin table
     *                 will return
     * @return true if there is such an entry in the admin table
     * and false if there is no admin in the admin table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
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

    /**
     * This method initialization does create a quiz by
     * inserting this parameters and
     * sets an incremental id for each of the entries for the quizzes
     *
     * @param username        representing the username that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @param total           representing the total that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void startQuiz(String username, int total, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "Insert into  p1Project.highscore(id,usernameofplayer,timespent," +
                    "nrofquestionsanswered,nrofquestionstotal,difficultylevel,score) values(default,?,0,0,?,?,0)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, username);
            st.setInt(2, total);
            st.setString(3, difficultyLevel);

            st.execute();

        } finally {
            conn.close();

        }

    }

    /**
     * This method does provide the time spent on a quiz by it's Id
     * by using a select query
     *
     * @param id              representing the id that is supposed
     *                        to be the highScore/quiz identifier
     * @param score           representing the score that is supposed
     *                        to be the highScore/quiz current score
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be the highScore/quiz difficulty level
     * @return a specific time spent in a quiz by using it's Id, difficulty level
     * and score for the current quiz (for the last entry)
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public int timeSpent(int id, int score, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.timespent FROM p1Project.highscore as score " +
                    "where score.difficultylevel ='" + difficultyLevel + "'"
                    + " AND score.id ='" + id + "'"
                    + " AND score.score ='" + score + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("timespent")
                );
            }
            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return -1;

        } finally {
            conn.close();

        }
    }

    /**
     * This method returns the last id from the high score table
     * we used here an "order by" due to it being buggy,
     * the application wasn't consistent, it was looking at the last row,
     * not the last entry of the database
     *
     * @return the highScore id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public int getHighScoreId() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.id FROM p1Project.highscore as score " +
                    "order by score.id";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("id")
                );
            }
            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return -1;

        } finally {
            conn.close();

        }
    }

    /**
     * This method does provide a specific number of questions answered in a quiz by it's Id
     *
     * @param id              representing the id that is supposed
     *                        to be the highScore/quiz identifier
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be the highScore/quiz difficulty level
     * @param score           representing the score that is supposed
     *                        to be the highScore/quiz score
     * @return a specific number of question in a quiz by it's Id, difficulty level and score
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public int getNrOfQAnsweredFromCurrQuiz(int id, String difficultyLevel, int score) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.nrofquestionsanswered FROM p1Project.highscore as score " +
                    "where id ='" + id + "'"
                    + " AND difficultylevel ='" + difficultyLevel + "'"
                    + " AND score.score =" + score + "";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("nrofquestionsanswered"));
            }

            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return -1;

        } finally {
            conn.close();

        }

    }

    /**
     * This method does provide a specific number of questions total in a quiz by it's Id
     *
     * @param id              representing the id that is supposed
     *                        to be the highScore/quiz identifier
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be the highScore/quiz difficulty level
     * @param score           representing the score that is supposed
     *                        to be the highScore/quiz score
     * @return a specific number of question in a quiz by it's Id, difficulty level and score
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public int getNumberOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.nrofquestionstotal FROM p1Project.highscore as score " +
                    "where  id ='" + id + "'"
                    + " AND difficultylevel ='" + difficultyLevel + "'"
                    + " AND score ='" + score + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("nrofquestionstotal")
                );
            }
            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return -1;

        } finally {
            conn.close();

        }

    }

    /**
     * This method does provide the highScore on an user in a quiz by it's Id
     *
     * @param id              representing the id that is supposed
     *                        to be the highScore/quiz identifier
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be the highScore/quiz difficulty level
     * @return a specific score in a quiz by using it's Id, difficulty level
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.score FROM p1Project.highscore as score " +
                    "where difficultylevel ='" + difficultyLevel + "'"
                    + " AND id ='" + id + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("score")
                );
            }
            if (arr.size() > 0)
                return arr.get(arr.size() - 1);
            else
                return -1;

        } finally {
            conn.close();

        }

    }

    /**
     * This method does check the validity of an answer for a specific question
     * it also validates null and "" (no data introduced)
     * so that we could an appropriate result
     *
     * @param answer representing the answer that is supposed
     *               to be the question identifier
     * @return true if there is such an entry in the user table
     * and false if there is no user in the user table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public boolean checkValidityOfAnswerForAQuestion(String answer) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT qAnsw.validityofanswer FROM p1Project.questions_answers as qAnsw " +
                    "join p1project.answer as answ on answ.id = qAnsw.answersid " +
                    "join p1project.questions as quest on quest.id = qAnsw.questionid " +
                    "where answ.givenanswer ='" + answer + "'";
            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();

            if (answer != null && !answer.equals("")) {
                ArrayList<Boolean> arr = new ArrayList<>();
                while (rs.next()) {
                    arr.add(rs.getBoolean("validityofanswer")
                    );
                }
                return arr.get(0);
            } else {
                return false;
            }

        } finally {
            conn.close();

        }

    }

    /**
     * This method does update the score on a specific difficulty level by using quiz/highScore Id for an user
     * using these parameters
     *
     * @param id                           representing the id that is supposed
     *                                     to be the highScore/quiz identifier
     * @param currentNrOfQuestionsAnswered representing the currNrOfQuestAnswered on the
     *                                     current quiz by Id
     * @param totalScore                   representing the totalScore on the
     *                                     current quiz by Id
     * @param userName                     representing the userName that is supposed
     *                                     current quiz's player
     * @param difficultyLevel              representing the difficultyLevel that is supposed
     *                                     current quiz by Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void updateScoreOnDifficultyForUser(int id, int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "update p1Project.highscore "
                    + " set nrofquestionsanswered= '" + currentNrOfQuestionsAnswered + "' ,"
                    + " score= '" + totalScore + "' " +
                    "where usernameofplayer = '" + userName + "'" +
                    "and difficultylevel = '" + difficultyLevel + "'";


            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();

        } finally {
            conn.close();

        }

    }

    /**
     * This method does update the number of Questions answered on a specific quiz/highScore by
     * quiz's Id using these parameters
     *
     * @param id            representing the id that is supposed
     *                      to be the highScore/quiz identifier
     * @param nrOfQAnswered representing the nrOfQAnswered on the
     *                      current quiz by Id
     * @param score         representing the score on the
     *                      current quiz by Id
     * @param timeSpent     representing the timeSpent that is supposed
     *                      current quiz by Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @Override
    public void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int score, int timeSpent) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "update p1project.highscore " +
                    "set  nrofquestionsanswered  = '" + nrOfQAnswered + "' ,"
                    + " score ='" + score + "' ," +
                    " timespent ='" + timeSpent + "' " +
                    "where id ='" + id + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }

    }

    /**
     * This method gather the ResultSet for the whole Question table
     * containing all the questions
     *
     * @return the specific ResultSet from Question table has gathered for further use
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public ResultSet getQuestions() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.questions "+
                "order by id";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * This method gather the ResultSet for the whole HighScore table
     * containing all the highScore entries
     *
     * @return the specific ResultSet from HighScore table has gathered for further use
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public ResultSet getHighScore() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT usernameofplayer,timespent,nrofquestionsanswered,nrofquestionstotal," +
                "difficultylevel,score FROM p1Project.highscore "+
                "order by id";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * This method gather the ResultSet for the whole HighScore table
     * on a specific user
     *
     * @param username represents the username of the current user of this session
     * @return the specific ResultSet from HighScore table has gathered for further use
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public ResultSet getHighScoreOnUser(String username) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT usernameofplayer,timespent,nrofquestionsanswered,nrofquestionstotal," +
                "difficultylevel,score FROM p1Project.highscore " +
                "where usernameofplayer = '" + username + "'"+
                "order by id";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * This method gather the ResultSet for the whole User table
     * containing all the users
     *
     * @return the specific ResultSet from User table has gathered for further use
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public ResultSet getUsers() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.user "+
                "order by id";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

}
