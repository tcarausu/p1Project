package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * File created on 11/13/2018
 * by Toader
 **/
public interface QuestionDatabaseI {
    public void createNewQuestion(String subject, String correctAnswer,
                                  String typeOfQuestion, String difficultylevel,
                                  String region) throws SQLException;

    public boolean verifyIntroducedQuestion(String subject, String correctAnswer,
                                            String typeOfQuestion, String difficultylevel,
                                            String region
                                             ) throws SQLException;

    public List getAll() throws SQLException;
}
