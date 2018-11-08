package controller;

import java.sql.*;

import dao.DatabaseI;
import view.*;

public class MyController {
    private DatabaseI db;
    private CountryUI countryUI;
    private DifficultyLevelUI difficultyLevelUI;
    private EasyQuestionUI easyQuestionUI;
    private MediumQuestionUI mediumQuestionUI;
    private HardQuestionUI hardQuestionUI;
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

    public void start() {
        MainUI mainUI = new MainUI(this);

    }

    public void openLoginWindow() {
        loginPageUI = new LoginPageUI(this);
    }

    public void openCountryWindow() {
        countryUI = new CountryUI(this);
    }

    public void openDifficultyWindow() {
        difficultyLevelUI = new DifficultyLevelUI(this);
    }

    public void openEasyWindow() {
        easyQuestionUI = new EasyQuestionUI(this);
    }

    public void openMediumWindow() {
        mediumQuestionUI = new MediumQuestionUI(this);
    }

    public void openHardWindow() {
        hardQuestionUI = new HardQuestionUI(this);
    }

}
