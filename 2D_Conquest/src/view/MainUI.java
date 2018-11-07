package view;

import javax.swing.*;


public class MainUI extends JFrame {

    private JButton start = new JButton("Start");
    private JButton quit = new JButton("QUIT");

    /**
     */
    public MainUI() {
        super("Main UI");
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceButton();
    }

    /**
     */
    private void introduceButton() {

        super.add(start);
        super.add(quit);

        setSize(300, 400);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);

        start.addActionListener(
                e -> {
                    dispose();
                    LoginPageUI loginPageUI = new LoginPageUI();
                    loginPageUI.setVisible(true);

                }
        );
        quit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
       MainUI mainUI=  new MainUI();
        mainUI.setVisible(true);
    }
}