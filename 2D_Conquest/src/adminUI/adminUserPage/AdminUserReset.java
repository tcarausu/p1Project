package adminUI.adminUserPage;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class AdminUserReset extends JFrame {

    private MyController controller;
    private AdminController aController;

    private JButton resetUserPassword = new JButton("Reset Password");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField(15);

    private JLabel userId = new JLabel("User ID");

    /**
     * Admin User Delete's Constructor.
     * <p>
     * Allows administrator to reset a user's information in the database.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminUserReset(MyController controller, AdminController aController) {

        super("Reset An User UI");

        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceLogin();

    }

    /**
     * This method allows the administrator to introduce new login information credentials.
     */
    private void introduceLogin() {
        setSize(500, 200);
        setLocation(500, 280);


        userId.setBounds(20, 30, 140, 20);
        id.setBounds(160, 30, 250, 20);

        resetUserPassword.setBounds(30, 100, 120, 40);
        back.setBounds(350, 100, 120, 40);

        super.add(userId);
        super.add(resetUserPassword);

        super.add(back);
        super.add(id);

        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    resetUserPassword();
                }
            }
        });

        resetUserPassword.addActionListener(ae -> {
            resetUserPassword();
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    /**
     * This method allows the administrator to reset the password credentials of a specific user.
     * <p>
     * This method also checks for valid input (non null/empty).
     */
    private void resetUserPassword() {
        String idText = id.getText();
        try {
            dispose();
            if (idText != null
                    && !idText.equals("")
            ) {
                aController.resetPasswordForUserById(Integer.parseInt(idText));
            } else {
                clearFieldsWhenNeeded();
                aController.openAdminUserUI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows login information fields to be cleared when necessary to enter in new information or delete current input.
     */
    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }

}