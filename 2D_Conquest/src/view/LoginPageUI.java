package view;

import javax.swing.*;

public class LoginPageUI extends JFrame {

    public static void main(String[] args) {
        LoginPageUI frameTabel = new LoginPageUI();
    }


    public LoginPageUI() {
        JButton blogin = new JButton("Login");
        JPanel panel = new JPanel();
        JTextField txuser = new JTextField(15);
        JPasswordField pass = new JPasswordField(15);

        setTitle("Login Authentication");
        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);


        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        blogin.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = pass.getText();
            if (puname.equals("test") && ppaswd.equals("12345")) {
                newframe regFace = new newframe();
                regFace.setVisible(true);
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