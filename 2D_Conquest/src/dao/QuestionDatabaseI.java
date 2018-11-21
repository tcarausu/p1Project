package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface QuestionDatabaseI {
    void createNewQuestion(String subject, String correctAnswer,
                           String typeOfQuestion, String difficultylevel,
                           String region) throws SQLException;

    boolean verifyIntroducedQuestion(String subject, String correctAnswer,
                                     String typeOfQuestion, String difficultylevel,
                                     String region) throws SQLException;

    String getAnEasyQuestion() throws SQLException;

    String getAnEasyQuestionCorrectAnswer() throws SQLException;

    List getAll() throws SQLException;
}
