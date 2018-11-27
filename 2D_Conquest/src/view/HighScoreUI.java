package view;

import controller.AdminController;
import controller.MyController;
import dao.Database;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.SQLException;

/**
 * File created on 11/9/2018
 * by Toader
 **/
public class HighScoreUI extends JFrame {
    private MyController controller;

    private JButton back = new JButton("Back");
    private AdminController aController;
    private Database database;

    /**
     * Admin Question Table Page  UI's Constructor
     *
     * @param controller of type MyController
     */
    public HighScoreUI(
            MyController controller,
            AdminController aController,
            Database database
    ) {

        super("All Questions Table");

        this.controller = controller;
        this.aController = aController;
        this.database = database;
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            displayAllQuestionsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void displayAllQuestionsTable() throws SQLException {

        super.setBounds(0, 0, 800, 200);
        setLocation(500, 300);

        JPanel panel = new JPanel();
        TableModel tableModel = controller.buildTableModel(database.getHighscore());
        JTable questionsTable = new JTable(tableModel);
        panel.add(new JScrollPane(questionsTable));

        back.setBounds(0, 150, 120, 40);
        panel.add(back);

        super.getContentPane().add(panel);

        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }


}
