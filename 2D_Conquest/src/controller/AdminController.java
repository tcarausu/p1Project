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
     * @param udb
     */
    public AdminController(UserDatabaseI udb) {
        this.udb = udb;
    }

    /**
     * @param mainController
     */
    public void setMainControllerAdminController(MyController mainController) {
        this.mainController = mainController;
    }

    /**
     * @param userName
     * @param password
     * @throws SQLException
     */
    void createNewUser(String userName, String password) throws SQLException {
        udb.createNewUser(userName, password);
        mainController.dataAddedSuccess();
        mainController.confirmationUI();
    }

    /**
     * @param userName
     * @param password
     * @throws SQLException
     */
    void createNewUserOnLogin(String userName, String password) throws SQLException {
        udb.createNewUser(userName, password);
        mainController.dataAddedSuccess();
        mainController.openCountryWindow();
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

    public void openAdminQuestionEditUI() {
//        new AdminQuestionEdit(this);
    }

    public void openAdminQuestionDeleteUI() {
//        new AdminQuestionDelete(this);
    }

    /**
     * @param id
     * @throws SQLException
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
}
