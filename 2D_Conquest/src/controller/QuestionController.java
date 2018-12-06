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

    public QuestionController(QuestionDatabaseI qdb) {
        this.qdb = qdb;
    }

    public void setMainControllerQuestionController(MyController mainController) {
        this.mainController = mainController;
    }

    String getAnEasyQuestion(String region) throws SQLException {
        return qdb.getAnEasyQuestion(region);
    }

    public String getAMediumQuestion(String region) throws SQLException {
        return qdb.getAMediumQuestion(region);
    }

    public String getAHardQuestion(String region) throws SQLException {
        return qdb.getAHardQuestion(region);

    }

    public List<String> getAnEasyQuestionAnswerList(String region, String question) throws SQLException {
        return qdb.getAnEasyQuestionAnswerList(region, question);

    }

    public String getAMediumQuestionCorrectAnswer(String region) throws SQLException {
        return qdb.getAMediumQuestionCorrectAnswer(region);

    }

    public String getAHardQuestionCorrectAnswer(String region) throws SQLException {
        return qdb.getAHardQuestionCorrectAnswer(region);

    }


    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultyLevel,
                           String region) throws SQLException {
        qdb.createNewQuestion(subject,
                typeOfQuestion, difficultyLevel, region);
        mainController.dataAddedSuccess();
        mainController.confirmationUI();
    }

    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultyLevel,
                                     String region) throws SQLException {
        return qdb.verifyIntroducedQuestion(subject,
                typeOfQuestion, difficultyLevel, region);
    }

    String getRegion(String region) throws SQLException {
        return qdb.getRegion(region);

    }

    public void deleteQuestionById(int id) throws SQLException {
        qdb.deleteQuestionByIdFromQuestionAnswer(id);
        qdb.deleteQuestionById(id);
        mainController.confirmationUI();

    }

    public void updateQuestionById(int id, String subject, String typeOfQ,
                                   String diffLevel, String region) throws SQLException {
        qdb.updateQuestionById(id, subject, typeOfQ, diffLevel, region);
        mainController.confirmationUI();

    }

    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException {

        return qdb.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
    }

}
