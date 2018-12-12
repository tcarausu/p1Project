package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface QuestionDatabaseI {
    /**
     * This method creates an question by using
     *
     * @param subject         representing the username introduced by the admin
     * @param typeOfQuestion  representing the username introduced by the admin
     * @param difficultyLevel representing the username introduced by the admin
     * @param region          representing the username introduced by the admin
     *                        as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     *                      there is an issue extracting data from the database
     */
    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultyLevel,
                           String region) throws SQLException;

    /**
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultyLevel,
                                     String region) throws SQLException;

    /**
     * @param difficultyLevel
     * @param region
     * @param question
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    List<String> getAnQuestionAnswerList(String difficultyLevel, String region, String question) throws SQLException;

    /**
     * @param difficulty
     * @param region
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException;

    /**
     * @param region
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    String getRegion(String region) throws SQLException;

    /**
     * @param id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void deleteQuestionById(int id) throws SQLException;

    /**
     * @param id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void deleteQuestionByIdFromQuestionAnswer(int id) throws SQLException;

    /**
     * @param id
     * @param subject
     * @param typeOfQ
     * @param diffLevel
     * @param region
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void updateQuestionById(int id, String subject, String typeOfQ,
                            String diffLevel, String region) throws SQLException;

}
