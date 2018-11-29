package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface QuestionDatabaseI {
    void createNewQuestion(String subject,
                           String typeOfQuestion, String difficultyLevel,
                           String region) throws SQLException;

    boolean verifyIntroducedQuestion(String subject,
                                     String typeOfQuestion, String difficultyLevel,
                                     String region) throws SQLException;

    String getAnEasyQuestion(String region) throws SQLException;

    String getAMediumQuestion(String region) throws SQLException;

    String getAHardQuestion(String region) throws SQLException;

    String getAMediumQuestionCorrectAnswer(String region) throws SQLException;

    String getAHardQuestionCorrectAnswer(String region) throws SQLException;

    List<String> getAnEasyQuestionAnswerList(String region, String question) throws SQLException;

    List getAll() throws SQLException;

    String getRegion(String region) throws SQLException;

}
