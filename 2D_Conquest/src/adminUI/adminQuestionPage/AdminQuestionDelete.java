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
     * Admin Question Delete's Constructor.
     * <p>
     * Allows an admin to delete a question that has registered.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
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

        introduceAdminQuestionDelete();

    }
    /**
     * This method allows the administrator to delete existing question information credentials.
     * <p>
     * This method initiates the buttons and attaches them to the window.
     * <p>
     * To delete the question it can both do that from either the last field (region)
     * by using "Enter" or by manually clicking on the delete button.
     * <p>
     * If the user wants to go back to Admin UI it will use the Back button.
     */


    /**
     * This method allows the administrator to introduce new login information credentials.
     */
    @SuppressWarnings("Duplicates")
    private void introduceAdminQuestionDelete() {
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

    /**
     * This method deletes an existing question  from the database
     * by the Id of the question required (done by an administrator)
     * <p>
     * This method also checks for valid input (non null/empty).
     * <p>
     * In case of appropriate input the method will
     * verify the admin data used for deletion and and move forward.
     * <p>
     * As mentioned before if the data is invalid,
     * it will clear the fields and open the same window.
     */
    private void deleteQuestion() {
        String idText = id.getText();
        try {
            dispose();
            if (idText != null
                    && !idText.equals("")
            ) {
                qController.deleteQuestionAnswerByQuestionIdFromQuestionAnswer(Integer.parseInt(idText));
            } else {
                clearFieldsWhenNeeded();
                aController.openAdminQuestionUI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows login information fields to be cleared when
     * necessary to enter in new information or delete current input.
     */
    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }

}

