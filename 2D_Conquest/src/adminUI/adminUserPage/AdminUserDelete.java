package adminUI.adminUserPage;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class AdminUserDelete  extends JFrame {
    private MyController controller;
    private JButton blogin = new JButton("Login");
    private JButton alogin = new JButton("Admin Login");
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("Login UserName");
    private JLabel lpass = new JLabel("Login Password");

    /**
     * Admin User Delete Page  UI's Constructor
     * @param controller of type MyController
     */
    public AdminUserDelete(MyController controller) {

        super("Delete an User");

        this.controller = controller;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(350, 250);
        setLocation(500, 280);

        luser.setBounds(20, 30, 120, 20);
        lpass.setBounds(20, 65, 120, 20);
        txuser.setBounds(140, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        blogin.setBounds(30, 140, 120, 40);
        alogin.setBounds(190, 140, 120, 40);

        super.add(luser);
        super.add(lpass);
        super.add(blogin);
        super.add(alogin);
        super.add(txuser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        blogin.addActionListener(ae -> {
//            String puname = txuser.getText();
//            String ppaswd = String.valueOf(pass.getPassword());
//            try {
//                dispose();
//                controller.verifyUserLogin(puname, ppaswd);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
//        alogin.addActionListener(ae -> {
//            String puname = txuser.getText();
//            String ppaswd = String.valueOf(pass.getPassword());
//            try {
//                dispose();
//                controller.verifyAdminLogin(puname, ppaswd);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
    }

    public void clearFields() {
        JOptionPane.showMessageDialog(null,
                "The system could not log you in.\n" + " Please make sure your username and password are correct",
                "Login Failure", JOptionPane.INFORMATION_MESSAGE);
        txuser.setText("");
        pass.setText("");
        txuser.requestFocus();
    }
}