package adminUI.adminUserPage;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class AdminUserEdit extends JFrame {

    private MyController controller;
    private AdminController aController;

    private JButton deleteUser = new JButton("Edit User");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField(15);

    private JLabel userId = new JLabel("User ID");

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
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(500, 200);
        setLocation(500, 280);


        userId.setBounds(20, 30, 140, 20);
        id.setBounds(160, 30, 250, 20);

        deleteUser.setBounds(30, 100, 120, 40);
        back.setBounds(350, 100, 120, 40);

        super.add(userId);
        super.add(deleteUser);

        super.add(back);
        super.add(id);

        deleteUser.addActionListener(ae -> {
            String idText = id.getText();
            try {
                dispose();
                if (idText != null
                        && !idText.equals("")
                ) {
                    aController.updateUser(Integer.parseInt(idText));
                } else {
                    clearFieldsWhenNeeded();
                    aController.openAdminUserUI();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }


}