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

    public String getAnEasyQuestion(String region) throws SQLException {
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

    public List getAllQuestion() throws SQLException {
        return qdb.getAll();
    }
}
