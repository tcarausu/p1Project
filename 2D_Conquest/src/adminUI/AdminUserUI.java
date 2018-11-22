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

    //    private JButton setttingsMenu = new JButton("High Score");
//    private JButton playGame = new JButton("Start");
//
    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    /**
     * Admin User UI's Constructor
     * @param aController of type MyController
     */
    public AdminUserUI(AdminController aController) {
        super("AdminPage User UI");
        this.aController = aController;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceButton();
    }

    /**
     */
    private void introduceButton() {
        setSize(600, 400);

        super.add(createUser);
        super.add(resetUser);
        super.add(deleteUser);
        super.add(editUser);
        super.add(quit);
        super.add(back);

//        super.add(setttingsMenu);
//        super.add(playGame);
//        setttingsMenu.setBounds(70, 150, 160, 40);
//        playGame.setBounds(70, 150, 160, 40);
//
        createUser.setBounds(70, 50, 160, 40);
        resetUser.setBounds(250, 50, 160, 40);
        deleteUser.setBounds(70, 150, 160, 40);
        editUser.setBounds(250, 150, 160, 40);


        back.setBounds(70, 250, 160, 40);
        quit.setBounds(250, 250, 160, 40);


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
