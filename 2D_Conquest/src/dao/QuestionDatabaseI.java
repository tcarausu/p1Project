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

    List<String> getAnQuestionAnswerList(String difficultyLevel,String region, String question)  throws SQLException;

    List<String> getAllQuestionsByDifficultyLevelAndRegion(String difficulty, String region) throws SQLException;

    String getRegion(String region) throws SQLException;

    void deleteQuestionById(int id) throws SQLException;

    void deleteQuestionByIdFromQuestionAnswer(int id) throws SQLException;

    void updateQuestionById(int id, String subject, String typeOfQ,
                            String diffLevel, String region) throws SQLException;

}
