package controller;

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

    public void verifyAdminDataOnUserCreate(String userName, String password) throws SQLException {
        if (db.verifyUserLogin(userName, password)) {
            alreadyInDatabaseFields();
            aController.openAdminCreateUserUI();
        } else {
            aController.createNewUser(userName, password);

        }

    }


    public void verifyAdminDataOnQuestionCreate(String subject,
                                                String typeOfQuestion, String difficultylevel,
                                                String region) throws SQLException {
        if (qController.verifyIntroducedQuestion(subject, typeOfQuestion, difficultylevel, region)) {
            alreadyInDatabaseFields();
            aController.openAdminQuestionCreateUI();
        } else {
            qController.createNewQuestion(subject, typeOfQuestion, difficultylevel, region);

        }

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

    public void openDifficultyWindow() {
        new DifficultyLevelUI(this);
    }

    public void openEasyWindow() {
        new EasyQuestionUI(this, qController);
    }

    public void openMediumWindow() {
        new MediumQuestionUI(this);
    }

    public void openHardWindow() {
        new HardQuestionUI(this);
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
}
