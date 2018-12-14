package controller;

import dao.QuestionDatabaseI;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/22/2018
 * by Toader
 **/
public class QuestionController {

    private QuestionDatabaseI qdb;

    private MyController mainController;

    /**
     * This constructor initiates the QuestionController and initializes it
     * with a QuestionDatabaseI interface
     *
     * @param qdb is the QuestionDatabaseI interface needed to instantiate the constructor
     */
    public QuestionController(QuestionDatabaseI qdb) {
        this.qdb = qdb;
    }

    /**
     * This void method instantiates the Question controller with an MyController class
     *
     * @param mainController is the MyController class needed to
     *                       set the connection between the 2 controllers
     */
    public void setMainControllerQuestionController(MyController mainController) {
        this.mainController = mainController;
    }

    /**
     * This void method adds question to the Question Table
     * then after doing so it will display and Success panel
     * and afterwards it will open the ConfirmationUI window
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
                           String region) throws SQLException {
        qdb.createNewQuestion(subject,
                typeOfQuestion, difficultyLevel, region);
        mainController.dataAddedSuccess();
        mainController.confirmationUI();
    }

    /**
     * This method verifies an question that user tries to add by using
     * the appropriate parameters
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
                                     String region) throws SQLException {
        return qdb.verifyIntroducedQuestion(subject,
                typeOfQuestion, difficultyLevel, region);
    }

    /**
     * This method updates a question by using
     * and update query to change/adjust
     * by using the id as a focal point
     * and in case of success it will display the Confirmation UI  window
     *
     * @param id        representing the id of said question
     * @param subject   representing the subject of said question
     * @param typeOfQ   representing the typeOfQ of said question
     * @param diffLevel representing the diffLevel of said question
     * @param region    representing the region of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void updateQuestionById(int id, String subject, String typeOfQ,
                                   String diffLevel, String region) throws SQLException {
        qdb.updateQuestionById(id, subject, typeOfQ, diffLevel, region);
        mainController.confirmationUI();

    }

    /**
     * This method deletes a Question table entry by first
     * deleting the data from the Question_Answer table then
     * if that is appropriate it will delete the question by the same id
     * used for the question_answer table
     *
     * @param id representing the id of said question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void deleteQuestionAnswerByQuestionIdFromQuestionAnswer(int id) throws SQLException {
        qdb.deleteQuestionAnswerByQuestionIdFromQuestionAnswer(id);
        qdb.deleteQuestionById(id);
        mainController.confirmationUI();

    }

    /**
     * This method returns a region from the Question table
     * by using the parameters
     *
     * @param region representing the region of said question
     * @return a region from the question table
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    String getRegion(String region) throws SQLException {
        return qdb.getRegion(region);

    }

    /**
     * This method extracts a list of questions subjects that by using
     * difficulty and region of the current quiz
     *
     * @param difficulty representing the difficultyLevel of said question
     * @param region     representing the region of said question
     *                   as parameters it can
     * @return a list of questions subjects by the selected difficulty level and region
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException {

        return qdb.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
    }

    /**
     * This method extracts a list of Answers specific for a question that by using
     * difficulty and region of the current quiz
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
    List<String> getAnQuestionAnswerList(String difficultyLevel, String region, String question) throws SQLException {
        return qdb.getAnQuestionAnswerList(difficultyLevel, region, question);

    }

}
