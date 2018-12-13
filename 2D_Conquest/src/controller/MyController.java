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
 * File created on 10/02/2018
 * by Toader
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
     *
     * @param db          is the Database interface
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
     * <p>
     * If the username and password are in the database
     * it sets the values to the controller for further use
     * and opens up the Country UI window
     * <p>
     * If the values are not present it clears the fields
     * for the Login UI and opens it up
     *
     * @param userName representing the username that is supposed
     *                 to be verified in the user table
     * @param password representing the username that is supposed
     *                 to be verified in the user table
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
     * <p>
     * If the username and password are in the database
     * it sets the values to the controller for further use
     * and opens up the Admin Settings UI window
     * <p>
     * If the values are not present it clears the fields
     * for the Login UI and opens it up
     *
     * @param userName representing the username that is supposed
     *                 to be verified in the user table
     * @param password representing the username that is supposed
     *                 to be verified in the user table
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
     * This method tries to create another User by
     * checking the username and password
     * from the database with the input from the user
     * withing User table from our database
     * <p>
     * If the data is already there, it will display an pop up
     * with an Already Exists tag and open the current UI;
     * <p>
     * If the data is not there it will create that user using
     * the parameters written by the Admin
     *
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
     * withing Admin table from our database.
     * <p>
     * If the data is already there then the user should try another user
     * thus after checking it's availability it will add up to the database.
     * <p>
     * This is done on the Login page to create it before playing the game as an user
     *
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
            aController.createNewUser(userName, password);

        }

    }

    /**
     * This method checks the admin data used for a question
     *
     * @param subject         represents the questions subject
     * @param typeOfQuestion  represents the questions type Of Question
     * @param difficultyLevel represents the questions difficulty Level
     * @param region          represents the questions region
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
     * This method is going to update the score for an user based on the difficulty of the question.
     * <p>
     * The method will use the username of the current User (player),
     * the highScore Id of the current quiz and it's total Score based on
     * the userIdForCurrentQuiz and it's difficulty level.
     * <p>
     * This method will obtain the total number of question  based on
     * the userIdForCurrentQuiz and it's difficulty level and total Score of it.
     * <p>
     * Both nr of Questions as well as the time spent will increment depending
     * on the how many answers the user had done thus far.
     * <p>
     * In the case that - The number of questions answered for both
     * 0 and
     * 1 or more and at the same time is smaller then the total number of questions
     * it will update the number of questions answered for this quiz by increment it.
     *
     * @param answer               is the answer selected by the user
     * @param nrOfQAnswered        is the current number of questions answered
     * @param difficultyLevel      is the difficulty level of the game selected
     * @param timeSpentOnAQuestion represents how much time the user spent thus far in the game
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void updateScoreOnForUserAndDifficulty(String answer, int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        String userName = getCurrentUser().getUserName();
        int userIdForCurrentQuiz = getHighScoreId();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, totalScore);
        if (db.checkValidityOfAnswerForAQuestion(answer)) {
            if (nrOfQAnswered == 0) {
                updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion);
                updateScoreOnDifficultyForUser(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, userName, difficultyLevel);

            } else if (nrOfQAnswered >= 1 && nrOfQAnswered <= totalNrOfQ) {
                updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion);
                updateScoreOnDifficultyForUser(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, userName, difficultyLevel);
            }
        } else {

            skipToNextQuestion(nrOfQAnswered, difficultyLevel, timeSpentOnAQuestion);
        }

    }

    /**
     * This method is going to skip a question in case the user decides so.
     * <p>
     * The method will use the highScore Id of the current quiz and it's total Score based on
     * the userIdForCurrentQuiz and it's difficulty level.
     * <p>
     * This method will obtain the total number of question  based on
     * the userIdForCurrentQuiz and it's difficulty level and total Score of it.
     * <p>
     * In the case that - The number of questions answered for both
     * 0 and
     * 1 or more and at the same time is smaller then the total number of questions
     * it will update the number of questions answered for this quiz by increment it.
     *
     * @param nrOfQAnswered        represents the number of questions on the quiz
     * @param difficultyLevel      represents the difficulty Level of the quiz
     * @param timeSpentOnAQuestion represents the time Spent On A Question for the quiz
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void skipToNextQuestion(int nrOfQAnswered, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        int userIdForCurrentQuiz = getHighScoreId();
        int totalScore;
        totalScore = getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, totalScore);
        if (nrOfQAnswered == 0) {
            updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion);

        } else if (nrOfQAnswered >= 1 && nrOfQAnswered < totalNrOfQ) {
            updateNrfQuestionsAnswerFromCurrentQuiz(userIdForCurrentQuiz, nrOfQAnswered + 1, totalScore, timeSpentOnAQuestion);
        }

    }

    /**
     * This method stars a new Quiz by instantiating a new entry in the
     * HighScore Table
     * depending on the difficulty selected
     *
     * @param total           represents the total of questions the user will have to answer
     * @param difficultyLevel represents the difficulty selected for the new quiz
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void startQuiz(int total, String difficultyLevel) throws SQLException {
        String username = getCurrentUser().getUserName();
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
     * This method method updates the Number of Questions answered
     * following it's Id, score and time spent doing that.
     *
     * @param id            represents the Id of the quiz
     * @param nrOfQAnswered represents the nrOfQAnswered of the quiz
     * @param totalScore    represents the totalScore of the quiz
     * @param timeSpent     represents the timeSpent of the quiz
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    private void updateNrfQuestionsAnswerFromCurrentQuiz(int id, int nrOfQAnswered, int totalScore, int timeSpent) throws SQLException {
        db.updateNrfQuestionsAnswerFromCurrentQuiz(id, nrOfQAnswered, totalScore, timeSpent);
    }

    /**
     * This method updates the Score on a difficulty for an User
     * depending on the difficulty :
     * 100 for an correct Easy answer
     * 200 for an correct Medium answer
     * 500 for an correct Hard answer
     *
     * @param id            represents the Id of the quiz
     * @param nrOfQAnswered represents the nrOfQAnswered of the quiz
     * @param totalScore    represents the totalScore of the quiz
     * @param userName      represents the userName of the quiz
     * @param difficulty    represents the difficulty of the quiz
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
     * This method returns the highScore on an User
     * based on the current HighScore/Quiz Id
     * by following it's difficulty level for the quiz
     *
     * @param id              represents the Id of the quiz
     * @param difficultyLevel represents the difficultyLevel of the quiz
     * @return Returns the Time Spent on the specific quiz Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getHighScoreOnUserWithDifficultyLevel(int id, String difficultyLevel) throws SQLException {
        return db.getHighScoreOnUserWithDifficultyLevel(id, difficultyLevel);
    }

    /**
     * This method returns the number of questions total on the current HighScore/Quiz Id
     * by following it's score and difficulty level for the quiz
     *
     * @param id              represents the Id of the quiz
     * @param score           represents the score of the quiz
     * @param difficultyLevel represents the difficultyLevel of the quiz
     * @return Returns the number of questions total on the specific quiz Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getNrOfQuestionsAnsweredFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException {
        return db.getNrOfQAnsweredFromCurrQuiz(id, difficultyLevel, score);
    }

    /**
     * This method returns the number of questions total on the current HighScore/Quiz Id
     * by following it's score and difficulty level for the quiz
     *
     * @param id              represents the Id of the quiz
     * @param difficultyLevel represents the difficultyLevel of the quiz
     * @param score           represents the score of the quiz
     * @return Returns the number of questions total on the specific quiz Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getNrOfQuestionsTotalFromCurrentQuiz(int id, String difficultyLevel, int score) throws SQLException {
        return db.getNumberOfQuestionsTotalFromCurrentQuiz(id, difficultyLevel, score);
    }

    /**
     * This method returns the current HighScore Id of the quiz
     *
     * @return Returns the HighScore Id on the specific quiz Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int getHighScoreId() throws SQLException {
        return db.getHighScoreId();
    }

    /**
     * This method returns the time spent based on the current HighScore/Quiz Id
     * by following it's score and difficulty level for the quiz
     *
     * @param id              represents the Id of the quiz
     * @param score           represents the score of the quiz
     * @param difficultyLevel represents the difficultyLevel of the quiz
     * @return Returns the Time Spent on the specific quiz Id
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public int timeSpent(int id, int score, String difficultyLevel) throws SQLException {
        return db.timeSpent(id, score, difficultyLevel);
    }

    /**
     * This method Initiates the MainUI window and "starts" the Application
     * using this MyController, an Admin Controller and a Question Controller
     */
    public void start() {
        new MainUI(this, aController, qController);

    }

    /**
     * This method Initiates the ConfirmationUI window using this MyController
     * and an Admin Controller
     */
    void confirmationUI() {
        new ConfirmationUI(this, aController);
    }

    /**
     * This method Initiates the LoginPageUI window using this MyController
     * and an Admin Controller
     */
    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this, aController);
    }

    /**
     * This method Initiates the CountryUI window using this MyController
     * and an Admin Controller
     */
    public void openCountryWindow() {
        new CountryUI(this, aController);
    }

    /**
     * This method Initiates the HardQuestionUI window using this MyController
     * and an Question Controller with a region as its parameter
     * <p>
     * This methods check the Question Controller for the region to be in the database
     * to display the window
     *
     * @param region represents the the current region for a hard difficulty to start.
     */
    public void openDifficultyWindow(String region) throws SQLException {
        new DifficultyLevelUI(this, qController.getRegion(region));
    }

    /**
     * This method Initiates the EasyQuestionUI window using this MyController
     * and an Question Controller with a region as its parameter
     *
     * @param region represents the the current region for a easy difficulty to start.
     */
    public void openEasyWindow(String region) {
        new EasyQuestionUI(this, qController, region);
    }

    /**
     * This method Initiates the MediumQuestionUI window using this MyController
     * and an Question Controller with a region as its parameter
     *
     * @param region represents the the current region for a medium difficulty to start.
     */
    public void openMediumWindow(String region) {
        new MediumQuestionUI(this, qController, region);
    }

    /**
     * This method Initiates the HardQuestionUI window using this MyController
     * and an Question Controller with a region as its parameter
     *
     * @param region represents the the current region for a hard difficulty to start.
     */
    public void openHardWindow(String region) {
        new HardQuestionUI(this, qController, region);
    }

    /**
     * This method Initiates the AdminQuestionDelete window using this MyController,
     * an Admin Controller and Question Controller
     */
    public void openAdminQuestionDeleteUI() {
        new AdminQuestionDelete(this, qController, aController);
    }

    /**
     * This method Initiates the AdminQuestionEdit window using this MyController,
     * an Admin Controller and Question Controller
     */
    public void openAdminQuestionEditUI() {
        new AdminQuestionEdit(this, qController, aController);
    }

    /**
     * This method Initiates the AdminAllQuestionTable window using this MyController,
     * an Admin Controller and Database Interface casted to Database Dao
     * with username of the current User as parameter.
     */
    public void openAdminFullQuestionTable() {
        new AdminAllQuestionTable(this, aController, (Database) db);
    }

    /**
     * This method Initiates the AdminAllUsersTable window using this MyController,
     * an Admin Controller and Database Interface casted to Database Dao
     * with username of the current User as parameter.
     */
    public void openAdminFullUserTable() {
        new AdminAllUsersTable(this, aController, (Database) db);
    }

    /**
     * This method Initiates the HighScoreUI window using this MyController,
     * an Admin Controller and Database Interface casted to Database Dao
     * with username of the current User as parameter.
     */
    public void openScoreWindow() {
        new HighScoreUI(this, aController, (Database) db);
    }

    /**
     * This method Initiates the HighScoreOnUser window using this MyController,
     * an Admin Controller and Database Interface casted to Database Dao
     * with username of the current User as parameter.
     *
     * @param username represents the the current User's username.
     */
    public void openScoreWindowOnUser(String username) {
        new HighScoreOnUser(this, aController, (Database) db, username);
    }

    /**
     * This method is going to return a Question of type String
     * after looping through the getResults auxiliary method
     * <p>
     * It instantiates a list of the question subjects using :
     * its difficulty and region as parameters.
     *
     * @param difficulty represents the difficulty type for the current question
     * @param region     represents the region type for the current question
     * @return an Question Subject for each iteration of the question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public String questionToBeAnswered(String difficulty, String region) throws SQLException {
        List<String> result = qController.getAllQuestionsByDifficultyLevelAndRegion(difficulty, region);
        return getResults(result, questionsAlreadyUsed);

    }

    /**
     * This method is going to return an Answer of type String
     * after looping through the getResults auxiliary method
     * <p>
     * It instantiates a list of the answer using:
     * the question subject, its difficulty and region as parameters.
     *
     * @param difficulty represents the difficulty type for the current question
     * @param region     represents the region type for the current question
     * @param question   represents the current question that is going to be
     *                   used to gather an answer list
     * @return an Answer for each iteration of the question
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public String answerForQuestion(String difficulty, String region, String question) throws SQLException {
        List<String> result = qController.getAnQuestionAnswerList(difficulty, region, question);
        return getResults(result, answersAlreadyUsed);

    }

    /**
     * This method returns either an question or an answer depending on the
     * list ( resultsFromDao) parameter.
     * <p>
     * First this checks the size of the list that is used to loop and gather data
     * 1. In case that questionsAlreadyUsed of the same size list that is currently looped through
     * it will return null.
     * <p>
     * 2. In case that answersAlreadyUsed of the same size list that is currently looped through
     * it will clear the current list; there was a bug related to the database returning 4 each time
     * and thus it was decide to better clear the list
     * <p>
     * The lists get an SecureRandom which gets an index for a question to loop
     * through and constantly checks it availability to add "unique" results.
     *
     * @param resultsFromDao represents the List which is going to be used
     *                       for the do while loop to gather an appropriate answer
     *                       or an question
     * @param list           represents the Lists that are instantiated in as
     *                       global parameters
     * @return either an answer or an question depending on the list
     * which is using as the second parameter
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
     * This method create a Panel which is going to display
     * "Already in use" when trying to add data to the database
     */
    private void alreadyInDatabaseFields() {
        JOptionPane.showMessageDialog(null,
                "The information introduced already exists.\n" +
                        " Please make sure your information you are trying to introduce is not already in use",
                "Already In Use", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method create a Panel which is going to display
     * a success after adding data to the database
     */
    void dataAddedSuccess() {
        JOptionPane.showMessageDialog(null,
                "The information had been introduced with success.\n" +
                        " Please decide your next operation",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method create a Panel which is going to display
     * a failure when not selecting any answers
     */
    public void answerSelectionFailure() {
        JOptionPane.showMessageDialog(null,
                "You have not selected anything as an answer.\n" +
                        " Please select your answer",
                "Failure", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This is an auxiliary method which creates the structure for
     * the table using a ResultSet from database
     * for each of the operation
     *
     * @param resultSet is the resultSet used for further Displaying
     *                  the data in a Table
     * @return the visual Table structure of the ResultSet
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
     * This method returns the current User logged in to
     * the current,My Controller, for further use
     *
     * @return It returns the current user
     */
    public User getCurrentUser() {
        return user;
    }

    /**
     * This method set the current User logged in to
     * the current,My Controller, for further use
     *
     * @param userName represents the userName for the player User
     * @param password represents the password for the player User
     */
    public void setCurrentUser(String userName, String password) {
        user = new User(userName, password);
        user.setController(this);
    }
}
