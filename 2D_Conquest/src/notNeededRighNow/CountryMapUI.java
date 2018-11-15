package notNeededRighNow;

import javax.swing.*;

public class CountryMapUI extends JFrame {
    private JButton fblogin = new JButton("first map");
    private JButton sblogin = new JButton("second map");
    private JButton tblogin = new JButton("third map");
    private JButton fthblogin = new JButton("fortth map");

    public static void main(String[] args) {
        CountryMapUI frameTabel = new CountryMapUI();
    }


    public CountryMapUI() {


        JPanel panel = new JPanel();
        JTextField txuser = new JTextField(15);
        JPasswordField pass = new JPasswordField(15);

        setTitle("Login Authentication");
        setSize(300, 400);
        setLocation(500, 280);
        panel.setLayout(null);


        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        fblogin.setBounds(110, 100, 80, 20);
        sblogin.setBounds(110, 150, 80, 20);
        tblogin.setBounds(110, 200, 80, 20);
        fthblogin.setBounds(110, 250, 80, 20);

        panel.add(fblogin);
        panel.add(sblogin);
        panel.add(tblogin);
        panel.add(fthblogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        fblogin.addActionListener(ae -> {
            String puname = txuser.getText();
            String ppaswd = String.valueOf(pass.getPassword());
            if (puname.equals("test") && ppaswd.equals("12345")) {
                Welcome regFace = new Welcome();
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
