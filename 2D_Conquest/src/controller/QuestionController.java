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


    public String getAnEasyQuestion() throws SQLException {
        return qdb.getAnEasyQuestion();

    }

    public String getAnEasyQuestionCorrectAnswer() throws SQLException {
        return qdb.getAnEasyQuestionCorrectAnswer();

    }

    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultylevel,
                           String region) throws SQLException {
        qdb.createNewQuestion(subject,
                typeOfQuestion, difficultylevel, region);
        mainController.dataAddedSuccess();
        mainController.confirmationUI();
    }

    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultylevel,
                                     String region) throws SQLException {
        return qdb.verifyIntroducedQuestion(subject,
                typeOfQuestion, difficultylevel, region);
    }

    public List getAllQuestion() throws SQLException {
        return qdb.getAll();
    }
}
