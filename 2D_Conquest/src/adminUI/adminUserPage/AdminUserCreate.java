package adminUI.adminUserPage;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class AdminUserCreate extends JFrame {
    private MyController controller;
    private JButton createNewUser = new JButton("Create User");
    private JButton back = new JButton("Back");
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("New UserName");
    private JLabel lpass = new JLabel("New Password");

    /**
     * Login Page UI's Constructor
     *
     * @param controller of type MyController
     */
    public AdminUserCreate(MyController controller) {

        super("LoginPage UI");

        this.controller = controller;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Login Authentication");
        introduceLogin();

    }

    private void introduceLogin() {
        setSize(350, 250);
        setLocation(500, 280);

        luser.setBounds(20, 30, 120, 20);
        lpass.setBounds(20, 65, 120, 20);
        txuser.setBounds(140, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        createNewUser.setBounds(30, 140, 120, 40);
        back.setBounds(190, 140, 120, 40);

        super.add(luser);
        super.add(lpass);
        super.add(createNewUser);
        super.add(back);
        super.add(txuser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        createNewUser.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = String.valueOf(pass.getPassword());
            try {
                dispose();
                if (puname != null && !puname.equals("") && !ppaswd.equals("")) {

                    controller.verifyAdminDataOnUserCreate(puname, ppaswd);
                } else {
                    clearFields();
                    controller.openAdminCreateUserUI();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        back.addActionListener(ae -> {
            dispose();
            controller.openAdminPageUI();
        });
    }

    public void clearFields() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is wrong.\n" +
                        " Please make sure your username and password are correct and available",
                "Incorect Information", JOptionPane.INFORMATION_MESSAGE);
        txuser.setText("");
        pass.setText("");
        txuser.requestFocus();
    }
}