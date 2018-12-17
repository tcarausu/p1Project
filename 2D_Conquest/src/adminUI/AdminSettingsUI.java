package adminUI;

import controller.AdminController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminSettingsUI extends JFrame {
    private AdminController aController;

    private ImageIcon button = new ImageIcon("images/login.jpg");

    private JButton volume = new JButton("Volume", button);
    private JButton defaultDifficulty = new JButton("Default difficulty"); // easy-medium-hard-off radio button
    private JButton soundEffects = new JButton("Sound Effects"); // on-off
    private JButton music = new JButton("Music"); // on-off

    private JButton playGame = new JButton("Start");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    private JSlider testSlider = new JSlider(0, 10);

    /**
     * Admin Settings UI's Constructor
     *
     * @param aController represent the MyController Controller needed to instantiate the constructor
     */
    public AdminSettingsUI(AdminController aController) {
        super("AdminPage Settings UI");
        this.aController = aController;

        setLocation(500, 150);
        setSize(500, 650);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceSettings();
    }

    /**
     * This method introduce buttons to the existing frame
     * <p>
     * If the user wants to return to the Admin Page UI it will use back button;
     * If the user wants to close the application he is going to use the Quit Button
     */
    private void introduceSettings() {

        super.add(volume);
        super.add(defaultDifficulty);
        super.add(soundEffects);
        super.add(music);

        super.add(playGame);

        super.add(quit);
        super.add(back);

        super.add(testSlider);

        JLabel background = new JLabel(new ImageIcon("images/Region-Top.png"));
        super.add(background);

        testSlider.setBounds(240, 50, 160, 40);

        volume.setBounds(70, 50, 160, 40);
        defaultDifficulty.setBounds(70, 100, 160, 40);
        soundEffects.setBounds(70, 150, 160, 40);

        music.setBounds(70, 200, 160, 40);

        back.setBounds(70, 500, 160, 40);
        quit.setBounds(70, 550, 160, 40);

        back.addActionListener(e -> {
            dispose();
            aController.openAdminPageUI();
        });
        defaultDifficulty.addActionListener(e -> aController.openAdminSetDifficultyOnUser());
        quit.addActionListener(e -> dispose());
    }

}

