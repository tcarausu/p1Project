package view;


import controller.AdminController;
import controller.MyController;
import dao.Database;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.SQLException;

/**
 * File created on 118/9/2018
 * by Alex
 **/
public class HighScoreOnUser extends JFrame {
    private MyController controller;
    private String username;

    private JButton back = new JButton("Back");
    private AdminController aController;
    private Database database;

    /**
     * Admin Question Table Page  UI's Constructor
     *
     * @param controller of type MyController
     */
    public HighScoreOnUser(MyController controller,
                           AdminController aController,
                           Database database,
                           String username) {

        super("High Score");

        this.controller = controller;
        this.aController = aController;
        this.database = database;
        this.username = username;

        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            displayAllQuestionsTable(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void displayAllQuestionsTable(String username) throws SQLException {

        setBounds(0, 0, 500, 500);
        setLocation(500, 200);

        JPanel panel = new JPanel();
        TableModel tableModel = controller.buildTableModel(database.getHighScoreOnUser(username));
        JTable questionsTable = new JTable(tableModel);
        panel.add(new JScrollPane(questionsTable));

        back.setBounds(0, 150, 120, 40);
        panel.add(back);

        super.getContentPane().add(panel);

        back.addActionListener(ae -> {
            dispose();
            controller.start();
        });
    }


}
