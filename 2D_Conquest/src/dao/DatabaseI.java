package dao;

import java.sql.SQLException;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public interface DatabaseI {

    boolean verifyUserLogin(String username, String password) throws SQLException;

    boolean verifyAdminLogin(String username, String password) throws SQLException;

    void startQuiz(String username, int total, String difficultyLevel) throws SQLException;

    int getNrOfQAnsweredFromCurrQuiz(int id, String difficultyLevel, int score) throws SQLException;

    int getNumberOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException;

    boolean checkHighScoreData(String username, int total, String difficultyLevel) throws SQLException;

    int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException;

    void updateScoreOnDifficultyForUser(int id,int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultyLevel) throws SQLException;

    void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int score, int timeSpent) throws SQLException;

    boolean checkValidityOfAnswerForAQuestion(String answer) throws SQLException;

    int timeSpent(int id, int score, String difficultyLevel) throws SQLException;

    int getHighScoreId() throws SQLException;
}
