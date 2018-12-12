package dao;

import java.sql.SQLException;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public interface DatabaseI {

    /**
     * @param username
     * @param password
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean verifyUserLogin(String username, String password) throws SQLException;

    /**
     * @param username
     * @param password
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean verifyAdminLogin(String username, String password) throws SQLException;

    /**
     * @param username
     * @param total
     * @param difficultyLevel
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void startQuiz(String username, int total, String difficultyLevel) throws SQLException;

    /**
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int getNrOfQAnsweredFromCurrQuiz(int id, String difficultyLevel, int score) throws SQLException;

    /**
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int getNumberOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException;

    /**
     * @param id
     * @param difficultyLevel
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException;

    /**
     * @param id
     * @param currentNrOfQuestionsAnswered
     * @param totalScore
     * @param userName
     * @param difficultyLevel
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void updateScoreOnDifficultyForUser(int id, int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultyLevel) throws SQLException;

    /**
     * @param id
     * @param nrOfQAnswered
     * @param score
     * @param timeSpent
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int score, int timeSpent) throws SQLException;

    /**
     * @param answer
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean checkValidityOfAnswerForAQuestion(String answer) throws SQLException;

    /**
     * @param id
     * @param score
     * @param difficultyLevel
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int timeSpent(int id, int score, String difficultyLevel) throws SQLException;

    /**
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int getHighScoreId() throws SQLException;
}
