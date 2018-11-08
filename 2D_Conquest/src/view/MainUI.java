package view;

import controller.MyController;
import dao.Database;

import javax.swing.*;
import java.sql.SQLException;


public class MainUI extends JFrame {

    private MyController ctrler;

    private JButton start = new JButton("Start");
    private JButton quit = new JButton("QUIT");

    /**
     */
    public MainUI(MyController ctrler) {
        super("Main UI");
        this.ctrler = ctrler;

        setLocation(500, 500);
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

        setSize(300, 400);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);

        start.addActionListener(
                e -> {
                    dispose();
                    ctrler.openLoginWindow();

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