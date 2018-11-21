package adminUI.adminQuestionPage;

import controller.MyController;
import dao.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Utilities;
import java.sql.*;
import java.util.Vector;

/**
 * File created on 11/17/2018
 * by Toader
 **/
public class AdminAllQuestionTable extends JFrame {
    private MyController controller;
    private Utilities utilities;
    private Database database;
    private Connection conn;
    private JTable questionsTable;
    private JButton back = new JButton("Back");
    /**
     * Admin Question Table Page  UI's Constructor
     *
     * @param controller of type MyController
     */
    public AdminAllQuestionTable(
            MyController controller,
            Database database
    ) {

        super("All Questions Tsble");

        this.controller = controller;
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

        JPanel panel = new JPanel();
        TableModel tableModel = controller.buildTableModel(database.getData());
        questionsTable = new JTable(tableModel);
        panel.add(new JScrollPane(questionsTable));

        back.setBounds(0, 150, 120, 40);
        panel.add(back);

        super.getContentPane().add(panel);

        back.addActionListener(ae -> {
            dispose();
            controller.openAdminPageUI();
        });
   /*

        version 2
        setSize(800, 300);
        setLocation(500, 300);


        TableModel tableModel = controller.buildTableModel(database.getData());
        questionsTable = new JTable(tableModel);
        super.add(new JScrollPane(questionsTable));

        super.setVisible(true);

        back.setBounds(350, 220, 120, 40);
        super.add(back);

        back.addActionListener(ae -> {
            dispose();
            controller.openAdminPageUI();
        });*/

//        setSize(350, 250);
//        setLocation(500, 280);
//        setLayout(null);
//        setResizable(false);
//        setVisible(true);
//        final JPanel panel = new JPanel();
//        final TableModel tableModel = buildTableModel(getData());
//        final JTable table = new JTable(tableModel);
//        panel.add(new JScrollPane(table));
//        super.getContentPane().add(panel);
    }


}
