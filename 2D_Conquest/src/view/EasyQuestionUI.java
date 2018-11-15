package view;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class EasyQuestionUI extends JFrame {

    private MyController controller;

    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");

    private JLabel easy = new JLabel("Easy");

    private JButton back = new JButton("Back");

    /**
     * Easy Question UI's Constructor
     * @param controller of type MyController
     */
    public EasyQuestionUI(MyController controller) {

        super("EasyQuestion UI");
        this.controller = controller;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setEasy();
    }

    private void setEasy() {

        super.add(easy);
        super.add(medium);
        super.add(hard);
        super.add(back);

        setSize(500, 400);

        easy.setBounds(20, 50, 100, 40);
        medium.setBounds(200, 50, 100, 40);
        hard.setBounds(330, 50, 100, 40);

        back.setBounds(70, 150, 160, 40);

        medium.addActionListener(e -> dispose());
        hard.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    controller.openDifficultyWindow();
                });
    }

}
