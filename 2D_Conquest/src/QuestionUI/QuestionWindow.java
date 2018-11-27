package QuestionUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created by Jesus
 **/

public class QuestionWindow extends JFrame {

    JTextField question;
    JButton prev = new JButton("Previous");
    JButton next = new JButton("Next");
    JButton first = new JButton("First Answer");
    JButton second = new JButton("Second Answer");
    JButton third = new JButton("Third Answer");
    JButton forth = new JButton("Forth Answer");
    JButton exit = new JButton("EXIT");


    JPanel panel = new JPanel();

    JLabel label = new JLabel();


    private MyController controller;

    public QuestionWindow(MyController controller) {
        panel.setLayout(null);

        this.controller = controller;

        label.setSize(150, 50);
        add(label);

        next.setBounds(600, 120, 90, 25);
        prev.setBounds(50, 120, 90, 25);
        exit.setBounds(305, 400, 90, 25);

        first.setBounds(190, 250, 150, 50);
        second.setBounds(360, 250, 150, 50);
        third.setBounds(190, 310, 150, 50);
        forth.setBounds(360, 310, 150, 50);

        panel.add(next);
        panel.add(prev);
        panel.add(exit);
        panel.add(first);
        panel.add(second);
        panel.add(third);
        panel.add(forth);



        question = new JTextField();
        question.setSize(300, 150);
        question.setText("Here is the question? what about you?");
        question.setLocation(200, 50);
        question.setEditable(false);
        panel.add(question);

        getContentPane().add(panel);
        setSize(750, 500);
        setVisible(true);
        setResizable(false);
        setTitle("Question");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }


}
