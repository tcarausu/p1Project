package view.difficulty;

import controller.MyController;
import controller.QuestionController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    private void setEasy() throws SQLException {

        super.add(question);
        super.add(currentNrOfQuestion);
        super.add(totalNrOfQuestions);

        super.add(next);
        super.add(done);

        ButtonGroup answers = new ButtonGroup();
        answers.add(radioButton1);
        answers.add(radioButton2);
        answers.add(radioButton3);
        answers.add(radioButton4);

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
        radioButton2.setText(qController.getAnEasyQuestionWrongAnswer());
        radioButton3.setText(qController.getAnEasyQuestionWrongAnswer());
        radioButton4.setText(qController.getAnEasyQuestionWrongAnswer());

        String radiobuttonText = radioButton1.getText();
        String radiobutton2Text = radioButton2.getText();

        question.setText(qController.getAnEasyQuestion(region));

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


        String pizdeojCorrect = String.valueOf(qController.getAnEasyQuestionCorrectAnswer(radiobuttonText));
        String pizdeojGresit = String.valueOf(qController.getAnEasyQuestionCorrectAnswer(radiobutton2Text));
        System.out.println(pizdeojCorrect + "  hellnot " + pizdeojGresit);

        if (radioButton1.isSelected()) {
//            radioButton1.addActionListener();
            radioButton1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        dispose();
                        try {
                            int value = nrOfCurrentQAnswered.getAndIncrement();

                            controller.updateScoreOnEasyForUser(value);
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
                }
            });
        }

       /* next.addActionListener(e ->
        {

        });*/

        done.addActionListener(e -> dispose());

    }

}
