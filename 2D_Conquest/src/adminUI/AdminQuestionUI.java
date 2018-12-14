package adminUI;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminQuestionUI extends JFrame {
    private MyController controller;
    private AdminController aController;

    private JButton addQuestion = new JButton("Create Question");
    private JButton deleteQuestion = new JButton("Delete Question");
    private JButton editQuestion = new JButton("Edit Question");
    private JButton displayAllQuestions = new JButton("Display QuestionTable");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");


    /**
     * Admin Question UI's Constructor
     * <p>
     * Creates Admin Question window for editing the questions from database.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminQuestionUI(MyController controller, AdminController aController) {
        super("AdminPage Questions UI");
        this.controller = controller;
        this.aController = aController;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceQuestionButtons();
    }

    /**
     * This method allows the administrator to introduce new question
     * delete,edit existing questions or display all question.
     * <p>
     * This method instantiates the buttons and attaches them to the window.
     * <p>
     * Question are added and edited by using the buttons with name of action selected.
     * <p>
     * For going back to AdminPage UI is used Back buttom
     */
    @SuppressWarnings("Duplicates")
    private void introduceQuestionButtons() {

        setSize(600, 400);
        super.add(addQuestion);
        super.add(deleteQuestion);
        super.add(editQuestion);
        super.add(displayAllQuestions);

        super.add(quit);
        super.add(back);

        AdminPageUI.uiSetup(addQuestion, deleteQuestion, editQuestion, displayAllQuestions, back, quit);


        addQuestion.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminQuestionCreateUI();

                }
        );
        deleteQuestion.addActionListener(
                e -> {
                    dispose();
                    controller.openAdminQuestionDeleteUI();

                }
        );
        editQuestion.addActionListener(
                e -> {
                    dispose();
                    controller.openAdminQuestionEditUI();

                }
        );
        displayAllQuestions.addActionListener(
                e -> {
                    dispose();
                    controller.openAdminFullQuestionTable();

                }
        );
        back.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminPageUI();

                }
        );
        quit.addActionListener(e -> dispose());
    }

}


