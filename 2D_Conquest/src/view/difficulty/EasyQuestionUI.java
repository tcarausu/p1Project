package view.difficulty;

import controller.MyController;
import controller.QuestionController;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

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
    public EasyQuestionUI(MyController controller, QuestionController qController, String region) {

        super("EasyQuestion UI");
        this.controller = controller;
        this.qController = qController;
        this.region = region;

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
        question.setBounds(100, 80, 450, 50);


        next.setBounds(600, 180, 110, 40);
        skip.setBounds(600, 260, 110, 40);
        done.setBounds(600, 340, 110, 40);

        radioButton1.setBounds(150, 250, 150, 50);
        radioButton2.setBounds(360, 250, 150, 50);
        radioButton3.setBounds(150, 200, 150, 50);
        radioButton4.setBounds(360, 200, 150, 50);

        startTimer();
//        timer2();
        question.setText(controller.questionToBeAnswered(difficultyLevel, region));

        String questionToBeAnswered = question.getText();

        radioButton1.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(0));
        radioButton2.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(1));
        radioButton3.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(2));
        radioButton4.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(3));

        question.setText("This is The Question: " + questionToBeAnswered);

        int userIdForCurrentQuiz = controller.getHighScoreId();
        int highScore = controller.getHighScoreOnUserWithDifficultyLevel(userIdForCurrentQuiz, difficultyLevel);
        AtomicInteger timeSpent = new AtomicInteger(
                controller.timeSpent(userIdForCurrentQuiz, highScore, difficultyLevel)
//                startTimer()
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
                        if (value >= 13) {
                            dispose();
                            controller.openScoreWindowOnUser(controller.getUser().getUserName());

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
//            stopTimer();
            controller.openScoreWindow();
        });


    }

    private void validationOfRButton(String answer, int value, int timeSpentOnAQuestion) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer, value, difficultyLevel, timeSpentOnAQuestion);
        if (value >= 13) {
            dispose();
            controller.openScoreWindowOnUser(controller.getUser().getUserName());

        } else {
            dispose();
            controller.openEasyWindow(region);

        }
    }

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

    private int startTimer() {

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
                        millisecond.setText(" : " + milliseconds);

                        milliseconds++;
                        second.setText(" : " + seconds);
                        minute.setText(" : " + minutes);
                        hour.setText("" + hours);
                    } catch (Exception ignored) {

                    }

                } else {
                    break;
                }

        });
        t.start();

        int result = minutes + (hours / 60) + (seconds / 60);
        return result;
    }

//    private void timer2() {
//        SwingWorker<String, Integer> timer = new SwingWorker<String, Integer>() {
//            Integer timer = 2;
//
//            @Override
//            protected String doInBackground() throws Exception {
//                //update guiModel
//                timeSpentOnTheQuiz.setText(timer.toString());
//                while (timer > 0) {
//                    Thread.sleep(1000);
//                    timer--;
//                }
//                return null;
//            }
//
//            @Override
//            public void done() {
//                addActionListener(e -> {
//                    dispose();
////            stopTimer();
//                    controller.openScoreWindow();
//                });
//            }
//        };
//
//    }
}
