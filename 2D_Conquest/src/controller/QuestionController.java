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

    public String getAMediumQuestion() throws SQLException {
        return qdb.getAMediumQuestion();

    }

    public String getAHardQuestion() throws SQLException {
        return qdb.getAHardQuestion();

    }

    public String getAnEasyQuestionCorrectAnswer() throws SQLException {
        return qdb.getAnEasyQuestionCorrectAnswer();

    }

    public String getAMediumQuestionCorrectAnswer() throws SQLException {
        return qdb.getAMediumQuestionCorrectAnswer();

    }

    public String getAHardQuestionCorrectAnswer() throws SQLException {
        return qdb.getAHardQuestionCorrectAnswer();

    }

    public String getAnEasyQuestionWrongAnswer() throws SQLException {
        return qdb.getAnEasyQuestionWrongAnswer();

    }

    public String getAMediumQuestionWrongAnswer() throws SQLException {
        return qdb.getAMediumQuestionWrongAnswer();

    }


    public String getAHardQuestionWrongAnswer() throws SQLException {
        return qdb.getAHardQuestionWrongAnswer();

    }

    public String getRegion(String region) throws SQLException {
        return qdb.getRegion(region);

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

    public List getAllQuestion() throws SQLException {
        return qdb.getAll();
    }
}
