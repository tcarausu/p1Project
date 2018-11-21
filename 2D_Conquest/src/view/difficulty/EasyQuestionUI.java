package view.difficulty;

import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class EasyQuestionUI extends JFrame {

    private MyController controller;

    private JButton next = new JButton("NEXT");
    private JButton done = new JButton("DONE");

    private JLabel question = new JLabel();
    private JRadioButton radioButton1 = new JRadioButton();
    private JRadioButton radioButton2 = new JRadioButton();
    private JRadioButton radioButton3 = new JRadioButton();
    private JRadioButton radioButton4 = new JRadioButton();
    private JButton back = new JButton("Back");

    /**
     * Easy Question UI's Constructor
     *
     * @param controller of type MyController
     */
    public EasyQuestionUI(MyController controller) {

        super("EasyQuestion UI");
        this.controller = controller;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            setEasy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setEasy() throws SQLException {

        super.add(question);
        super.add(next);
        super.add(done);
        super.add(back);

        super.add(radioButton1);
        super.add(radioButton2);
        super.add(radioButton3);
        super.add(radioButton4);

        setSize(600, 400);
        setLocation(500, 200);

        question.setBounds(20, 50, 450, 50);
        next.setBounds(175, 250, 125, 40);
        done.setBounds(325, 250, 125, 40);

        radioButton1.setBounds(25, 150, 125, 40);
        radioButton2.setBounds(175, 150, 125, 40);
        radioButton3.setBounds(325, 150, 125, 40);
        radioButton4.setBounds(475, 150, 125, 40);

        back.setBounds(200, 300, 150, 40);


        radioButton1.setText(controller.getAnEasyQuestionCorrectAnswer());
        radioButton2.setText(controller.getAnEasyQuestionCorrectAnswer());
        radioButton3.setText(controller.getAnEasyQuestionCorrectAnswer());
        radioButton4.setText(controller.getAnEasyQuestionCorrectAnswer());

        question.setText(controller.getAnEasyQuestion());

        next.addActionListener(e ->
        {
            dispose();
            controller.openEasyWindow();

        });
        done.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    controller.openDifficultyWindow();
                });
    }

}
