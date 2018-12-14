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
    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultyLevel,
                           String region) throws SQLException;

    /**
     * This method verifies an question that user tries to add by using
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
    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultyLevel,
                                     String region) throws SQLException;

    /**
     * This method updates a question by using
     * the id as a focal point
     *
     * @param id        representing the id of said question
     * @param subject   representing the subject of said question
     * @param typeOfQ   representing the typeOfQ of said question
     * @param diffLevel representing the diffLevel of said question
     * @param region    representing the region of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void updateQuestionById(int id, String subject, String typeOfQ,
                            String diffLevel, String region) throws SQLException;

    /**
     * This method deletes a question by using
     * the id as a focal point
     *
     * @param id representing the id of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void deleteQuestionById(int id) throws SQLException;

    /**
     * This method deletes a question_answer entry by using
     * it's question id as a focal point
     *
     * @param id representing the id of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void deleteQuestionAnswerByQuestionIdFromQuestionAnswer(int id) throws SQLException;

    /**
     * This method extracts a region from the Question table by using
     *
     * @param region representing the region of said question
     * @return a region from the question table
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    String getRegion(String region) throws SQLException;

    /**
     * This method extracts a list of questions subjects that by using
     *
     * @param difficulty representing the difficultyLevel of said question
     * @param region     representing the region of said question
     *                   as parameters it can
     * @return a list of questions by the selected difficulty level and region
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException;

    /**
     * This method extracts a list of Answers specific for a question that by using
     * <p>
     * The number of answer is going to be limited to 4
     *
     * @param difficultyLevel representing the difficultyLevel of said question
     * @param region          representing the region of said question
     *                        as parameters it can
     * @param question        representing the question that the list is supposed to gain
     *                        its results from
     * @return a list of answers from a question by the selected difficulty level and region of that question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    List<String> getAnQuestionAnswerList(String difficultyLevel, String region, String question) throws SQLException;

}
