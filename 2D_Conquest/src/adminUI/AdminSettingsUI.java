package adminUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminSettingsUI extends JFrame {
    private MyController controller;

    private ImageIcon button = new ImageIcon("C:\\Users\\Jesus\\Downloads\\button.jpg");

    private JButton volume = new JButton("Volume",button);
    private JButton defDif = new JButton("Default difficulty"); // easy-medium-hard-off radio button
    private JButton soundEffects = new JButton("Sound Effects"); // on-off
    private JButton music = new JButton("Music"); // on-off

    private JButton addQuestion = new JButton("Start");
    private JButton deleteQuestion = new JButton("Start");
    private JButton editQuestion = new JButton("Start");

    private JButton setttingsMenu = new JButton("High Score");
    private JButton playGame = new JButton("Start");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    private JSlider testslider = new JSlider(0,10);

    /**
     * Admin Settings UI's Constructor
     * @param controller of type MyController
     */
    public AdminSettingsUI(MyController controller) {
        super("AdminPage Settings UI");
        this.controller = controller;

        setLocation(500, 200);
//        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceButton();
    }

    /**
     */
    private void introduceButton() {

        super.add(volume);
        super.add(defDif);
        super.add(soundEffects);
        super.add(music);

        super.add(setttingsMenu);
        super.add(playGame);

        super.add(quit);
        super.add(back);

        super.add(testslider);

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\Jesus\\Downloads\\clipart-png-elephant-11.png"));
        super.add(background);



        testslider.setBounds(240, 50, 160, 40);

        setSize(500, 700);

        volume.setBounds(70, 50, 160, 40);
        defDif.setBounds(70, 100, 160, 40);
        soundEffects.setBounds(70, 150, 160, 40);

        music.setBounds(70, 200, 160, 40);


        setttingsMenu.setBounds(70, 400, 160, 40);

        back.setBounds(70, 500, 160, 40);
        quit.setBounds(70, 550, 160, 40);

//        createUser.addActionListener(
//                e -> {
//                    dispose();
//                    controller.openLoginWindow();
//
//                }
//        );
        setttingsMenu.addActionListener(
                e -> {
                    dispose();
                    controller.openScoreWindow();

                }
        );
        quit.addActionListener(e -> dispose());
    }

}

