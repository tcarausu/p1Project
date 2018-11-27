package dao;

import java.sql.SQLException;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public interface DatabaseI {

    boolean verifyUserLogin(String username, String password) throws SQLException;

    boolean verifyAdminLogin(String username, String password) throws SQLException;

    void startQuiz(String username, int total, String difficultylevel) throws SQLException;

    int getNumberOfQuestionsAnsweredFromCurrentQuiz(String username, String difficultylevel, int score) throws SQLException;

    int getNumberOfQuestionsTotalFromCurrentQuiz(String username, String difficultylevel, int score) throws SQLException;

    boolean checkHighscoreData(String username, int total, String difficultylevel) throws SQLException;

    int getHighScoreOnUserWithDifficultyLevel(String username, String difficultyLevel) throws SQLException;

    void updateScoreOnDifficultyForUser(int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultylevel) throws SQLException;

    void updateNrfQuestionsAnswFromCurrentQuiz(int nrOfQAnswered, String username, int score) throws SQLException;
}
