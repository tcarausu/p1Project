package adminUI;

import controller.MyController;

import javax.swing.*;

/**
 * File created on 11/11/2018
 * by Toader
 **/
public class AdminSettingsUI extends JFrame {
    private MyController ctrler;

    private JButton createUser = new JButton("Start");
    private JButton resetUser = new JButton("Start");
    private JButton deleteUser = new JButton("Start");
    private JButton editUser = new JButton("Start");

    private JButton addQuestion = new JButton("Start");
    private JButton deleteQuestion = new JButton("Start");
    private JButton editQuestion = new JButton("Start");

    private JButton setttingsMenu = new JButton("High Score");
    private JButton playGame = new JButton("Start");

    private JButton quit = new JButton("QUIT");
    private JButton back = new JButton("Back");

    /**
     * Admin Settings UI's Constructor
     * @param ctrler
     */
    public AdminSettingsUI(MyController ctrler) {
        super("AdminPage Settings UI");
        this.ctrler = ctrler;

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

        super.add(createUser);
        super.add(resetUser);
        super.add(deleteUser);
        super.add(addQuestion);

        super.add(setttingsMenu);
        super.add(playGame);

        super.add(quit);
        super.add(back);

        setSize(600, 800);

        createUser.setBounds(70, 50, 160, 40);
        resetUser.setBounds(70, 50, 160, 40);
        deleteUser.setBounds(70, 50, 160, 40);

        addQuestion.setBounds(70, 50, 160, 40);
        addQuestion.setBounds(70, 50, 160, 40);
        addQuestion.setBounds(70, 50, 160, 40);

        createUser.setBounds(70, 50, 160, 40);
        setttingsMenu.setBounds(70, 150, 160, 40);

        quit.setBounds(70, 250, 160, 40);
        back.setBounds(70, 250, 160, 40);

        createUser.addActionListener(
                e -> {
                    dispose();
                    ctrler.openLoginWindow();

                }
        );
        setttingsMenu.addActionListener(
                e -> {
                    dispose();
                    ctrler.openScoreWindow();

                }
        );
        quit.addActionListener(e -> dispose());
    }

}

