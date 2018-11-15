package adminUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminQuestionUI extends JFrame {
    private MyController controller;

    private JButton addQuestion = new JButton("Create Question");
    private JButton deleteQuestion = new JButton("Delete Question");
    private JButton editQuestion = new JButton("Edit Question");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

//    private JButton setttingsMenu = new JButton("High Score");
//    private JButton playGame = new JButton("Start");
//
   /** Admin Question UI's Constructor
    * @param controller of type MyController
    */
    public AdminQuestionUI(MyController controller) {
        super("AdminPage Questions UI");
        this.controller = controller;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceButton();
    }

    /**
     */
    private void introduceButton() {

        setSize(600, 400);
        super.add(addQuestion);
        super.add(deleteQuestion);
        super.add(editQuestion);

        super.add(quit);
        super.add(back);

//        super.add(setttingsMenu);
//        super.add(playGame);
//        setttingsMenu.setBounds(70, 150, 160, 40);
//        playGame.setBounds(70, 150, 160, 40);
//
        addQuestion.setBounds(70, 50, 160, 40);
        deleteQuestion.setBounds(250, 50, 160, 40);
        editQuestion.setBounds(70, 150, 160, 40);


        back.setBounds(70, 250, 160, 40);
        quit.setBounds(250, 250, 160, 40);



        addQuestion.addActionListener(
                e -> {
                    dispose();
                    controller.openLoginWindow();

                }
        );
        deleteQuestion.addActionListener(
                e -> {
                    dispose();
                    controller.openScoreWindow();

                }
        );
        editQuestion.addActionListener(
                e -> {
                    dispose();
                    controller.openScoreWindow();

                }
        );
        back.addActionListener(
                e -> {
                    dispose();
                    controller.openAdminPageUI();

                }
        );
        quit.addActionListener(e -> dispose());
    }

}

