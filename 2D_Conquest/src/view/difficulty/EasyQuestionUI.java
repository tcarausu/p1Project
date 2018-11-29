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

    private JLabel question = new JLabel();
    private JRadioButton[] selectionButtons = new JRadioButton[4];
    private int rbSelection = 0;
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

        super.add(next);
        super.add(skip);
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
        skip.setBounds(600, 260, 110, 40);
        done.setBounds(600, 340, 110, 40);

        radioButton1.setBounds(150, 250, 150, 50);
        radioButton2.setBounds(360, 250, 150, 50);
        radioButton3.setBounds(150, 200, 150, 50);
        radioButton4.setBounds(360, 200, 150, 50);

        question.setText(qController.getAnEasyQuestion(region));

        String questionToBeAnswered = question.getText();

        radioButton1.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(0));
        radioButton2.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(1));
        radioButton3.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(2));
        radioButton4.setText(qController.getAnEasyQuestionAnswerList(region, questionToBeAnswered).get(3));


        question.setText("This is The Question: " + questionToBeAnswered);

        String userName = controller.getUser().getUserName();
        String difficultyLevel = "easy";
        int highScore = controller.getHighScoreOnUserWithDifficultyLevel(userName, difficultyLevel);

        AtomicInteger nrOfCurrentQAnswered = new AtomicInteger(controller.getNrOfQuestionsAnsweredFromCurrentQuiz(userName,
                difficultyLevel, highScore));

        currentNrOfQuestion.setText(
                nrOfCurrentQAnswered
                        + "/");

        totalNrOfQuestions.setText(
                String.valueOf(
                        controller.getNrOfQuestionsTotalFromCurrentQuiz(userName, difficultyLevel, highScore)));

//        JPanel jp = new JPanel(new GridLayout(4, 1));
//        ButtonGroup group = new ButtonGroup();
//        for (int x = 0; x < selectionButtons.length; x++) {
//            selectionButtons[x] = new JRadioButton("RB " + x);
//            group.add(selectionButtons[x]);
//            jp.add(selectionButtons[x]);
//            selectionButtons[x].addKeyListener(new KeyAdapter() {
//                public void keyPressed(KeyEvent ke) {
//                    if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
//                        rbSelection = (rbSelection + 1) % selectionButtons.length;
//                        ke.consume();
//                        selectionButtons[rbSelection].setSelected(true);
//                        selectionButtons[rbSelection].requestFocusInWindow();
//                    }
//                }
//            });
//        }
//        super.add(jp);


//       String pizdeojCorrect = String.valueOf(qController.getAnEasyQuestionCorrectAnswer(radiobuttonText));
//        String pizdeojGresit = String.valueOf(qController.getAnEasyQuestionCorrectAnswer(radiobutton2Text));
//        System.out.println(pizdeojCorrect + "  hellnot " + pizdeojGresit);


//        radioButton1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                }
//            }
//        });

        next.addActionListener(e ->
        {
            dispose();
            try {
                int value = nrOfCurrentQAnswered.getAndIncrement();

                if (radioButton1.isSelected()) {
                    validationOfRButton(radioButton1.getText(), value, difficultyLevel);
                }
                if (radioButton2.isSelected()) {
                    validationOfRButton(radioButton2.getText(), value, difficultyLevel);
                }
                if (radioButton3.isSelected()) {
                    validationOfRButton(radioButton3.getText(), value, difficultyLevel);
                }
                if (radioButton4.isSelected()) {
                    validationOfRButton(radioButton4.getText(), value, difficultyLevel);
                } else if (!radioButton1.isSelected() && !radioButton2.isSelected()
                        && !radioButton3.isSelected() && !radioButton4.isSelected()) {
                    validationOfRButton("", value, difficultyLevel);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });

        skip.addActionListener(e -> {
                    dispose();
                    try {
                        int value = nrOfCurrentQAnswered.getAndIncrement();

                        controller.skipToNextQuestion(value, difficultyLevel);
                        if (value >= 20) {
                            dispose();
                            controller.openScoreWindow();

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
            controller.openScoreWindow();
        });


    }

    private void validationOfRButton(String answer, int value, String difficultyLevel) throws SQLException {
        controller.updateScoreOnForUserAndDifficulty(answer, value, difficultyLevel);
        if (value >= 20) {
            dispose();
            controller.openScoreWindow();

        } else {
            dispose();
            controller.openEasyWindow(region);

        }
    }
}
