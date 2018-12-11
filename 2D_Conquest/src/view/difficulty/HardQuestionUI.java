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
    private String region;
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
     * Hard Question UI's Constructor
     *
     * Easy Question UI's Constructor
     *
     * @param controller of type MyController
     */
    public HardQuestionUI(MyController controller, QuestionController qController, String region) {

        super("HardQuestion UI");
        this.controller = controller;
        this.region = region;
        this.qController = qController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            setHard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
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


        radioButton1.setText(qController.getAHardQuestionCorrectAnswer(region));
        radioButton2.setText(qController.getAHardQuestionCorrectAnswer(region));
        radioButton3.setText(qController.getAHardQuestionCorrectAnswer(region));
        radioButton4.setText(qController.getAHardQuestionCorrectAnswer(region));

        question.setText(qController.getAHardQuestion(region));

        String userName = controller.getUser().getUserName();
        String difficultyLevel = "hard";
        int userIdForCurrentQuiz = controller.getHighScoreId();
        int highScore = controller.getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        AtomicInteger timeSpent = new AtomicInteger(controller.timeSpent(userIdForCurrentQuiz, highScore, difficultyLevel));

        AtomicInteger nrOfCurrentQAnswered = new AtomicInteger(controller.getNrOfQuestionsAnsweredFromCurrentQuiz
                (userIdForCurrentQuiz, difficultyLevel, highScore));

        currentNrOfQuestion.setText(
                nrOfCurrentQAnswered
                        + "/");

        totalNrOfQuestions.setText(
                String.valueOf(
                        controller.getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, highScore)));

        next.addActionListener(e ->
        {
            dispose();
            try {
                int value = nrOfCurrentQAnswered.getAndIncrement();
                int timeSpentOnAQuestion = timeSpent.getAndIncrement();
                if (radioButton1.isSelected()) {
                    validationOfRButton(radioButton1.getText(),value, difficultyLevel,  timeSpentOnAQuestion);
                }
                if (radioButton2.isSelected()) {
                    validationOfRButton(radioButton2.getText(),value, difficultyLevel,  timeSpentOnAQuestion);
                }
                if (radioButton3.isSelected()) {
                    validationOfRButton(radioButton3.getText(),value, difficultyLevel,  timeSpentOnAQuestion);
                }
                if (radioButton4.isSelected()) {
                    validationOfRButton(radioButton4.getText(),value, difficultyLevel,  timeSpentOnAQuestion);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        done.addActionListener(e -> dispose());

    }
    private void validationOfRButton(String answer, int value, String difficultyLevel, int timeSpentOnAQuestion) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer,value, difficultyLevel,timeSpentOnAQuestion);
        if (value >= 20) {
            dispose();
            controller.openScoreWindow();

        } else {
            dispose();
            controller.openHardWindow(region);

        }
    }
}
