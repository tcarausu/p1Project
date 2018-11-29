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

    @Override
    public boolean checkHighScoreData(String username, int total, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT * FROM p1Project.highscore as score " +
                    "where usernameofplayer ='" + username + "'"
                    + " AND difficultylevel ='" + difficultyLevel + "'"
                    + " AND nrofquestionstotal ='" + total + "'";
            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            return rs.next();

        } finally {
            conn.close();

        }

    }

    @Override
    public boolean checkValidityOfAnswerForAQuestion(String answer) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT qAnsw.valityofanswer FROM p1Project.questions_answers as qAnsw " +
                    "join p1project.answer as answ on answ.id = qAnsw.answersid " +
                    "join p1project.questions as quest on quest.id = qAnsw.questionsid " +
                    "where answ.givenanswer ='" + answer + "'";
            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();

            if (answer != null && !answer.equals("")) {
                ArrayList<Boolean> arr = new ArrayList<>();
                while (rs.next()) {
                    arr.add(rs.getBoolean("valityofanswer")
                    );
                }
                return arr.get(0);
            }else{
                return false;
            }

        } finally {
            conn.close();

        }

    }

    @Override
    public int getNumberOfQuestionsAnsweredFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.nrofquestionsanswered FROM p1Project.highscore as score " +
                    "where usernameofplayer ='" + username + "'"
                    + " AND difficultylevel ='" + difficultyLevel + "'"
                    + " AND score ='" + score + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("nrofquestionsanswered")
                );
            }
            return arr.get(0);
        } finally {
            conn.close();

        }

    }

    @Override
    public void updateNrfQuestionsAnswerFromCurrentQuiz(int nrOfQAnswered, String username, int score) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "update p1project.highscore " +
                    "set  nrofquestionsanswered  = '" + nrOfQAnswered + "' ,"
                    + " score ='" + score + "' " +
                    "where usernameofplayer ='" + username + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
        } finally {
            conn.close();

        }

    }

    @Override
    public void updateScoreOnDifficultyForUser(int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultyLevel) throws SQLException {
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

    @Override
    public int getNumberOfQuestionsTotalFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.nrofquestionstotal FROM p1Project.highscore as score " +
                    "where usernameofplayer ='" + username + "'"
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
            return arr.get(0);

        } finally {
            conn.close();

        }

    }

    @Override
    public int getHighScoreOnUserWithDifficultyLevel(String username, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "SELECT score.score FROM p1Project.highscore as score " +
                    "where score.usernameofplayer ='" + username + "'"
                    + " AND difficultylevel ='" + difficultyLevel + "'";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.execute();
            ResultSet rs = st.getResultSet();
            ArrayList<Integer> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getInt("score")
                );
            }
            return arr.get(0);

        } finally {
            conn.close();

        }

    }

    @Override
    public void startQuiz(String username, int total, String difficultyLevel) throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        try {
            String SQL = "Insert into  p1Project.highscore(usernameofplayer,timespent," +
                    "nrofquestionsanswered,nrofquestionstotal,difficultylevel,score) values(?,0,0,?,?,0)";

            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, username);
            st.setInt(2, total);
            st.setString(3, difficultyLevel);

            st.execute();

        } finally {
            conn.close();

        }

    }


    public ResultSet getQuestionsData() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.questions";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getHighscore() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        final String sql = "SELECT * FROM p1Project.highscore ";
        final PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
}
