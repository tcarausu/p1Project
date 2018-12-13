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
    private String region;

    private JButton next = new JButton("NEXT");
    private JButton skip = new JButton("SKIP");
    private JButton done = new JButton("DONE");

    private final String difficultyLevel = "easy";

    private JLabel question = new JLabel();

    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton radioButton1 = new JRadioButton();
    private JRadioButton radioButton2 = new JRadioButton();
    private JRadioButton radioButton3 = new JRadioButton();
    private JRadioButton radioButton4 = new JRadioButton();

    private JLabel currentNrOfQuestion = new JLabel();
    private JLabel totalNrOfQuestions = new JLabel();

    private JLabel timeSpentOnTheQuiz = new JLabel();
    private JLabel currentTimeInMin = new JLabel();
    private JLabel currentTimeInSec = new JLabel();

    private static int milliseconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;
    private static int time;

    private static boolean state = true;

    private Thread t = new Thread();

    /**
     * This constructor initiates the EasyQuestionUI window and initializes it
     * with a MyController Controller and QuestionController Controller
     *
     * @param controller  the MyController Controller needed to instantiate the constructor
     * @param qController the QuestionController Controller needed to instantiate the constructor
     * @param region      represent the region chosen in the Country UI and persisted through the Difficulty UI window
     */
    public EasyQuestionUI(MyController controller, QuestionController qController, String region) {

        super("EasyQuestion UI");
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

    }

    /**
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @SuppressWarnings("Duplicates")
    private void setEasy() throws SQLException {

        startTimer();

        super.add(question);
        super.add(currentNrOfQuestion);
        super.add(totalNrOfQuestions);

        super.add(timeSpentOnTheQuiz);
        super.add(currentTimeInMin);
        super.add(currentTimeInSec);

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

        setSize(750, 500);
        setLocation(500, 200);

        question.setBounds(50, 80, 600, 50);

        currentNrOfQuestion.setBounds(675, 0, 20, 20);
        totalNrOfQuestions.setBounds(705, 0, 20, 20);

        currentTimeInMin.setBounds(25, 0, 150, 20);
        timeSpentOnTheQuiz.setBounds(155, 0, 20, 20);
        currentTimeInSec.setBounds(185, 0, 100, 20);

        next.setBounds(600, 180, 110, 40);
        skip.setBounds(600, 260, 110, 40);
        done.setBounds(600, 340, 110, 40);

        radioButton1.setBounds(50, 250, 250, 50);
        radioButton2.setBounds(310, 250, 250, 50);
        radioButton3.setBounds(50, 200, 250, 50);
        radioButton4.setBounds(310, 200, 250, 50);

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
                time
        );

        AtomicInteger nrOfCurrentQAnswered = new AtomicInteger(controller.getNrOfQuestionsAnsweredFromCurrentQuiz
                (userIdForCurrentQuiz, difficultyLevel, highScore));

        currentNrOfQuestion.setText(
                nrOfCurrentQAnswered
                        + "/ ");

        totalNrOfQuestions.setText(
                String.valueOf(
                        controller.getNrOfQuestionsTotalFromCurrentQuiz(userIdForCurrentQuiz, difficultyLevel, highScore)));
        next.addActionListener(e ->
        {
            dispose();
            try {
                int value = nrOfCurrentQAnswered.getAndIncrement();
                int timeSpentOnAQuestion = Integer.parseInt(timeSpentOnTheQuiz.getText());

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
                        if (value >= 13) {
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
            stopTimer();
            controller.openScoreWindow();
        });


    }

    /**
     * @param answer
     * @param value
     * @param timeSpentOnAQuestion
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    private void validationOfRButton(String answer, int value, int timeSpentOnAQuestion) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer, value, difficultyLevel, timeSpentOnAQuestion);
        if (value >= 13) {
            dispose();
            t.stop();
            controller.openScoreWindowOnUser(controller.getCurrentUser().getUserName());

        } else {
            dispose();
            controller.openEasyWindow(region);

        }
    }

    /**
     *
     */
    @SuppressWarnings("Duplicates")
    private void stopTimer() {
        t.stop();
        done.addActionListener(e -> {
            state = false;
            System.out.printf("You finished in this time: %d h:%d m:%d s", hours, minutes, seconds);
            System.out.println();
            hours = 0;
            minutes = 0;
            seconds = 0;
            milliseconds = 0;
        });

    }

    /**
     * This method instantiates a thread to gather and sustain the
     * time spent on a quiz , incrementally.
     */
    @SuppressWarnings("Duplicates")
    private void startTimer() {

        state = true;

        t = new Thread(() -> {
            for (; ; )
                if (state) {
                    try {
                        Thread.sleep(1);

                        if (milliseconds > 762) {
                            milliseconds = 0;
                            seconds++;
                        }
                        if (seconds > 60) {
                            milliseconds = 0;
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes > 60) {
                            milliseconds = 0;
                            seconds = 0;
                            minutes = 0;
                            hours++;
                        }

                        milliseconds++;

                        time = (minutes + hours * 60 + seconds / 60);

                        timeSpentOnTheQuiz.setText(String.valueOf(time));
                        currentTimeInMin.setText("Time elapsed minutes: ");
                        currentTimeInSec.setText(" seconds: " + seconds);
                    } catch (Exception ignored) {

                    }

                } else {
                    break;
                }

        });
        t.start();


    }

}
