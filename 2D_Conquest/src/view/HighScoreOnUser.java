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
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     * @param database    represent the Database  Dao Layer class needed to instantiate the constructor
     * @param username    represent the username of the User the highScore should be displayed for
     */
    public HighScoreOnUser(MyController controller,
                           AdminController aController,
                           Database database,
                           String username) {

        super("High Score For The User");

        this.controller = controller;
        this.aController = aController;
        this.database = database;
        this.username = username;

        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            displayAllHighScoreResultsOnTheUserTable(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method create an visual interpretation for the HighScore table of the
     * database.
     *
     * @param username represent the username of the User the highScore should be displayed for
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @SuppressWarnings("Duplicates")
    private void displayAllHighScoreResultsOnTheUserTable(String username) throws SQLException {

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
