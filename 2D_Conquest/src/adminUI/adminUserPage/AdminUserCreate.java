package adminUI.adminUserPage;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class AdminUserCreate extends JFrame {
    private MyController ctrler;
    private JButton createNewUser = new JButton("Create User");
    private JButton back = new JButton("Back");
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("New UserName");
    private JLabel lpass = new JLabel("New Password");

    /**
     * Login Page UI's Constructor
     *
     * @param ctrler
     */
    public AdminUserCreate(MyController ctrler) {

        super("LoginPage UI");

        this.ctrler = ctrler;
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
            String ppaswd = pass.getText();
            try {
                dispose();
                ctrler.createNewUser(puname, ppaswd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        back.addActionListener(ae -> {
            dispose();
            ctrler.openAdminPageUI();
        });
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