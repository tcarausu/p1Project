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

    private JButton quit = new JButton("QUIT");

    /**
     * Main Application UI's Constructor
     * <p>
     * This constructor instantiates all the controller, thus method which later on will connect the
     * application to the database to retrieve data from.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     * @param qController represent the QuestionController Controller needed to instantiate the constructor
     */
    public MainUI(MyController controller, AdminController aController, QuestionController qController) {
        super("Main UI");
        this.controller = controller;
        this.aController = aController;
        this.qController = qController;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceButton();
    }

    /**
     * This method introduces the buttons and other extremities.
     * <p>
     * For the Start button it open up the Login Page. Thus providing the user or admin
     * with a way to login, and create an user if necessary.
     * <p>
     * For the HighScore button, anyone could see the highScore any of the players have received.
     * <p>
     * Lastly the user can quit the application using the quit button.
     */
    private void introduceButton() {

        super.add(start);
        super.add(quit);
        super.add(highScore);

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

        quit.addActionListener(e -> dispose());
    }

    /**
     * This main methods initiates all the Dao classes,
     * all the controller classes and initiating the connection
     * between them so that it could properly start the application
     *
     * @param args are the initialisation for the main
     */
    public static void main(String[] args) {
        Database database = new Database();
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();

        AdminController aController = new AdminController(userDao);
        QuestionController qController = new QuestionController(questionDao);
        MyController myController = new MyController(database, aController, qController);

        aController.setMainControllerAdminController(myController);
        qController.setMainControllerQuestionController(myController);

        myController.start();
    }
}