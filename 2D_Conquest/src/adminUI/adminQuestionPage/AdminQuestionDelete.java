package adminUI.adminQuestionPage;

import controller.AdminController;
import controller.MyController;
import controller.QuestionController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * File created on 11/17/2018
 * by Toader
 **/
public class AdminQuestionDelete extends JFrame {

    private MyController controller;
    private QuestionController qController;
    private AdminController aController;

    private JButton deleteQuestion = new JButton("Delete Question");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField();

    private JLabel questionId = new JLabel("Question ID");

    /**
     * Admin Quest Delete's Constructor
     *
     * @param controller of type MyController
     */
    public AdminQuestionDelete(MyController controller, QuestionController qController,
                               AdminController aController) {

        super("Delete An Question UI");

        this.controller = controller;
        this.qController = qController;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(500, 200);
        setLocation(500, 280);


        questionId.setBounds(20, 30, 140, 20);
        id.setBounds(160, 30, 250, 20);

        deleteQuestion.setBounds(30, 100, 120, 40);
        back.setBounds(350, 100, 120, 40);

        super.add(questionId);
        super.add(deleteQuestion);

        super.add(back);
        super.add(id);

        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    deleteQuestion();
                }
            }
        });


        deleteQuestion.addActionListener(ae -> {
            deleteQuestion();
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    private void deleteQuestion() {
        String idText = id.getText();
        try {
            dispose();
            if (idText != null
                    && !idText.equals("")
            ) {
                qController.deleteQuestionById(Integer.parseInt(idText));
            } else {
                clearFieldsWhenNeeded();
                aController.openAdminQuestionUI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }


}

