package adminUI;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class ConfirmationUI extends JFrame {
    private MyController controller;
    private JButton continueToGame = new JButton("Play");
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
    public ConfirmationUI(MyController controller) {

        super("Confirmation UI");

        this.controller = controller;
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
        continueToGame.setBounds(30, 140, 120, 40);
        back.setBounds(180, 140, 160, 40);

        super.add(continueToGame);
        super.add(back);
        super.add(luser);
        super.add(lpass);
        super.add(adminUser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        continueToGame.addActionListener(ae -> {
            dispose();
            controller.openCountryWindow();

        });
        back.addActionListener(ae -> {

            dispose();
            controller.openAdminPageUI();
        });
    }

}
