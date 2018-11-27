package view.difficulty;

import controller.MyController;
import controller.QuestionController;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File created on 11/1/2018
 * by Toader
 **/
public class EasyQuestionUI extends JFrame {

    private MyController controller;
    private QuestionController qController;

    private JButton next = new JButton("NEXT");
    private JButton done = new JButton("DONE");

    private JLabel question = new JLabel();
    private JRadioButton radioButton1 = new JRadioButton();
    private JRadioButton radioButton2 = new JRadioButton();
    private JRadioButton radioButton3 = new JRadioButton();
    private JRadioButton radioButton4 = new JRadioButton();

    private JLabel currentNrOfQuestion = new JLabel();
    private JLabel totalNrOfQuestions = new JLabel();


    /**
     * Easy Question UI's Constructor
     *
     * @param controller of type MyController
     */
    public EasyQuestionUI(MyController controller, QuestionController qController) {

        super("EasyQuestion UI");
        this.controller = controller;
        this.qController = qController;

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
        super.add(currentNrOfQuestion);
        super.add(totalNrOfQuestions);

        super.add(next);
        super.add(done);

        super.add(radioButton1);
        super.add(radioButton2);
        super.add(radioButton3);
        super.add(radioButton4);

        setSize(750, 500);
        setLocation(500, 200);

        currentNrOfQuestion.setBounds(675, 0, 30, 20);
        totalNrOfQuestions.setBounds(725, 0, 30, 20);
        question.setBounds(100, 80, 450, 50);


        next.setBounds(600, 180, 110, 40);
        done.setBounds(600, 340, 110, 40);

        radioButton1.setBounds(150, 250, 150, 50);
        radioButton2.setBounds(360, 250, 150, 50);
        radioButton3.setBounds(150, 200, 150, 50);
        radioButton4.setBounds(360, 200, 150, 50);


        radioButton1.setText(qController.getAnEasyQuestionCorrectAnswer());
        radioButton2.setText(qController.getAnEasyQuestionCorrectAnswer());
        radioButton3.setText(qController.getAnEasyQuestionCorrectAnswer());
        radioButton4.setText(qController.getAnEasyQuestionCorrectAnswer());

        question.setText(qController.getAnEasyQuestion());

        String userName = controller.getUser().getUserName();
        String difficultyLevel = "easy";
        int highscore = controller.getHighScoreOnUserWithDifficultyLevel(userName, difficultyLevel);

        AtomicInteger nrOfCurrentQAnswered = new AtomicInteger(controller.getNrOfQuestionsAnsweredFromCurrentQuiz(userName,
                difficultyLevel, highscore));

        currentNrOfQuestion.setText(
                nrOfCurrentQAnswered
                        + "/");

        totalNrOfQuestions.setText(
                String.valueOf(
                        controller.getNrOfQuestionsTotalFromCurrentQuiz(userName, difficultyLevel, highscore)));

        next.addActionListener(e ->
        {
            dispose();
            try {
                int value = nrOfCurrentQAnswered.getAndIncrement();

                controller.updateScoreOnEasyForUser(value);
                if (value >= 20) {
                    dispose();
                    controller.openScoreWindow();

                } else {
                    dispose();
                    controller.openEasyWindow();

                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        done.addActionListener(e -> dispose());

    }

}
