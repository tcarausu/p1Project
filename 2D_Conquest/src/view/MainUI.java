package view;

import controller.AdminController;
import controller.MyController;
import controller.QuestionController;
import dao.Database;
import dao.QuestionDao;
import dao.UserDao;

import javax.swing.*;


public class MainUI extends JFrame {

    private MyController controller;
    private AdminController aController;
    private QuestionController qController;

    private JButton start = new JButton("Start");
    private JButton highScore = new JButton("High Score");
    private JButton adminUI = new JButton("Admin Panel");

    private JButton quit = new JButton("QUIT");

    /**
     * Main UI's Constructor
     *
     * @param controller of type MyController
     */
    public MainUI(MyController controller,AdminController aController,QuestionController qController) {
        super("Main UI");
        this.controller = controller;
        this.aController = aController;
        this.qController = qController;

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

        super.add(start);
        super.add(quit);
        super.add(highScore);
        super.add(adminUI);

        setSize(300, 385);

        start.setBounds(70, 50, 160, 40);
        highScore.setBounds(70, 150, 160, 40);
        quit.setBounds(70, 250, 160, 40);

        start.addActionListener(
                e -> {
                    dispose();
                    controller.openLoginWindow();

                }
        );
        highScore.addActionListener(
                e -> {
                    dispose();
                    controller.openScoreWindow();

                }
        );
        adminUI.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminPageUI();

                }
        );
        quit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        Database database = new Database();
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();

        AdminController aController = new AdminController(userDao);
        QuestionController qController = new QuestionController(questionDao);
        MyController myController = new MyController(database,  aController, qController);

        aController.setMainControllerAdminController(myController);
        qController.setMainControllerQuestionController(myController);

        myController.start();
    }
}