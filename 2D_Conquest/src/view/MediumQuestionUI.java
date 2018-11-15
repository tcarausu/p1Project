package view;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class MediumQuestionUI extends JFrame {

    private MyController controller;

    private JButton easy = new JButton("Easy");
    private JButton hard = new JButton("Hard");

    private JLabel medium = new JLabel("Medium");

    private JButton back = new JButton("Back");

    /**
     * Medium Question UI's Constructor
     * @param controller of type MyController
     */
    public MediumQuestionUI(MyController controller) {

        super("MediumQuestion UI");
        this.controller = controller;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setMedium();
    }

    private void setMedium() {

        super.add(easy);
        super.add(medium);
        super.add(hard);
        super.add(back);

        setSize(500, 400);

        easy.setBounds(70, 50, 100, 40);
        medium.setBounds(20, 50, 100, 40);
        hard.setBounds(330, 50, 100, 40);

        back.setBounds(70, 150, 160, 40);

        easy.addActionListener(e -> dispose());
        hard.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    controller.openDifficultyWindow();
                });
    }

}
