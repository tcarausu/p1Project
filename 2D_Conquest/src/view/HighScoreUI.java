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
     * HighScore table Page  UI's Constructor.
     * <p>
     * Displays the list of higScore entries from the database.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     * @param database    represent the Database Dao Layer needed to instantiate the constructor
     */
    public HighScoreUI(
            MyController controller,
            AdminController aController,
            Database database
    ) {

        super("High Score");

        this.controller = controller;
        this.aController = aController;
        this.database = database;
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            displayAllHighScoreDataTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method create an visual interpretation for the HighScore table of the
     * database.
     *
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @SuppressWarnings("Duplicates")
    private void displayAllHighScoreDataTable() throws SQLException {

        super.setBounds(0, 0, 500, 500);
        setLocation(500, 200);

        JPanel panel = new JPanel();
        TableModel tableModel = controller.buildTableModel(database.getHighScore());
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
