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
     *
     * @throws SQLException in case that there is no way to connect
     *                      it sets up the connection for the whole Database Dao Class
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
     * @param username
     * @param password
     * @return
     * @throws SQLException
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
     * @param username
     * @param password
     * @return
     * @throws SQLException
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
     * @param answer
     * @return
     * @throws SQLException
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
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException
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
     * @param id
     * @param nrOfQAnswered
     * @param score
     * @param timeSpent
     * @throws SQLException
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
     * @param id
     * @param currentNrOfQuestionsAnswered
     * @param totalScore
     * @param userName
     * @param difficultyLevel
     * @throws SQLException
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
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException
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
     * @param id
     * @param difficultyLevel
     * @return
     * @throws SQLException
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
     * @param username
     * @param total
     * @param difficultyLevel
     * @throws SQLException
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
     * @param id
     * @param score
     * @param difficultyLevel
     * @return
     * @throws SQLException
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
     * @return
     * @throws SQLException
     */
    @Override
    public int getHighScoreId() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.id FROM p1Project.highscore as score "+
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
     * @return
     * @throws SQLException
     */
    public ResultSet getQuestionsData() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.questions";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * @return
     * @throws SQLException
     */
    public ResultSet getHighScore() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT usernameofplayer,timespent,nrofquestionsanswered,nrofquestionstotal," +
                "difficultylevel,score FROM p1Project.highscore ";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * @param username
     * @return
     * @throws SQLException
     */
    public ResultSet getHighScoreOnUser(String username) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT usernameofplayer,timespent,nrofquestionsanswered,nrofquestionstotal," +
                "difficultylevel,score FROM p1Project.highscore " +
                "where usernameofplayer = '" + username + "'";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    /**
     * @return
     * @throws SQLException
     */
    public ResultSet getUsers() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.user ";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

}
