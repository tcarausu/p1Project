package dao;

import java.sql.SQLException;

/**
 * File created on 11/8/2018
 * by Toader
 **/
public interface DatabaseI {

    /**
     * This method checks the database for an user with
     *
     * @param username representing the username that is supposed
     *                 to be verified in the user table
     * @param password representing the username that is supposed
     *                 to be verified in the user table
     * @return true if there is such an entry in the user table
     * and false if there is no user in the user table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean verifyUserLogin(String username, String password) throws SQLException;

    /**
     * This method checks the database for an admin with
     *
     * @param username representing the username that is supposed
     *                 to be verified in the admin table
     * @param password representing the username that is supposed
     *                 to be verified in the admin table
     * @return true if there is such an entry in the admin table
     * and false if there is no user in the admin table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean verifyAdminLogin(String username, String password) throws SQLException;

    /**
     * This method initialization does create a quiz using
     *
     * @param username        representing the username that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @param total           representing the total Score that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be set up for the HighScore table/our quiz registry
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void startQuiz(String username, int total, String difficultyLevel) throws SQLException;

    /**
     * This method does provide the time spent on a quiz by it's Id
     *
     * @param id              representing the id that is supposed
     *                        to be the highScore/quiz identifier
     * @param score           representing the score that is supposed
     *                        to be the highScore/quiz current score
     * @param difficultyLevel representing the difficultyLevel that is supposed
     *                        to be the highScore/quiz difficulty level
     * @return a specific time spent in a quiz by using it's Id, difficulty level
     * and score for the current quiz
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int timeSpent(int id, int score, String difficultyLevel) throws SQLException;

    /**
     * This method does provide current highScore id
     *
     * @return the highScore id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    int getHighScoreId() throws SQLException;

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
    int getNrOfQAnsweredFromCurrQuiz(int id, String difficultyLevel, int score) throws SQLException;

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
    int getNumberOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException;

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
    int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException;

    /**
     * This method does check the validity of an answer for a specific question
     *
     * @param answer representing the answer that is supposed
     *               to be the question identifier
     * @return true if there is such an entry in the user table
     * and false if there is no user in the user table with such parameters
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean checkValidityOfAnswerForAQuestion(String answer) throws SQLException;

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
    void updateScoreOnDifficultyForUser(int id, int currentNrOfQuestionsAnswered, int totalScore, String userName, String difficultyLevel) throws SQLException;

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
    void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int score, int timeSpent) throws SQLException;

}
