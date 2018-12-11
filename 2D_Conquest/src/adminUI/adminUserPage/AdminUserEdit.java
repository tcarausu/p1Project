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
public class AdminUserEdit extends JFrame {

    private MyController controller;
    private AdminController aController;

    private JButton editUser = new JButton("Edit User");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField();
    private JTextField username = new JTextField();
    private JTextField password = new JTextField();

    private JLabel userId = new JLabel("User ID");
    private JLabel user_name = new JLabel("User name");
    private JLabel user_password = new JLabel("User password");

    /**
     * Admin Quest Delete's Constructor
     *
     * @param controller of type MyController
     */
    public AdminUserEdit(MyController controller, AdminController aController) {

        super("Edit An User UI");

        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(500, 200);
        setLocation(500, 280);


        userId.setBounds(20, 30, 140, 20);
        user_name.setBounds(20, 60, 140, 20);
        user_password.setBounds(20, 90, 140, 20);
        id.setBounds(160, 30, 250, 20);
        username.setBounds(160, 60, 250, 20);
        password.setBounds(160, 90, 250, 20);

        editUser.setBounds(30, 120, 120, 40);
        back.setBounds(350, 120, 120, 40);

        super.add(userId);
        super.add(user_name);
        super.add(user_password);
        super.add(id);
        super.add(username);
        super.add(password);

        super.add(editUser);

        super.add(back);

        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    editUser();
                }
            }

        });


        editUser.addActionListener(ae -> {
            editUser();
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    private void editUser() {
        String idText = id.getText();
        String user_nameText = username.getText();
        String user_passwordText = password.getText();
        try {
            dispose();
            if (idText != null
                    && !idText.equals("")
            ) {
                aController.updateUser(Integer.parseInt(idText), user_nameText, user_passwordText);
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