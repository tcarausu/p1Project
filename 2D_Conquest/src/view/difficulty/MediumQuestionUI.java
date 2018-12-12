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
public class MediumQuestionUI extends JFrame {

    private MyController controller;
    private QuestionController qController;
    private String region;

    private JButton next = new JButton("NEXT");
    private JButton skip = new JButton("SKIP");
    private JButton done = new JButton("DONE");

    private final String difficultyLevel = "medium";

    private JLabel question = new JLabel();
    private JRadioButton radioButton1 = new JRadioButton();
    private JRadioButton radioButton2 = new JRadioButton();
    private JRadioButton radioButton3 = new JRadioButton();
    private JRadioButton radioButton4 = new JRadioButton();

    private ButtonGroup buttonGroup = new ButtonGroup();

    private JLabel currentNrOfQuestion = new JLabel();
    private JLabel timeSpentOnTheQuiz = new JLabel();
    private JLabel totalNrOfQuestions = new JLabel();

    private static int milliseconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;

    private static boolean state = true;

    private JLabel hour = new JLabel("00: ");
    private JLabel minute = new JLabel("00: ");
    private JLabel second = new JLabel("00: ");
    private JLabel millisecond = new JLabel("00");

    private Thread t = new Thread();

    /**
     * Easy Question UI's Constructor
     *
     * @param controller of type MyController
     */
    public MediumQuestionUI(MyController controller, QuestionController qController, String region) {

        super("Medium Question UI");
        this.controller = controller;
        this.qController = qController;
        this.region = region;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            setEasy();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (t.isDaemon()) {
            System.exit(0);
        }
    }

    @SuppressWarnings("Duplicates")
    private void setEasy() throws SQLException {


        super.add(question);
        super.add(currentNrOfQuestion);
        super.add(totalNrOfQuestions);
        super.add(timeSpentOnTheQuiz);

        super.add(next);
        super.add(skip);
        super.add(done);

        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);

        super.add(radioButton1);
        super.add(radioButton2);
        super.add(radioButton3);
        super.add(radioButton4);

        super.add(hour);
        super.add(minute);
        super.add(second);
        super.add(millisecond);

        hour.setBounds(10, 10, 30, 30);
        minute.setBounds(40, 10, 30, 30);
        second.setBounds(70, 10, 30, 30);
        millisecond.setBounds(110, 10, 30, 30);

        setSize(750, 500);
        setLocation(500, 200);

        currentNrOfQuestion.setBounds(675, 0, 30, 20);
        totalNrOfQuestions.setBounds(725, 0, 30, 20);
        timeSpentOnTheQuiz.setBounds(325, 0, 30, 20);
        question.setBounds(50, 80, 600, 50);


        next.setBounds(600, 180, 110, 40);
        skip.setBounds(600, 260, 110, 40);
        done.setBounds(600, 340, 110, 40);

        radioButton1.setBounds(100, 250, 200, 50);
        radioButton2.setBounds(360, 250, 200, 50);
        radioButton3.setBounds(100, 200, 200, 50);
        radioButton4.setBounds(360, 200, 200, 50);

        EasyQuestionUI easyQuestionUI = new EasyQuestionUI(controller, qController, region);
        easyQuestionUI.startTimer();

        question.setText(controller.questionToBeAnswered(difficultyLevel, region));

        String questionToBeAnswered = question.getText();

        radioButton1.setText(controller.answerForQuestion(difficultyLevel, region, questionToBeAnswered));
        radioButton2.setText(controller.answerForQuestion(difficultyLevel, region, questionToBeAnswered));
        radioButton3.setText(controller.answerForQuestion(difficultyLevel, region, questionToBeAnswered));
        radioButton4.setText(controller.answerForQuestion(difficultyLevel, region, questionToBeAnswered));

        question.setText("This is The Question: " + questionToBeAnswered);

        int userIdForCurrentQuiz = controller.getHighScoreId();
        int highScore = controller.getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        AtomicInteger timeSpent = new AtomicInteger(
                controller.timeSpent(userIdForCurrentQuiz, highScore, difficultyLevel)
        );

        AtomicInteger nrOfCurrentQAnswered = new AtomicInteger(controller.getNrOfQuestionsAnsweredFromCurrentQuiz
                (userIdForCurrentQuiz, difficultyLevel, highScore));

        timeSpentOnTheQuiz.setText(String.valueOf(timeSpent));

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
                    validationOfRButton(radioButton1.getText(), value, timeSpentOnAQuestion);
                }
                if (radioButton2.isSelected()) {
                    validationOfRButton(radioButton2.getText(), value, timeSpentOnAQuestion);
                }
                if (radioButton3.isSelected()) {
                    validationOfRButton(radioButton3.getText(), value, timeSpentOnAQuestion);
                }
                if (radioButton4.isSelected()) {
                    validationOfRButton(radioButton4.getText(), value, timeSpentOnAQuestion);

                } else if (!radioButton1.isSelected() && !radioButton2.isSelected()
                        && !radioButton3.isSelected() && !radioButton4.isSelected()) {
                    controller.answerSelectionFailure();
                    controller.openEasyWindow(region);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });

        skip.addActionListener(e -> {
                    dispose();
                    try {
                        int value = nrOfCurrentQAnswered.getAndIncrement();
                        int timeSpentOnAQuestion = timeSpent.getAndIncrement();
                        controller.skipToNextQuestion(value, difficultyLevel, timeSpentOnAQuestion);
                        if (value >= 8) {
                            dispose();
                            controller.openScoreWindowOnUser(controller.getCurrentUser().getUserName());

                        } else {
                            dispose();
                            controller.openEasyWindow(region);

                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        done.addActionListener(e -> {
            dispose();
            easyQuestionUI.stopTimer();
            controller.openScoreWindow();
        });

    }

    private void validationOfRButton(String answer, int value, int timeSpentOnAQuestion) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer, value, difficultyLevel, timeSpentOnAQuestion);
        if (value >= 8) {
            dispose();
            controller.openScoreWindowOnUser(controller.getCurrentUser().getUserName());

        } else {
            dispose();
            controller.openEasyWindow(region);

        }
    }

}
