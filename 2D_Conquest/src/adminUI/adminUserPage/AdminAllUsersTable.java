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
     * Admin Users Table Page  UI's Constructor
     *
     * @param controller of type MyController
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
            displayAllQuestionsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("Duplicates")
    private void displayAllQuestionsTable() throws SQLException {

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
