package view;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginPageUI extends JFrame {
    private MyController controller;
    private AdminController aController;

    private JButton ulogin = new JButton("User Login");
    private JButton alogin = new JButton("Admin Login");
    private JButton createuser = new JButton("Create User");
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("Login UserName");
    private JLabel lpass = new JLabel("Login Password");

    /**
     * Login Page UI's Constructor
     *
     * @param controller of type MyController
     */
    public LoginPageUI(MyController controller, AdminController adminController) {

        super("LoginPage UI");

        this.controller = controller;
        this.aController = adminController;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Login Authentication");
        introduceLogin();

    }

    private void introduceLogin() { // admin panel de mutat dupa admin login
        setSize(350, 280);
        setLocation(500, 280);

        luser.setBounds(20, 30, 120, 20);
        lpass.setBounds(20, 65, 120, 20);
        txuser.setBounds(140, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        ulogin.setBounds(30, 125, 120, 40);
        alogin.setBounds(110, 190, 120, 40);
        createuser.setBounds(190, 125, 120, 40);

        super.add(luser);
        super.add(lpass);
        super.add(txuser);
        super.add(pass);
        super.add(ulogin);
        super.add(alogin);
        super.add(createuser);

        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    login();

                }
            }
        });
        ulogin.addActionListener(e -> {

            login();
        });

        createuser.addActionListener(e -> {
            String puname = txuser.getText().toLowerCase();
            String ppaswd = String.valueOf(pass.getPassword()).toLowerCase();
            try {
                dispose();
                if (puname != null && !puname.equals("") && !ppaswd.equals("")) {
                    controller.verifyAdminDataOnUserCreateOnLogin(puname, ppaswd);
                    controller.setCurrentUser(puname,ppaswd);
                } else {
                    clearFields();
                    controller.openLoginWindow();
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        });

        alogin.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = String.valueOf(pass.getPassword());
            try {
                dispose();
                controller.verifyAdminLogin(puname, ppaswd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void login() {
        String puname = txuser.getText().toLowerCase();
        String ppaswd = String.valueOf(pass.getPassword()).toLowerCase();
        try {
            dispose();
            controller.verifyUserLogin(puname, ppaswd);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
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