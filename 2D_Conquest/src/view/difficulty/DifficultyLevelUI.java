package view.difficulty;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class DifficultyLevelUI extends JFrame {

    private MyController controller;

    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");

    private JButton back = new JButton("Back");

    /**
     * DifficultyLevel UI's Constructor
     * @param controller of type MyController
     */
    public DifficultyLevelUI(MyController controller) {

        super("DifficultyUI UI");
        this.controller = controller;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        difficultyButtons();
    }

    /**
     * sets up the functionality for the Country "UI"
     */
    private void difficultyButtons() {

        super.add(easy);
        super.add(medium);
        super.add(hard);
        super.add(back);

        setSize(500, 400);
        setLocation(500,200);

        easy.setBounds(70, 100, 100, 40);
        medium.setBounds(200, 100, 100, 40);
        hard.setBounds(330, 100, 100, 40);

        back.setBounds(68, 200, 362, 40);

        easy.addActionListener(e -> {
            dispose();
            try {
                controller.startEasyQuiz(20,"easy");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openEasyWindow();
        });

        medium.addActionListener(e -> {
            dispose();
            try {
                controller.startMediumQuiz(20,"medium");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openMediumWindow();
        });
        hard.addActionListener(e -> {
            dispose();
            try {
                controller.startHardQuiz(controller.getUser().getUserName(),20,"hard");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openHardWindow();
        });

        back.addActionListener(
                e -> {
                    dispose();
                    controller.openCountryWindow();
                });
    }

}