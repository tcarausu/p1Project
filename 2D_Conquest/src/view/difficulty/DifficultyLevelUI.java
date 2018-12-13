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
    private String region;

    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");

    private JButton back = new JButton("Back");

    /**
     * This constructor initiates the DifficultyLevelUI window and initializes it
     * with a MyController Controller
     *
     * @param controller represents the MyController Controller needed to instantiate the constructor
     * @param region     represents the current selected region at start of difficulty selection
     */
    public DifficultyLevelUI(MyController controller, String region) {

        super("DifficultyUI UI");
        this.controller = controller;
        this.region = region;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        setLocation(500, 200);

        easy.setBounds(70, 100, 100, 40);
        medium.setBounds(200, 100, 100, 40);
        hard.setBounds(330, 100, 100, 40);

        back.setBounds(68, 200, 362, 40);

        easy.addActionListener(e -> {
            dispose();
            try {
                controller.startQuiz(20, "easy");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openEasyWindow(region);
        });

        medium.addActionListener(e -> {
            dispose();

            try {
                controller.startQuiz(20, "medium");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openMediumWindow(region);
        });
        hard.addActionListener(e -> {
            dispose();

            try {
                controller.startQuiz(20, "hard");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            controller.openHardWindow(region);
        });

        back.addActionListener(
                e -> {
                    dispose();
                    controller.openCountryWindow();
                });
    }

}
