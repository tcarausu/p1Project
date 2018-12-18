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

    private JButton userLogin = new JButton("User Login");
    private JButton adminLogin = new JButton("Admin Login");
    private JButton createUser = new JButton("Create User");
    private JTextField userTextField = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel userNameLabel = new JLabel("Login UserName");
    private JLabel passwordLabel = new JLabel("Login Password");

    /**
     * Login Page UI's Constructor
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param adminController represent the AdminController Controller needed to instantiate the constructor
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

    /**
     * This method introduces the buttons and other extremities.
     * <p>
     * For both the Password field as well as the (User) Login  button it will
     * perform a login using Admin credentials, by checking the value from database using the auxiliary method: "login".
     * <p>
     * For the (Admin) Login it will perform a login using Admin credentials afterwards it will open
     * the admin panel.
     * <p>
     * Lastly there is a create new user button which will create an user for the new players, this method is also done by
     * the admin in the Admin User Create UI.
     */
    @SuppressWarnings("Duplicates")
    private void introduceLogin() {
        setSize(350, 280);
        setLocation(500, 280);

        userNameLabel.setBounds(20, 30, 120, 20);
        passwordLabel.setBounds(20, 65, 120, 20);
        userTextField.setBounds(140, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        userLogin.setBounds(30, 125, 120, 40);
        adminLogin.setBounds(110, 190, 120, 40);
        createUser.setBounds(190, 125, 120, 40);

        super.add(userNameLabel);
        super.add(passwordLabel);
        super.add(userTextField);
        super.add(pass);
        super.add(userLogin);
        super.add(adminLogin);
        super.add(createUser);

        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    login();

                }
            }
        });
        userLogin.addActionListener(e -> login());

        createUser.addActionListener(e -> {
            String username = userTextField.getText().toLowerCase();
            String password = String.valueOf(pass.getPassword()).toLowerCase();
            try {
                dispose();
                if (!username.equals("") && !password.equals("")) {
                    controller.verifyAdminDataOnUserCreateOnLogin(username, password);
                    controller.setCurrentUser(username, password);
                    controller.getCurrentDefaultDifficulty();
                } else {
                    clearFields();
                    controller.openLoginWindow();
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        });

        adminLogin.addActionListener(ae -> {
            String username = userTextField.getText();
            String password = String.valueOf(pass.getPassword()).toLowerCase();

            try {
                dispose();
                controller.verifyAdminLogin(username, password);
                controller.setCurrentUser(username, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Auxiliary method for verifying the input of the the user
     * and continue to the game.
     */
    private void login() {
        String username = userTextField.getText();
        String password = String.valueOf(pass.getPassword()).toLowerCase();
        try {
            dispose();
            controller.verifyUserLogin(username, password);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    /**
     * This method clears the fields when the user types incorrect information
     * for further use.
     */
    public void clearFields() {
        JOptionPane.showMessageDialog(null,
                "The system could not log you in.\n" + " Please make sure your username and password are correct",
                "Login Failure", JOptionPane.INFORMATION_MESSAGE);
        userTextField.setText("");
        pass.setText("");
        userTextField.requestFocus();
    }
}