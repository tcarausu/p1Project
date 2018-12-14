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
public class AdminQuestionEdit extends JFrame {

    private MyController controller;
    private AdminController aController;
    private QuestionController qController;

    private JButton editQuestion = new JButton("Edit Question");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField();
    private JTextField subject = new JTextField();
    private JTextField typeQ = new JTextField();
    private JTextField diff = new JTextField();
    private JTextField region = new JTextField();

    private JLabel userId = new JLabel("Question ID");
    private JLabel subjectOfQ = new JLabel("Question Subject");
    private JLabel typeOfQ = new JLabel("Type Of Question");
    private JLabel difficultyLevelOfQ = new JLabel("Difficulty Level");
    private JLabel regionOfQ = new JLabel("Region Of Question");

    /**
     * Admin User Edit's Constructor.
     * <p>
     * Allows admin to edit a question in the database
     * and edit its information in game.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param qController represent the QuestionController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminQuestionEdit(MyController controller, QuestionController qController,
                             AdminController aController) {

        super("Edit An Question UI");

        this.controller = controller;
        this.aController = aController;
        this.qController = qController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceAdminQuestionEdit();

    }

    /**
     * This method allows the administrator to edit existing question information credentials.
     * <p>
     * This method initiates the buttons and attaches them to the window.
     * <p>
     * To edit the question it can both do that from either the last field (region)
     * by using "Enter" or by manually clicking on the Edit button.
     * <p>
     * If the user wants to go back to Admin UI it will use the Back button.
     */
    private void introduceAdminQuestionEdit() {
        setSize(500, 400);
        setLocation(500, 280);

        id.setBounds(160, 30, 250, 20);
        subject.setBounds(160, 60, 250, 20);
        typeQ.setBounds(160, 90, 250, 20);
        diff.setBounds(160, 120, 250, 20);
        region.setBounds(160, 150, 250, 20);

        userId.setBounds(20, 30, 140, 20);
        subjectOfQ.setBounds(20, 60, 140, 20);
        typeOfQ.setBounds(20, 90, 140, 20);
        difficultyLevelOfQ.setBounds(20, 120, 140, 20);
        regionOfQ.setBounds(20, 150, 140, 20);


        editQuestion.setBounds(30, 250, 120, 40);
        back.setBounds(350, 250, 120, 40);

        super.add(id);
        super.add(subject);
        super.add(typeQ);
        super.add(diff);
        super.add(region);

        super.add(userId);
        super.add(subjectOfQ);
        super.add(typeOfQ);
        super.add(difficultyLevelOfQ);
        super.add(regionOfQ);


        super.add(editQuestion);

        super.add(back);

        region.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    editQuestion();
                }
            }
        });


        editQuestion.addActionListener(ae -> {
            editQuestion();
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    /**
     * This method edits existing questions from the database
     * by the Id of the question required (done by an administrator).
     * <p>
     * This method also checks for valid input (non null/empty).
     * <p>
     * In case of appropriate input the method will
     * verify the admin data used for edits and and move forward.
     * <p>
     * As mentioned before if the data is invalid,
     * it will clear the fields and open the same window.
     */
    private void editQuestion() {
        String idText = id.getText();
        String subjectText = subject.getText();
        String typeOfQText = typeQ.getText();
        String diffLevelText = diff.getText();
        String regionText = region.getText();
        try {
            dispose();
            if (idText != null
                    && !idText.equals("")
            ) {
                qController.updateQuestionById(Integer.parseInt(idText), subjectText, typeOfQText,
                        diffLevelText, regionText);
            } else {
                clearFieldsWhenNeeded();
                aController.openAdminUserUI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows login information fields to be cleared when necessary to enter in new information or delete current input.
     */
    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }

}