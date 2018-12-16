package view;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import static java.lang.String.valueOf;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class ConfirmationUI extends JFrame {
    private MyController controller;
    private AdminController aController;
    private JButton back = new JButton("Back To Editing");

    private JTextField adminUser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel userLabel = new JLabel("Admin UserName");
    private JLabel passLabel = new JLabel("Admin Password");

    /**
     * Confirmation UI's Constructor
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public ConfirmationUI(MyController controller, AdminController aController) {

        super("Confirmation UI");

        this.controller = controller;
        this.aController = aController;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        confirmUI();

    }

    /**
     * This method instantiates the buttons as well as performs the check of the admin,
     * by either using the "Enter" button or clicking on the button
     */
    private void confirmUI() {
        setSize(380, 250);
        setLocation(500, 280);

        userLabel.setBounds(20, 30, 140, 20);
        passLabel.setBounds(20, 65, 140, 20);
        adminUser.setBounds(160, 30, 180, 20);
        pass.setBounds(160, 65, 180, 20);
        back.setBounds(110, 140, 160, 40);


        super.add(back);
        super.add(userLabel);
        super.add(passLabel);
        super.add(adminUser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmationOfAdmin();
                }
            }
        });

        back.addActionListener(ae -> {

            dispose();
            confirmationOfAdmin();
            aController.openAdminPageUI();
        });
    }

    /**
     * This is an auxiliary method to confirm the Admin data.
     */
    private void confirmationOfAdmin() {
        String user = adminUser.getText();
        String password = valueOf(pass.getPassword());
        try {
            dispose();
            controller.verifyAdminLogin(user, password);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

}
