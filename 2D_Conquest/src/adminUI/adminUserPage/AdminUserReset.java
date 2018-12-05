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
     * Admin Quest Delete's Constructor
     *
     * @param controller of type MyController
     */
    public AdminUserReset(MyController controller, AdminController aController) {

        super("Reset An User UI");

        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceLogin();

    }

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

    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }


}