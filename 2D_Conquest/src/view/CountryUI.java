package view;


import controller.MyController;

import javax.swing.*;

public class CountryUI extends JFrame {
    private ImageIcon firstImage = new ImageIcon("src/Blank_Map_of_Denmark.png");

    private MyController ctrler;

    private JButton firstMap = new JButton(firstImage);
    private JButton secondMap = new JButton(firstImage);
    private JButton thirdMap = new JButton(firstImage);
    private JButton forthMap = new JButton(firstImage);
    private JButton fifthMap = new JButton(firstImage);
    private JButton sixThMap = new JButton(firstImage);


    private JButton back = new JButton("Back");

    public CountryUI(MyController myController) {

        super("Country UI");
        this.ctrler = myController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        countryButtons();
    }

    /**
     * sets up the functionality for the Country "UI"
     */
    private void countryButtons() {

        super.add(firstMap);
        super.add(secondMap);
        super.add(thirdMap);
        super.add(forthMap);
        super.add(fifthMap);
        super.add(sixThMap);

        super.add(back);

        setSize(400, 600);

        firstMap.setBounds(70, 50, 80, 80);
        secondMap.setBounds(70, 150, 80, 80);
        thirdMap.setBounds(70, 250, 80, 80);
        forthMap.setBounds(170, 50, 80, 80);
        fifthMap.setBounds(170, 150, 80, 80);
        sixThMap.setBounds(170, 250, 80, 80);

        back.setBounds(70, 350, 160, 40);

        firstMap.addActionListener(e -> {
                    dispose();
                ctrler.openDifficultyWindow();
                }
        );
        secondMap.addActionListener(e -> dispose());
        thirdMap.addActionListener(e -> dispose());
        forthMap.addActionListener(e -> dispose());
        fifthMap.addActionListener(e -> dispose());
        sixThMap.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    LoginPageUI loginPageUI = new LoginPageUI(ctrler);
                    loginPageUI.setVisible(true);
                });
    }

}