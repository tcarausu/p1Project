package controller;

import adminUI.adminQuestionPage.AdminAllQuestionTable;
import adminUI.adminQuestionPage.AdminQuestionDelete;
import adminUI.adminQuestionPage.AdminQuestionEdit;
import adminUI.adminUserPage.AdminAllUsersTable;
import dao.Database;
import dao.DatabaseI;
import view.*;
import view.difficulty.DifficultyLevelUI;
import view.difficulty.EasyQuestionUI;
import view.difficulty.HardQuestionUI;
import view.difficulty.MediumQuestionUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyController {
    private DatabaseI db;
    private User user;
    private QuestionController qController;
    private AdminController aController;
    private LoginPageUI loginPageUI;

    private List<String> questionsAlreadyUsed = new ArrayList<>();

    public MyController(DatabaseI db, AdminController aController, QuestionController qController) {
        this.db = db;
        this.qController = qController;
        this.aController = aController;

    }

    public void verifyUserLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            setCurrentUser(userName, password);
            openCountryWindow();
//            openEasyWindow();
        } else {
            loginPageUI.clearFields();
            openLoginWindow();
        }

    }

    public void verifyAdminLogin(String userName, String password) throws SQLException {
        if (db.verifyAdminLogin(userName, password)) {
            aController.openAdminPageUI();
        } else {
            loginPageUI.clearFields();
            openLoginWindow();
        }

    }

    public void verifyAdminDataOnUserCreate(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            aController.openAdminCreateUserUI();
        } else {
            aController.createNewUser(userName, password);

        }

    }

    public void verifyAdminDataOnUserCreateOnLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            openLoginWindow();
        } else {
            aController.createNewUserOnLogin(userName, password);

        }

    }

    /**
     * If the value is answer is correct for question, it will increase the score
     * if not i will stay the same(it will not skip to the next one)
     *
     * @param answer
     * @param nrOfQAnswered
     * @param difficultyLevel
     * @throws SQLException
     */
    public void updateScoreOnForUserAndDifficulty(String answer, int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        String userName = getUser().getUserName();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userName, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userName, difficultyLevel, totalScore);
        if (db.checkValidityOfAnswerForAQuestion(answer)) {
            if (nrOfQAnswered == 0) {
                updateNrfQuestionsAnswerFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore, timeSpentOnAQuestion + 1);
                updateScoreOnDifficultyForUser(nrOfQAnswered + 1, totalScore, userName, difficultyLevel);

            } else if (nrOfQAnswered >= 1 && nrOfQAnswered < totalNrOfQ) {
                updateNrfQuestionsAnswerFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore, timeSpentOnAQuestion + 1);
                updateScoreOnDifficultyForUser(nrOfQAnswered + 1, totalScore, userName, difficultyLevel);
            }
        } else {

            skipToNextQuestion(nrOfQAnswered, difficultyLevel, timeSpentOnAQuestion);
        }

    }

    public void skipToNextQuestion(int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        String userName = getUser().getUserName();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userName, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userName, difficultyLevel, totalScore);
        if (nrOfQAnswered == 0) {
            updateNrfQuestionsAnswerFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore, timeSpentOnAQuestion);

        } else if (nrOfQAnswered >= 1 && nrOfQAnswered < totalNrOfQ) {
            updateNrfQuestionsAnswerFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore, timeSpentOnAQuestion);
        }

    }


    public void verifyAdminDataOnQuestionCreate(String subject,
                                                String typeOfQuestion, String difficultyLevel,
                                                String region) throws SQLException {
        if (qController.verifyIntroducedQuestion(subject, typeOfQuestion, difficultyLevel, region)) {
            alreadyInDatabaseFields();
            aController.openAdminQuestionCreateUI();
        } else {
            qController.createNewQuestion(subject, typeOfQuestion, difficultyLevel, region);
        }

    }

    public void startQuiz(int total, String difficultyLevel) throws SQLException {
        String username = getUser().getUserName();
        switch (difficultyLevel) {
            case "easy":
                db.startQuiz(username, total, difficultyLevel);
                break;
            case "medium":
                db.startQuiz(username, total, difficultyLevel);

                break;
            case "hard":
                db.startQuiz(username, total, difficultyLevel);

                break;
        }

    }

    public int getHighScoreId() throws SQLException {
        return db.getHighScoreId();
    }

    public int getNrOfQuestionsAnsweredFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        return db.getNrOfQAnsweredFromCurrQuiz(username, difficultyLevel, score);
    }

    private void updateNrfQuestionsAnswerFromCurrentQuiz(int nrOfQAnswered, String userName, int totalScore, int timeSpent) throws SQLException {
        db.updateNrfQuestionsAnswerFromCurrentQuiz(nrOfQAnswered, userName, totalScore, timeSpent);
    }

    private void updateScoreOnDifficultyForUser(int nrOfQAnswered, int totalScore, String userName, String difficulty) throws SQLException {
        switch (difficulty) {
            case "easy":
                db.updateScoreOnDifficultyForUser(nrOfQAnswered, totalScore + 100, userName, difficulty);

                break;
            case "medium":
                db.updateScoreOnDifficultyForUser(nrOfQAnswered, totalScore + 200, userName, difficulty);

                break;
            case "hard":
                db.updateScoreOnDifficultyForUser(nrOfQAnswered, totalScore + 500, userName, difficulty);

                break;
        }
    }

    public int getHighScoreOnUserWithDifficultyLevel(String username, String difficultyLevel) throws SQLException {
        return db.getHighScoreOnUserWithDifficultyLevel(username, difficultyLevel);
    }

    public int getNrOfQuestionsTotalFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        return db.getNumberOfQuestionsTotalFromCurrentQuiz(username, difficultyLevel, score);
    }

    public int timeSpent(String username, int score, String difficultyLevel) throws SQLException {
        return db.timeSpent(username, score, difficultyLevel);
    }

    public void start() {
        new MainUI(this, aController, qController);

    }

    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this, aController);
    }

    public void openCountryWindow() {
        new CountryUI(this, aController);
    }

    public void openDifficultyWindow(String region) throws SQLException {
        /*
        While intitiating the difficulty/question we are going to check it based on the input/zone
        String getRegion(String region)

         */
        new DifficultyLevelUI(this, qController.getRegion(region));
    }

    public void openEasyWindow(String region) {
        new EasyQuestionUI(this, qController, region);
    }

    public void openMediumWindow(String region) {
        new MediumQuestionUI(this, qController, region);
    }

    public void openHardWindow(String region) {
        new HardQuestionUI(this, qController, region);
    }

    void confirmationUI() {
        new ConfirmationUI(this, aController);
    }

    public void openScoreWindow() {
        new HighScoreUI(this, aController, (Database) db);
    }

    public void openScoreWindowOnUser(String username) {
        new HighScoreOnUser(this, aController, (Database) db, username);
    }

    public void openAdminFullQuestionTable() {
        new AdminAllQuestionTable(this, aController, (Database) db);
    }

    public void openAdminFullUserTable() {
        new AdminAllUsersTable(this, aController, (Database) db);
    }

    public void openAdminQuestionDeleteUI() {
        new AdminQuestionDelete(this, qController, aController);
    }

    public void openAdminQuestionEditUI() {
        new AdminQuestionEdit(this, qController, aController);
    }

    public String questionToBeAnswered(String difficulty, String region) throws SQLException {
        //TODO FIX ME
        List<String> result = qController.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
        int totalNrOfQ = result.size();

        if (totalNrOfQ == questionsAlreadyUsed.size()) {
            return null;
        }

        SecureRandom sr = new SecureRandom();
        String tempQ;
        do {
            int index = sr.nextInt(totalNrOfQ);
            tempQ = result.get(index);
            if (!questionsAlreadyUsed.contains(tempQ)) {
                questionsAlreadyUsed.add(tempQ);
                break;

                //CLEAR ME
            }
        }
        while (true);

        return tempQ;

    }

    private static int decrementValueTilResultIsOne(int num) {
        return (num == 1 ? 1 : decrementValueTilResultIsOne(num - 1));
        //it was num*decrement for factorial
    }

    private void alreadyInDatabaseFields() {
        JOptionPane.showMessageDialog(null,
                "The information introduced already exists.\n" +
                        " Please make sure your information you are trying to introduce is not already in use",
                "Already In Use", JOptionPane.INFORMATION_MESSAGE);
    }

    void dataAddedSuccess() {
        JOptionPane.showMessageDialog(null,
                "The information had been introduced with success.\n" +
                        " Please decide your next operation",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void answerSelectionFailure() {
        JOptionPane.showMessageDialog(null,
                "You have not selected anything as an answer.\n" +
                        " Please select your answer",
                "Failure", JOptionPane.INFORMATION_MESSAGE);
    }

    public TableModel buildTableModel(ResultSet resultSet)
            throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        // Column names.
        Vector<String> columnNames = new Vector<>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames.add(resultSet.getMetaData().getColumnName(columnIndex));
        }

        // Data of the table.
        Vector<Vector<Object>> dataVector = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> rowVector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowVector.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(rowVector);
        }

        return new DefaultTableModel(dataVector, columnNames);
    }

    public void setCurrentUser(String userName, String password) {
        user = new User(userName, password);
        user.setController(this);
    }

    public User getUser() {
        return user;
    }

}
