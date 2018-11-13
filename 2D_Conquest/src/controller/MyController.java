package controller;

import adminUI.*;
import adminUI.adminUserPage.AdminUserCreate;
import dao.DatabaseI;
import view.*;

import java.sql.SQLException;

public class MyController {
    private DatabaseI db;
    private LoginPageUI loginPageUI;

    public MyController(DatabaseI db) throws SQLException {
        this.db = db;

    }

    public void verifyUserLogin(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            openCountryWindow();
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

    public void createNewUser(String userName, String password) throws SQLException {
        if (db.createNewUser(userName, password)) {
            confirmationUI();
        } else {
            openAdminPageUI();
        }

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

}
