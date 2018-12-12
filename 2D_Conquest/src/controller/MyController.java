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

/**
 *
 */
public class MyController {
    private DatabaseI db;
    private User user;
    private QuestionController qController;
    private AdminController aController;
    private LoginPageUI loginPageUI;

    private List<String> questionsAlreadyUsed = new ArrayList<>();

    private List<String> answersAlreadyUsed = new ArrayList<>();

    /**
     * This is the general constructor (MyController)
     * for the whole application
     * @param db  is the Database interface
     * @param aController is the Admin Controller
     * @param qController is the Question Controller
     */
    public MyController(DatabaseI db, AdminController aController, QuestionController qController) {
        this.db = db;
        this.qController = qController;
        this.aController = aController;

    }

    /**
     * This method checks the username and password
     * from the database with the input from the user
     * withing User table from our database on Login
     * @param userName is the username that has to be checked
     * @param password is the password that has to be checked
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void verifyUserLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            setCurrentUser(userName, password);
            openCountryWindow();
        } else {
            loginPageUI.clearFields();
            openLoginWindow();
        }

    }

    /**
     * This method checks the username and password
     * from the database with the input from the user
     * withing Admin table from our database on Login
     * @param userName is the username that has to be checked
     * @param password is the password that has to be checked
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void verifyAdminLogin(String userName, String password) throws SQLException {
        if (db.verifyAdminLogin(userName, password)) {
            aController.openAdminPageUI();
        } else {
            loginPageUI.clearFields();
            openLoginWindow();
        }

    }

    /**
     * This method checks the username and password
     * from the database with the input from the user
     * withing Admin table from our database
     * if the data is already there then the user should try another user
     * thus after checking it's availability it will add up to the database
     * This is done on the Admin page to create it
     * @param userName is the username that has to be checked
     * @param password is the password that has to be checked
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void verifyAdminDataOnUserCreate(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            aController.openAdminCreateUserUI();
        } else {
            aController.createNewUser(userName, password);

        }

    }

    /**
     * This method checks the username and password
     * from the database with the input from the user
     * withing Admin table from our database
     * if the data is already there then the user should try another user
     * thus after checking it's availability it will add up to the database
     * This is done on the Login page to create it before playing the game as an user
     * @param userName is the username that has to be checked
     * @param password is the password that has to be checked
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void verifyAdminDataOnUserCreateOnLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            openLoginWindow();
        } else {
            aController.createNewUserOnLogin(userName, password);

        }

    }

    /**
     * If the value of the answer is correct for question, it will increase the score
     * if not i will stay the same(it will not skip to the next one)
     *
     * Both nr of Questions as well as the time spent will increment depending
     * on the how many answer the user had done thus far
     * @param answer is the answer selected by the user
     * @param nrOfQAnswered is the current number of questions answered
     * @param difficultyLevel is the difficulty level of the game selected
     * @param timeSpentOnAQuestion represents how much time the user spent thus far in the game
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void updateScoreOnForUserAndDifficulty(String answer, int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        String userName = getUser().getUserName();
        int userIdForCurrentQuiz = getHighScoreId();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, totalScore);
        if (db.checkValidityOfAnswerForAQuestion(answer)) {
            if (nrOfQAnswered == 0) {
                updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion + 1);
                updateScoreOnDifficultyForUser(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, userName, difficultyLevel);

            } else if (nrOfQAnswered >= 1 && nrOfQAnswered <= totalNrOfQ) {
                updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion + 1);
                updateScoreOnDifficultyForUser(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, userName, difficultyLevel);
            }
        } else {

            skipToNextQuestion(nrOfQAnswered, difficultyLevel, timeSpentOnAQuestion);
        }

    }

    /**
     * @param nrOfQAnswered
     * @param difficultyLevel
     * @param timeSpentOnAQuestion
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void skipToNextQuestion(int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        int userIdForCurrentQuiz = getHighScoreId();
        int totalScore;
        totalScore = getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, totalScore);
        if (nrOfQAnswered == 0) {
            updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion + 1);

        } else if (nrOfQAnswered >= 1 && nrOfQAnswered < totalNrOfQ) {
            updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion + 1);
        }

    }


    /**
     * @param subject
     * @param typeOfQuestion
     * @param difficultyLevel
     * @param region
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
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

    /**
     * @param total
     * @param difficultyLevel
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
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

    /**
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getHighScoreId() throws SQLException {
        return db.getHighScoreId();
    }

    /**
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getNrOfQuestionsAnsweredFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException {
        return db.getNrOfQAnsweredFromCurrQuiz(id, difficultyLevel, score);
    }

    /**
     * @param id
     * @param nrOfQAnswered
     * @param totalScore
     * @param timeSpent
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    private void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int totalScore, int timeSpent) throws SQLException {
        db.updateNrfQuestionsAnswerFromCurrentQuiz(id, nrOfQAnswered, totalScore, timeSpent);
    }

    /**
     * @param id
     * @param nrOfQAnswered
     * @param totalScore
     * @param userName
     * @param difficulty
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    private void updateScoreOnDifficultyForUser(int id, int nrOfQAnswered, int totalScore, String userName, String difficulty) throws SQLException {
        switch (difficulty) {
            case "easy":
                db.updateScoreOnDifficultyForUser(id, nrOfQAnswered, totalScore + 100, userName, difficulty);

                break;
            case "medium":
                db.updateScoreOnDifficultyForUser(id, nrOfQAnswered, totalScore + 200, userName, difficulty);

                break;
            case "hard":
                db.updateScoreOnDifficultyForUser(id, nrOfQAnswered, totalScore + 500, userName, difficulty);

                break;
        }
    }

    /**
     * @param id
     * @param difficultyLevel
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException {
        return db.getHighScoreOnUserWithDifficultyLevel(id, difficultyLevel);
    }

    /**
     * @param id
     * @param difficultyLevel
     * @param score
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getNrOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException {
        return db.getNumberOfQuestionsTotalFromCurrentQuiz(id, difficultyLevel, score);
    }

    /**
     * @param id
     * @param score
     * @param difficultyLevel
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int timeSpent(int id, int score, String difficultyLevel) throws SQLException {
        return db.timeSpent(id, score, difficultyLevel);
    }

    /**
     *
     */
    public void start() {
        new MainUI(this, aController, qController);

    }

    /**
     *
     */
    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this, aController);
    }

    /**
     *
     */
    public void openCountryWindow() {
        new CountryUI(this, aController);
    }

    /**
     * @param region
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void openDifficultyWindow(String region) throws SQLException {
        /*
        While intitiating the difficulty/question we are going to check it based on the input/zone
        String getRegion(String region)

         */
        new DifficultyLevelUI(this, qController.getRegion(region));
    }

    /**
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void openEasyWindow(String region) {
        new EasyQuestionUI(this, qController, region);
    }

    /**
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void openMediumWindow(String region) {
        new MediumQuestionUI(this, qController, region);
    }

    /**
     * @param region
     */
    public void openHardWindow(String region) {
        new HardQuestionUI(this, qController, region);
    }

    /**
     *
     */
    void confirmationUI() {
        new ConfirmationUI(this, aController);
    }

    /**
     *
     */
    public void openScoreWindow() {
        new HighScoreUI(this, aController, (Database) db);
    }

    /**
     * @param username
     */
    public void openScoreWindowOnUser(String username) {
        new HighScoreOnUser(this, aController, (Database) db, username);
    }

    /**
     *
     */
    public void openAdminFullQuestionTable() {
        new AdminAllQuestionTable(this, aController, (Database) db);
    }

    /**
     *
     */
    public void openAdminFullUserTable() {
        new AdminAllUsersTable(this, aController, (Database) db);
    }

    /**
     *
     */
    public void openAdminQuestionDeleteUI() {
        new AdminQuestionDelete(this, qController, aController);
    }

    /**
     *
     */
    public void openAdminQuestionEditUI() {
        new AdminQuestionEdit(this, qController, aController);
    }

    /**
     * @param difficulty
     * @param region
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public String questionToBeAnswered(String difficulty, String region) throws SQLException {
        //TODO FIX ME
        List<String> result = qController.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
        return getResults(result, questionsAlreadyUsed);

    }

    /**
     * @param difficulty
     * @param region
     * @param question
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public String answerForQuestion(String difficulty, String region, String question) throws SQLException {
        //TODO FIX ME
        List<String> result = qController.getAnQuestionAnswerList(difficulty, region, question);
        return getResults(result, answersAlreadyUsed);

    }

    /**
     * @param resultsFromDao
     * @param list
     * @return
     */
    private String getResults(List<String> resultsFromDao, List<String> list) {
        int totalNrOfResultsFromDao = resultsFromDao.size();

        if (list == answersAlreadyUsed) {
            if (totalNrOfResultsFromDao == list.size()) {
                answersAlreadyUsed.clear();
            }
        } else if (list == questionsAlreadyUsed) {
            if (totalNrOfResultsFromDao == list.size()) {
                return null;
            }
        }

        SecureRandom sr = new SecureRandom();
        String result;
        do {
            int index = sr.nextInt(totalNrOfResultsFromDao);
            result = resultsFromDao.get(index);
            if (!list.contains(result)) {
                list.add(result);

                break;
            }

        } while (true);

        return result;
    }

    /**
     *
     */
    private void alreadyInDatabaseFields() {
        JOptionPane.showMessageDialog(null,
                "The information introduced already exists.\n" +
                        " Please make sure your information you are trying to introduce is not already in use",
                "Already In Use", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     */
    void dataAddedSuccess() {
        JOptionPane.showMessageDialog(null,
                "The information had been introduced with success.\n" +
                        " Please decide your next operation",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     */
    public void answerSelectionFailure() {
        JOptionPane.showMessageDialog(null,
                "You have not selected anything as an answer.\n" +
                        " Please select your answer",
                "Failure", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param resultSet
     * @return
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
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

    /**
     * @param userName
     * @param password
     */
    public void setCurrentUser(String userName, String password) {
        user = new User(userName, password);
        user.setController(this);
    }

    /**
     * @return
     */
    public User getUser() {
        return user;
    }
}
