package adminUI.adminUserPage;

import controller.AdminController;
import controller.MyController;
import dao.Database;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.SQLException;

/**
 * File created on 11/30/2018
 * BY Toader
 **/
public class AdminAllUsersTable extends JFrame {
    private MyController controller;

    private JButton back = new JButton("Back");
    private AdminController aController;
    private Database database;

    /**
     * Admin All Users Table Page  UI's Constructor.
     * <p>
     * Displays the list of users from the database.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     * @param database    represent the Database Dao Layer needed to instantiate the constructor
     */
    public AdminAllUsersTable(
            MyController controller,
            AdminController aController,
            Database database
    ) {

        super("User Table");

        this.controller = controller;
        this.aController = aController;
        this.database = database;
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            displayAllUserTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method displays a table of all the users in the database
     *
     * @throws SQLException in case that there is no data or
     *                      there is an issue extracting data from the database
     */
    @SuppressWarnings("Duplicates")
    private void displayAllUserTable() throws SQLException {

        super.setBounds(0, 0, 500, 500);
        setLocation(500, 200);

        JPanel panel = new JPanel();
        TableModel tableModel = controller.buildTableModel(database.getUsers());
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
