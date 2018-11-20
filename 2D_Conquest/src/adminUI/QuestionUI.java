package adminUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/20/2018
 * by Toader
 **/
public class QuestionUI extends JFrame {

    private MyController controller;

    /**
     * @param controller
     */
    public QuestionUI(MyController controller) {
        super("Main UI");
        this.controller = controller;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        displayQuestion();
    }

    /**
     */
    private void displayQuestion() {

    }
}
