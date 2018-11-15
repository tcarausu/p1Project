package controller;

import adminUI.*;
import adminUI.adminUserPage.AdminUserCreate;
import dao.DatabaseI;
import dao.QuestionDatabaseI;
import dao.UserDatabaseI;
import view.*;

import javax.swing.*;
import java.sql.SQLException;

public class MyController {
    private DatabaseI db;
    private UserDatabaseI udb;
    private QuestionDatabaseI qdb;
    private LoginPageUI loginPageUI;

    public MyController(DatabaseI db, UserDatabaseI udb, QuestionDatabaseI qdb) throws SQLException {
        this.db = db;
        this.udb = udb;
        this.qdb = qdb;

    }

    public void verifyUserLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            openCountryWindow();
        } else {
            loginPageUI.clearFields();
            openLoginWindow();
        }

    }

    public void verifyAdminDataOnUserCreate(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            openAdminCreateUserUI();
        } else {
            createNewUser(userName, password);

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

    private void createNewUser(String userName, String password) throws SQLException {
        udb.createNewUser(userName, password);
        dataAddedSuccess();
        confirmationUI();
    }

    public void start() {
        MainUI mainUI = new MainUI(this);

    }

    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this);
    }

    public void openCountryWindow() {
        CountryUI countryUI = new CountryUI(this);
    }

    public void openDifficultyWindow() {
        DifficultyLevelUI difficultyLevelUI = new DifficultyLevelUI(this);
    }

    public void openEasyWindow() {
        EasyQuestionUI easyQuestionUI = new EasyQuestionUI(this);
    }

    public void openMediumWindow() {
        MediumQuestionUI mediumQuestionUI = new MediumQuestionUI(this);
    }

    public void openHardWindow() {
        HardQuestionUI hardQuestionUI = new HardQuestionUI(this);
    }

    public void openAdminQuestionUI() {
        AdminQuestionUI adminQuestionUI = new AdminQuestionUI(this);
    }

    public void openAdminUserUI() {
        AdminUserUI adminUserUI = new AdminUserUI(this);
    }

    public void openAdminSettingsUI() {
        AdminSettingsUI adminSettingsUI = new AdminSettingsUI(this);
    }

    public void openAdminPageUI() {
        AdminPageUI adminPageUI = new AdminPageUI(this);
    }

    public void openScoreWindow() {
        HighScoreUI highscoreUI = new HighScoreUI(this);
    }

    public void openAdminCreateUserUI() {
        AdminUserCreate adminUserUI = new AdminUserCreate(this);
    }

    public void confirmationUI() {
        ConfirmationUI confirmationUI = new ConfirmationUI(this);
    }

    private void alreadyInDatabaseFields() {
        JOptionPane.showMessageDialog(null,
                "The information introduced already exists.\n" +
                        " Please make sure your information you are trying to introduce is not already in use",
                "Already In Use", JOptionPane.INFORMATION_MESSAGE);
    }

    private void dataAddedSuccess() {
        JOptionPane.showMessageDialog(null,
                "The information had been introduced with success.\n" +
                        " Please decide your next operation",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
