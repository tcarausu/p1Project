package view;

import javax.swing.*;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class HardQuestionUI extends JFrame {

    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");

    private JLabel hard = new JLabel("Hard");

    private JButton back = new JButton("Back");

    public HardQuestionUI() {

        super("HardQuestion UI");
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setQuestionsHard();
    }

    private void setQuestionsHard() {

        super.add(easy);
        super.add(medium);
        super.add(hard);
        super.add(back);

        setSize(500, 400);

        easy.setBounds(70, 50, 100, 40);
        medium.setBounds(200, 50, 100, 40);
        hard.setBounds(20, 50, 100, 40);

        back.setBounds(70, 150, 160, 40);

        easy.addActionListener(e -> dispose());
        medium.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    DifficultyLevelUI difficultyUI = new DifficultyLevelUI();
                    difficultyUI.setVisible(true);
                });
    }

}