package adminUI;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/12/2018
 * by Toader
 **/
public class ConfirmationUI extends JFrame {
    private MyController ctrler;
    private JButton continueToGame = new JButton("Play");
    private JButton back = new JButton("Back");

    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);
    private JLabel luser = new JLabel("Login UserName");
    private JLabel lpass = new JLabel("Login Password");

    /**
     * Confirmation UI's Constructor
     *
     * @param ctrler
     */
    public ConfirmationUI(MyController ctrler) {

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
        continueToGame.setBounds(30, 140, 120, 40);
        back.setBounds(190, 140, 120, 40);

        super.add(continueToGame);
        super.add(back);
        super.add(luser);
        super.add(lpass);
        super.add(txuser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        continueToGame.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            try {
                dispose();
                ctrler.verifyUserLogin(puname, ppaswd);
                ctrler.openCountryWindow();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        back.addActionListener(ae -> {

            dispose();
            ctrler.openAdminPageUI();
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            try {
                dispose();
                ctrler.verifyUserLogin(puname, ppaswd);
                ctrler.openAdminPageUI();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

}
