package controller;

import adminUI.AdminPageUI;
import adminUI.AdminQuestionUI;
import adminUI.AdminSettingsUI;
import adminUI.AdminUserUI;
import adminUI.adminQuestionPage.AdminQuestionCreate;
import adminUI.adminUserPage.AdminUserCreate;
import adminUI.adminUserPage.AdminUserDelete;
import adminUI.adminUserPage.AdminUserEdit;
import adminUI.adminUserPage.AdminUserReset;
import dao.UserDatabaseI;

import java.sql.SQLException;

/**
 * File created on 11/22/2018
 * by Toader
 **/
public class AdminController {

    private UserDatabaseI udb;
    private MyController mainController;

    /**
     * This constructor initiates the AdminController and initializes it
     * with a UserDatabaseI interface
     *
     * @param udb is the UserDatabaseI interface needed to instantiate the constructor
     */
    public AdminController(UserDatabaseI udb) {
        this.udb = udb;
    }

    /**
     * This void method instantiates the Admin controller with an MyController class
     *
     * @param mainController is the MyController class needed to
     *                       set the connection between the 2 controllers
     */
    public void setMainControllerAdminController(MyController mainController) {
        this.mainController = mainController;
    }

    /**
     * This method method adds question to the User Table
     * then after doing so it will display and Success panel
     * and afterwards it will open the ConfirmationUI window
     *
     * @param userName representing the username introduced by the admin
     * @param password representing the password introduced by the admin
     *                 as parameters it can
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    void createNewUser(String userName, String password) throws SQLException {
        udb.createNewUser(userName, password);
        mainController.dataAddedSuccess();
        mainController.confirmationUI();
    }


    /**
     * This method deletes a User table entry by
     * deleting the User by the same id
     * used for the User table
     *
     * @param id representing the id introduced by the admin
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    public void deleteUser(int id) throws SQLException {
        udb.deleteUser(id);
        mainController.confirmationUI();

    }

    /**
     * @param id
     * @param username
     * @param password
     * @throws SQLException
     */
    public void updateUser(int id, String username, String password) throws SQLException {
        udb.updateUser(id, username, password);
        mainController.confirmationUI();

    }

    /**
     * @param id
     * @throws SQLException
     */
    public void resetPasswordForUserById(int id) throws SQLException {
        udb.resetPasswordForUserById(id);
        mainController.confirmationUI();

    }

    /**
     *
     */
    public void openAdminQuestionUI() {
        new AdminQuestionUI(mainController, this);
    }

    /**
     *
     */
    public void openAdminUserUI() {
        new AdminUserUI(this);
    }

    /**
     *
     */
    public void openAdminSettingsUI() {
        new AdminSettingsUI(mainController);
    }

    /**
     *
     */
    public void openAdminPageUI() {
        new AdminPageUI(mainController, this);
    }

    /**
     *
     */
    public void openAdminCreateUserUI() {
        new AdminUserCreate(mainController, this);
    }

    /**
     *
     */
    public void openAdminDeleteUserUI() {
        new AdminUserDelete(mainController, this);
    }

    /**
     *
     */
    public void openAdminEditUserUI() {
        new AdminUserEdit(mainController, this);
    }

    /**
     *
     */
    public void openAdminResetUserUI() {
        new AdminUserReset(mainController, this);
    }

    /**
     *
     */
    public void openAdminQuestionCreateUI() {
        new AdminQuestionCreate(mainController, this);
    }
}
