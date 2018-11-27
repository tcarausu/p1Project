package controller;

import QuestionUI.QuestionWindow;
import adminUI.adminQuestionPage.AdminAllQuestionTable;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MyController {
    private DatabaseI db;
    private User user;
    private QuestionController qController;
    private AdminController aController;
    private LoginPageUI loginPageUI;

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
            openCountryWindow();
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

    public void updateScoreOnEasyForUser(int nrOfQAnswered) throws SQLException {
        String difficulty = "easy";
        String userName = getUser().getUserName();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userName, difficulty);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userName, difficulty, totalScore);
        if (nrOfQAnswered == 0) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnswered + 1, totalScore, userName, difficulty);

        } else if (nrOfQAnswered >= 1 && nrOfQAnswered < totalNrOfQ) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnswered + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnswered + 1, totalScore, userName, difficulty);
        }
    }
    public void updateScoreOnMediumForUser(int nrOfQAnsweredOnMedium) throws SQLException {
        String difficulty = "medium";
        String userName = getUser().getUserName();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userName, difficulty);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userName, difficulty, totalScore);
        if (nrOfQAnsweredOnMedium == 0) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnsweredOnMedium + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnsweredOnMedium + 1, totalScore, userName, difficulty);

        } else if (nrOfQAnsweredOnMedium >= 1 && nrOfQAnsweredOnMedium < totalNrOfQ) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnsweredOnMedium + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnsweredOnMedium + 1, totalScore, userName, difficulty);
        }
    }

    public void updateScoreOnHardForUser(int nrOfQAnsweredOnHard) throws SQLException {
        String difficulty = "hard";
        String userName = getUser().getUserName();
        int totalScore = getHighScoreOnUserWithDifficultyLevel(userName, difficulty);
        int totalNrOfQ = getNrOfQuestionsTotalFromCurrentQuiz(userName, difficulty, totalScore);
        if (nrOfQAnsweredOnHard == 0) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnsweredOnHard + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnsweredOnHard + 1, totalScore, userName, difficulty);

        } else if (nrOfQAnsweredOnHard >= 1 && nrOfQAnsweredOnHard < totalNrOfQ) {
            updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnsweredOnHard + 1, userName, totalScore);
            updateScoreOnDifficultyForUser(nrOfQAnsweredOnHard + 1, totalScore, userName, difficulty);
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

    public void startEasyQuiz(int total, String difficultyLevel) throws SQLException {
        String username = getUser().getUserName();
        if (!db.checkHighscoreData(username, total, difficultyLevel)) {
            db.startQuiz(username, total, difficultyLevel);

        }
    }
    public void startHardQuiz(String username, int total, String difficultylevel) throws SQLException {
        if (!db.checkHighscoreData(getUser().getUserName(), total, difficultylevel)) {
            db.startQuiz(getUser().getUserName(), total, difficultylevel);

        }
    }
    public void startMediumQuiz(int total, String difficultylevel) throws SQLException {
        String username = getUser().getUserName();

        if (!db.checkHighscoreData(username, total, difficultylevel)) {
            db.startQuiz(getUser().getUserName(), total, difficultylevel);

        }
    }

    public int getNrOfQuestionsAnsweredFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        return db.getNumberOfQuestionsAnsweredFromCurrentQuiz(username, difficultyLevel, score);
    }

    private void updateNrfQuestionsAnswFromCurrentQuiz(int nrOfQAnswered, String userName, int totalScore) throws SQLException {
        db.updateNrfQuestionsAnswFromCurrentQuiz(nrOfQAnswered, userName, totalScore);
    }

    private void updateScoreOnDifficultyForUser(int nrOfQAnswered, int totalScore, String userName, String difficulty) throws SQLException {
        db.updateScoreOnDifficultyForUser(nrOfQAnswered, totalScore, userName, difficulty);
    }

    public int getHighScoreOnUserWithDifficultyLevel(String username, String difficultyLevel) throws SQLException {
        return db.getHighScoreOnUserWithDifficultyLevel(username, difficultyLevel);
    }

    public int getNrOfQuestionsTotalFromCurrentQuiz(String username, String difficultyLevel, int score) throws SQLException {
        return db.getNumberOfQuestionsTotalFromCurrentQuiz(username, difficultyLevel, score);
    }

    public void start() {
        new MainUI(this, aController, qController);

    }

    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this);
    }

    public void openCountryWindow() {
        new CountryUI(this);
    }

    public void openDifficultyWindow(String region) throws SQLException {
        qController.getRegion(region);
        /*
        While intitiating the difficulty/question we are going to check it based on the input/zone
        String getRegion(String region)

         */
        new DifficultyLevelUI(this, region);
    }


    public void openEasyWindow(String region) {
        new EasyQuestionUI(this, qController, region);
    }

    public void openMediumWindow(String region) {
        new MediumQuestionUI(this, qController,region);
    }

    public void openHardWindow(String region) {
        new HardQuestionUI(this, qController,region);
    }

    void confirmationUI() {
        new ConfirmationUI(this, aController);
    }

    public void openScoreWindow() {
        new HighScoreUI(this);
    }

    public void openAdminFullQuestionTable() {
        new AdminAllQuestionTable(this, aController, (Database) db);
    }


    public void openQuestionWIndow() {
        new QuestionWindow(this);
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

    private void setCurrentUser(String userName, String password) {
        user = new User(userName, password);
        user.setController(this);
    }

    public User getUser() {
        return user;
    }
}
