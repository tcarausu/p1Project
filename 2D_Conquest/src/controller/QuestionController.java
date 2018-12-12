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
     * @param qdb
     */
    public QuestionController(QuestionDatabaseI qdb) {
        this.qdb = qdb;
    }

    /**
     * @param mainController
     */
    public void setMainControllerQuestionController(MyController mainController) {
        this.mainController = mainController;
    }

    /**
     * @param difficultyLevel
     * @param region
     * @param question
     * @return
     * @throws SQLException
     */
    public List<String> getAnQuestionAnswerList(String difficultyLevel, String region, String question) throws SQLException {
        return qdb.getAnQuestionAnswerList(difficultyLevel, region, question);

    }

    /**
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @throws SQLException
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
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @return
     * @throws SQLException
     */
    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultyLevel,
                                     String region) throws SQLException {
        return qdb.verifyIntroducedQuestion(subject,
                typeOfQuestion, difficultyLevel, region);
    }

    /**
     * @param region
     * @return
     * @throws SQLException
     */
    String getRegion(String region) throws SQLException {
        return qdb.getRegion(region);

    }

    /**
     * @param id
     * @throws SQLException
     */
    public void deleteQuestionById(int id) throws SQLException {
        qdb.deleteQuestionByIdFromQuestionAnswer(id);
        qdb.deleteQuestionById(id);
        mainController.confirmationUI();

    }

    /**
     * @param id
     * @param subject
     * @param typeOfQ
     * @param diffLevel
     * @param region
     * @throws SQLException
     */
    public void updateQuestionById(int id, String subject, String typeOfQ,
                                   String diffLevel, String region) throws SQLException {
        qdb.updateQuestionById(id, subject, typeOfQ, diffLevel, region);
        mainController.confirmationUI();

    }

    /**
     * @param difficulty
     * @param region
     * @return
     * @throws SQLException
     */
    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException {

        return qdb.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
    }

}
