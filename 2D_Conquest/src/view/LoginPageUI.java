package view;

import controller.MyController;

import javax.swing.*;

public class LoginPageUI extends JFrame {
    private MyController myController;
    private JButton blogin = new JButton("Login");
    private JTextField txuser = new JTextField(15);
    private JPasswordField pass = new JPasswordField(15);

    public LoginPageUI(
            MyController myController
    ) {

        super("LoginPage UI");

        this.myController = myController;
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Login Authentication");
        introduceLogin();

    }

    private void introduceLogin() {
        setSize(300, 200);
        setLocation(500, 280);


        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        super.add(blogin);
        super.add(txuser);
        super.add(pass);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        blogin.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            if (puname.equals(myController.getUsername()) && ppaswd.equals(myController.getPassword())) {
                CountryUI countryUI = new CountryUI(myController);
                countryUI.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                txuser.setText("");
                pass.setText("");
                txuser.requestFocus();
            }

        });
    }

}