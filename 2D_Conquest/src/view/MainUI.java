package view;

import controller.MyController;
import dao.Database;

import javax.swing.*;
import java.sql.SQLException;


public class MainUI extends JFrame {

    private MyController ctrler;

    private JButton start = new JButton("Start");
    private JButton highScore = new JButton("High Score");
    private JButton adminUI = new JButton("Admin Panel");

    private JButton quit = new JButton("QUIT");

    /**
     * Main UI's Constructor
     * @param ctrler
     */
    public MainUI(MyController ctrler) {
        super("Main UI");
        this.ctrler = ctrler;

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

        setSize(300, 500);

        start.setBounds(70, 50, 160, 40);
        highScore.setBounds(70, 150, 160, 40);
        adminUI.setBounds(70, 250, 160, 40);
        quit.setBounds(70, 350, 160, 40);

        start.addActionListener(
                e -> {
                    dispose();
                    ctrler.openLoginWindow();

                }
        );
        highScore.addActionListener(
                e -> {
                    dispose();
                    ctrler.openScoreWindow();

                }
        );
        adminUI.addActionListener(
                e -> {
                    dispose();
                    ctrler.openAdminPageUI();

                }
        );
        quit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) throws SQLException {
        Database database = new Database();
        MyController myController = new MyController(database);
        myController.start();
    }
}