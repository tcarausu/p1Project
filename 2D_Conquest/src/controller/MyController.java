package controller;

import adminUI.*;
import adminUI.adminUserPage.AdminUserCreate;
import adminUI.adminUserPage.AdminUserDelete;
import adminUI.adminUserPage.AdminUserEdit;
import adminUI.adminUserPage.AdminUserReset;
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

    public MyController(DatabaseI db, UserDatabaseI udb, QuestionDatabaseI qdb) {
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
        new CountryUI(this);
    }

    public void openDifficultyWindow() {
        new DifficultyLevelUI(this);
    }

    public void openEasyWindow() {
        new EasyQuestionUI(this);
    }

    public void openMediumWindow() {
        new MediumQuestionUI(this);
    }

    public void openHardWindow() {
        new HardQuestionUI(this);
    }

    public void openAdminQuestionUI() {
        new AdminQuestionUI(this);
    }

    public void openAdminUserUI() {
        new AdminUserUI(this);
    }

    public void openAdminSettingsUI() {
        new AdminSettingsUI(this);
    }

    public void openAdminPageUI() {
        new AdminPageUI(this);
    }

    public void openAdminCreateUserUI() {
        new AdminUserCreate(this);
    }

    public void openAdminDeleteUserUI() {
        new AdminUserDelete(this);
    }

    public void openAdminEditUserUI() {
        new AdminUserEdit(this);
    }

    public void openAdminResetUserUI() {
        new AdminUserReset(this);
    }

    private void confirmationUI() {
        new ConfirmationUI(this);
    }

    public void openScoreWindow() {
        new HighScoreUI(this);
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
