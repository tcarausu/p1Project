package view.difficulty;

import controller.MyController;
import controller.QuestionController;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File created on 11/1/2018
 * by Alex
 **/
public class HardQuestionUI extends JFrame {

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
    public HardQuestionUI(MyController controller, QuestionController qController) {

        super("HardQuestion UI");
        this.controller = controller;
        this.qController = qController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            setHard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setHard() throws SQLException {

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

        currentNrOfQuestion.setBounds(500, 0, 30, 20);
        totalNrOfQuestions.setBounds(550, 0, 30, 20);
        question.setBounds(20, 50, 450, 50);


        next.setBounds(600, 180, 110, 40);
        done.setBounds(600, 240, 110, 40);

        radioButton1.setBounds(190, 250, 150, 50);
        radioButton2.setBounds(360, 250, 150, 50);
        radioButton3.setBounds(190, 200, 150, 50);
        radioButton4.setBounds(360, 200, 150, 50);


        radioButton1.setText(qController.getAnHardQuestionCorrectAnswer());
        radioButton2.setText(qController.getAnHardQuestionCorrectAnswer());
        radioButton3.setText(qController.getAnHardQuestionCorrectAnswer());
        radioButton4.setText(qController.getAnHardQuestionCorrectAnswer());

        question.setText(qController.getAnHardQuestion());

        String userName = controller.getUser().getUserName();
        String difficultyLevel = "hard";
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

                controller.updateScoreOnHardForUser(value);
                if (value >= 20) {
                    dispose();
                    controller.openScoreWindow();

                } else {
                    dispose();
                    controller.openHardWindow();

                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        done.addActionListener(e -> dispose());

    }

}
