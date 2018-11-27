package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface QuestionDatabaseI {
    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultylevel,
                           String region) throws SQLException;

    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultylevel,
                                     String region) throws SQLException;

    String getAnEasyQuestion(String region) throws SQLException;

    String getAMediumQuestion() throws SQLException;

    String getAHardQuestion() throws SQLException;

    String getAnEasyQuestionCorrectAnswer() throws SQLException;

    String getAMediumQuestionCorrectAnswer() throws SQLException;

    String getAHardQuestionCorrectAnswer() throws SQLException;

    String getAnEasyQuestionWrongAnswer() throws SQLException;

    String getAMediumQuestionWrongAnswer() throws SQLException;

    String getAHardQuestionWrongAnswer() throws SQLException;

    List getAll() throws SQLException;

    String getRegion(String region) throws SQLException;

}
