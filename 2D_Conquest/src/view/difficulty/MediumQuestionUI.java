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
     * This constructor initiates the MediumQuestionUI window and initializes it
     * with a MyController Controller and QuestionController Controller
     *
     * @param controller  the MyController Controller needed to instantiate the constructor
     * @param qController the QuestionController Controller needed to instantiate the constructor
     * @param region      represent the region chosen in the Country UI and persisted through the Difficulty UI window
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
            setMedium();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method instantiates the difficulty level UI buttons.
     * <p>
     * It will as well start the Thread for our timer,which is going to be used at the end
     * as a mean to display how much time an user spent on the game.
     * <p>
     * The window will have a question selected by checking for it not being used thus far
     * or having a random one at the start. The question is going to be selected from the pool
     * of the same region and difficulty selected before hand.
     * <p>
     * For each question there will be 4 answers referred to the question.
     * <p>
     * For gaining points an continuing to play the game the user is supposed to select an answer and press next for another question.
     * If the user will not choose an answer it will display an Selection Failure pop up, thus making the user try again.
     * <p>
     * The is the option of skipping a question as well, thus moving forward to another objective.
     *
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @SuppressWarnings("Duplicates")
    private void setMedium() throws SQLException {

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
                int questionsAnswered = nrOfCurrentQAnswered.getAndIncrement();
                int timeSpentOnAQuestion = timeSpent.getAndIncrement();

                if (radioButton1.isSelected()) {
                    validationOfRButton(radioButton1.getText(), questionsAnswered, timeSpentOnAQuestion);
                }
                if (radioButton2.isSelected()) {
                    validationOfRButton(radioButton2.getText(), questionsAnswered, timeSpentOnAQuestion);
                }
                if (radioButton3.isSelected()) {
                    validationOfRButton(radioButton3.getText(), questionsAnswered, timeSpentOnAQuestion);
                }
                if (radioButton4.isSelected()) {
                    validationOfRButton(radioButton4.getText(), questionsAnswered, timeSpentOnAQuestion);

                } else if (!radioButton1.isSelected() && !radioButton2.isSelected()
                        && !radioButton3.isSelected() && !radioButton4.isSelected()) {
                    controller.answerSelectionFailure();
                    controller.openMediumWindow(region);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });

        skip.addActionListener(e -> {
                    dispose();
                    try {
                        int questionsAnswered = nrOfCurrentQAnswered.getAndIncrement();
                        int timeSpentOnAQuestion = timeSpent.getAndIncrement();
                        controller.skipToNextQuestion(questionsAnswered, difficultyLevel, timeSpentOnAQuestion);
                        if (questionsAnswered >= 8) {
                            dispose();
                            controller.openScoreWindowOnUser(controller.getCurrentUser().getUserName());

                        } else {
                            dispose();
                            controller.openMediumWindow(region);
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
     * This constructor checks and validates the choosing and confirmation of a radio button by the user,
     * which ticks an answer chosen.
     *
     * @param answer               represents the answer chosen by the user during the quiz
     * @param questionsAnswered    represents the amount of questions answered thus far
     * @param timeSpentOnAQuestion represents the time that has passed
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    private void validationOfRButton(String answer, int questionsAnswered, int timeSpentOnAQuestion) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer, questionsAnswered, difficultyLevel, timeSpentOnAQuestion);
        if (questionsAnswered >= 8) {
            dispose();
            controller.openScoreWindowOnUser(controller.getCurrentUser().getUserName());

        } else {
            dispose();
            controller.openMediumWindow(region);

        }
    }

    /**
     * This method will stop Thread (the timer)
     * and returns the value to the initial state.
     */
    @SuppressWarnings("Duplicates")
    private void stopTimer() {
        t.stop();
        state = false;
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;

    }

    /**
     * This method instantiates a Thread to gather and sustain the
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
