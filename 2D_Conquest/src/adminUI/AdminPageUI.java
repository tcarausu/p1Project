package adminUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminPageUI extends JFrame {
    private MyController controller;

    private JButton userUI = new JButton("Admin UserUI");

    private JButton questionsUI = new JButton("Admin QuestionsUI");

    private JButton settingsMenu = new JButton("Admin SettingMenu");
    private JButton playGame = new JButton("Play the Game ");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    /** Admin Page UI's Constructor
     * @param controller of type MyController
     */
    public AdminPageUI(MyController controller) {
        super("AdminPage UI");
        this.controller = controller;

        setLocation(500, 200);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        introduceButton();
    }

    /**
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
                    controller.openAdminUserUI();

                }
        );
        questionsUI.addActionListener(
                e -> {
                    dispose();
                    controller.openAdminQuestionUI();

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
                    controller.openAdminSettingsUI();

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