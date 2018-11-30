package view;

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
public class ConfirmationUI extends JFrame {
    private MyController controller;
    private AdminController aController;
    private JButton back = new JButton("Back To Editing");

    private JTextField adminUser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("New Admin UserName");
    private JLabel lpass = new JLabel("New Admin Password");

    /**
     * Confirmation UI's Constructor
     *
     * @param controller of type MyController
     */
    public ConfirmationUI(MyController controller, AdminController aController) {

        super("Confirmation UI");

        this.controller = controller;
        this.aController = aController;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(380, 250);
        setLocation(500, 280);

        luser.setBounds(20, 30, 140, 20);
        lpass.setBounds(20, 65, 140, 20);
        adminUser.setBounds(160, 30, 180, 20);
        pass.setBounds(160, 65, 180, 20);
        back.setBounds(110, 140, 160, 40);


        super.add(back);
        super.add(luser);
        super.add(lpass);
        super.add(adminUser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String puname = adminUser.getText();
                    String ppaswd = String.valueOf(pass.getPassword());
                    try {
                        dispose();
                        controller.verifyAdminLogin(puname, ppaswd);
                    } catch (SQLException sql) {
                        sql.printStackTrace();
                    }

                }
            }
        });

        back.addActionListener(ae -> {

            dispose();
            aController.openAdminPageUI();
        });
    }

}
