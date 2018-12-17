package adminUI;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * File created on 12/17/2018
 * BY Toader
 **/
public class AdminSetDifficultyForUser extends JFrame {

    private MyController controller;
    private AdminController aController;

    private JButton setDefaultDifficulty = new JButton("Set Default Difficulty");
    private JButton back = new JButton("Back");

    private JTextField id = new JTextField();
    private JTextField difficultyDefault = new JTextField();

    private JLabel userId = new JLabel("User ID");
    private JLabel difficultyDefaultForUser = new JLabel("Default Difficulty");

    /**
     * Set Default Difficulty for User Constructor.
     * <p>
     * Allows an admin set a default difficulty for an User by his Id.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminSetDifficultyForUser(MyController controller,
                                     AdminController aController) {

        super("Set Default Difficulty for User");

        this.controller = controller;
        this.aController = aController;

        setSize(500, 200);
        setLocation(500, 280);

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setUpDifficultyForUser();

    }

    /**
     * This method allows the administrator to set a difficulty for an User by his Id.
     * <p>
     * This method initiates the buttons and attaches them to the window.
     * <p>
     * To set the difficulty for an User by his Id it can be done from either the last field (id)
     * by using "Enter" or by manually clicking on the delete button.
     * <p>
     * If the user wants to go back to Admin UI it will use the Back button.
     */
    @SuppressWarnings("Duplicates")
    private void setUpDifficultyForUser() {

        userId.setBounds(20, 30, 140, 20);
        difficultyDefaultForUser.setBounds(20, 60, 140, 20);
        id.setBounds(160, 30, 250, 20);
        difficultyDefault.setBounds(160, 60, 250, 20);

        setDefaultDifficulty.setBounds(30, 100, 160, 40);
        back.setBounds(350, 100, 120, 40);

        super.add(id);
        super.add(userId);
        super.add(difficultyDefaultForUser);
        super.add(difficultyDefault);

        super.add(back);
        super.add(setDefaultDifficulty);

        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setDifficultyForUserById();
                }
            }
        });

        setDefaultDifficulty.addActionListener(ae -> {
            setDifficultyForUserById();
        });

        back.addActionListener(ae -> {
            dispose();
            aController.openAdminSettingsUI();
        });
    }

    /**
     * This method set the default difficulty for an User from the database
     * by the Id of the user required (done by an administrator)
     * <p>
     * This method also checks for valid input (non null/empty).
     * <p>
     * In case of appropriate input the method will
     * verify the admin data used for deletion and and move forward.
     * <p>
     * As mentioned before if the data is invalid,
     * it will clear the fields and open the same window.
     */
    private void setDifficultyForUserById() {
        String idText = id.getText();
        String defaultDiffText = difficultyDefault.getText();

        dispose();
        if ((idText != null && !idText.equals("")) &&
                (defaultDiffText != null && !defaultDiffText.equals(""))) {
            try {
                boolean result = aController.checkUserIdValidity(Integer.parseInt(idText));
                if (result) {
                    controller.setCurrentUserIdAndDefaultDifficulty(Integer.parseInt(idText), defaultDiffText);
                } else {
                    clearFieldsWhenNeeded();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            clearFieldsWhenNeeded();
            aController.openAdminSetDifficultyOnUser();
        }
    }

    /**
     * Allows login information fields to be cleared when
     * necessary to enter in new information or delete current input.
     */
    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is incorrect",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        id.setText("");
        id.requestFocus();
    }
}