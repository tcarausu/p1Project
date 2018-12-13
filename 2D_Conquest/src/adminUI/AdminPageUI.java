package adminUI;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminPageUI extends JFrame {
    private MyController controller;
    private AdminController aController;

    private JButton userUI = new JButton("Admin UserUI");

    private JButton questionsUI = new JButton("Admin QuestionsUI");

    private JButton settingsMenu = new JButton("Admin SettingMenu");
    private JButton playGame = new JButton("Play the Game ");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    /**
     * Admin Page UI's Constructor.
     * <p>
     * Initiates and displays AdminPage UI
     *
     * @param controller  controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
     */
    public AdminPageUI(MyController controller, AdminController aController) {
        super("AdminPage UI");
        this.controller = controller;
        this.aController = aController;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceButton();
    }

    /**
     * This method allows administrator to access userUI and questionUI or starting the game.
     * <p>
     * This method instantiates buttons and attaches them to the window.
     * <p>
     * To access userUI and questionUI should be used buttons with he exact name.
     * <p>
     * If the admin wants to go back to MainUI, Back button will be used
     *
     */
    private void introduceButton() {

        super.add(userUI);
        super.add(questionsUI);

        super.add(settingsMenu);
        super.add(playGame);

        super.add(quit);
        super.add(back);

        setSize(600, 400);

        userUI.setBounds(70, 50, 160, 40);
        questionsUI.setBounds(250, 50, 160, 40);

        playGame.setBounds(70, 150, 160, 40);
        settingsMenu.setBounds(250, 150, 160, 40);

        quit.setBounds(70, 250, 160, 40);
        back.setBounds(250, 250, 160, 40);

        userUI.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminUserUI();

                }
        );
        questionsUI.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminQuestionUI();

                }
        );
        playGame.addActionListener(
                e -> {
                    dispose();
                    controller.openCountryWindow();

                }
        );
        settingsMenu.addActionListener(
                e -> {
                    dispose();
                    aController.openAdminSettingsUI();

                }
        );
        back.addActionListener(
                e -> {
                    dispose();
                    controller.start();

                }
        );

        quit.addActionListener(e -> dispose());
    }

}
