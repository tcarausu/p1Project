package adminUI;

import controller.AdminController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminUserUI extends JFrame {
    private AdminController aController;

    private JButton createUser = new JButton("Create User");
    private JButton resetUser = new JButton("Reset User");
    private JButton deleteUser = new JButton("Delete User");
    private JButton editUser = new JButton("Edit User");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    /**
     * Admin User UI's Constructor
     *
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminUserUI(AdminController aController) {
        super("AdminPage User UI");
        this.aController = aController;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceUserButtons();
    }

    /**
     * This method allows the administrator to add new users
     * reset,delete,edit existing users.
     * <p>
     * This method instantiates the buttons and attaches them to the window
     * <p>
     * If the user wants to go back to AdminPage UI it will use Back button
     */
    @SuppressWarnings("Duplicates")
    private void introduceUserButtons() {
        setSize(500, 400);

        super.add(createUser);
        super.add(resetUser);
        super.add(deleteUser);
        super.add(editUser);
        super.add(quit);
        super.add(back);

        AdminPageUI.uiSetup(createUser, resetUser, deleteUser, editUser, back, quit);

        createUser.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminCreateUserUI();

                }
        );
        resetUser.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminResetUserUI();

                }
        );
        deleteUser.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminDeleteUserUI();

                }
        );
        editUser.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminEditUserUI();

                }
        );
        back.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminPageUI();

                }
        );
        quit.addActionListener(e -> dispose());
    }

}
