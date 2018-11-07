package view;

import controller.MyController;

import javax.swing.*;


public class MainUI extends JFrame {

    private MyController myController;

    private JButton start = new JButton("Start");
    private JButton quit = new JButton("QUIT");

    /**
     */
    public MainUI(MyController myController) {
        super("Main UI");
        this.myController = myController;

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
                    LoginPageUI loginPageUI = new LoginPageUI(myController);
                    loginPageUI.setVisible(true);

                }
        );
        quit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        MyController myController = new MyController();
        MainUI mainUI = new MainUI(myController);
        mainUI.setVisible(true);
    }
}