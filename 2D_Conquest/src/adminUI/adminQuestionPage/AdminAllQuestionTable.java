package adminUI.adminQuestionPage;

import controller.MyController;
import dao.Database;
import dao.QuestionDao;
import dao.UserDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.Vector;

/**
 * File created on 11/17/2018
 * by Toader
 **/
public class AdminAllQuestionTable extends JFrame {
    private MyController controller;
    private Connection conn;
    private JTable questionsTable;

    /**
     * Admin Question Table Page  UI's Constructor
     *
     * @param controller of type MyController
     */
    public AdminAllQuestionTable(
            MyController controller
    ) {

        super("All Questions Tsble");

        this.controller = controller;
        setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            displayAllQuestionsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void displayAllQuestionsTable() throws SQLException {

        super.setBounds(0, 0, 800, 600);

        JPanel panel = new JPanel();
        TableModel tableModel = buildTableModel(Database.getData());
        questionsTable = new JTable(tableModel);
        panel.add(new JScrollPane(questionsTable));
        super.getContentPane().add(panel);

        super.setVisible(true);

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

    private TableModel buildTableModel(ResultSet resultSet)
            throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        // Column names.
        Vector<String> columnNames = new Vector<>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames.add(resultSet.getMetaData().getColumnName(columnIndex));
        }

        // Data of the table.
        Vector<Vector<Object>> dataVector = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> rowVector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowVector.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(rowVector);
        }

        return new DefaultTableModel(dataVector, columnNames);
    }

    public static void main(final String[] arguments) {
        Database database = new Database();
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();
        MyController myController = new MyController(database, userDao, questionDao);
        myController.openAdminFullQuestionTable();

    }
}
