package view;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class DifficultyLevelUI extends JFrame {

    private MyController myController;

    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");

    private JButton back = new JButton("Back");

    public DifficultyLevelUI(MyController myController) {

        super("DifficultyUI UI");
        this.myController = myController;

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

        easy.setBounds(70, 50, 100, 40);
        medium.setBounds(200, 50, 100, 40);
        hard.setBounds(330, 50, 100, 40);

        back.setBounds(70, 150, 160, 40);

        easy.addActionListener(e -> {
            dispose();
            EasyQuestionUI easyUI = new EasyQuestionUI(myController);
            easyUI.setVisible(true);
        });
        medium.addActionListener(e -> {
            dispose();
            MediumQuestionUI mediumUI = new MediumQuestionUI(myController);
            mediumUI.setVisible(true);
        });
        hard.addActionListener(e -> {
            dispose();
            HardQuestionUI hardUI = new HardQuestionUI(myController);
            hardUI.setVisible(true);
        });

        back.addActionListener(
                e -> {
                    dispose();
                    CountryUI countryUI = new CountryUI(myController);
                    countryUI.setVisible(true);
                });
    }

}
