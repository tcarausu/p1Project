package view;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/9/2018
 * by Toader
 **/
public class HighScoreUI extends JFrame {
    private MyController controller;

    private JButton back = new JButton("Back");
    private JButton openMe = new JButton("Easy");
    private JLabel hscore = new JLabel("Highscore");

    /**
     * High Score UI's Constructor
     * @param controller of type MyController
     */
    public HighScoreUI(MyController controller) {

        super("Highscore UI");
        this.controller = controller;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setHighScore();
    }

    private void setHighScore() {

        super.add(openMe);
        super.add(back);
        super.add(hscore);

        setSize(300, 400);

        openMe.setBounds(100, 50, 100, 40);
        back.setBounds(70, 150, 160, 40);
        hscore.setBounds(20, 50, 120, 20);

        openMe.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    controller.start();
                });
    }
}