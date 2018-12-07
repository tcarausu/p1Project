package view;


import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

public class CountryUI extends JFrame {
    private ImageIcon firstImage = new ImageIcon("D:\\GIT version control\\2D_Conquest\\src\\RegionBottom.png");
    private ImageIcon secondImage = new ImageIcon("D:\\GIT version control\\2D_Conquest\\src\\Region-Middle.png");
    private ImageIcon thirdImage = new ImageIcon("D:\\GIT version control\\2D_Conquest\\src\\Region-Side.png");
    private ImageIcon fortImage = new ImageIcon("D:\\GIT version control\\2D_Conquest\\src\\RegionTop.png");

    private MyController controller;
    private AdminController aController;

    private JButton firstMap = new JButton(firstImage);
    private JButton secondMap = new JButton(secondImage);
    private JButton thirdMap = new JButton(thirdImage);
    private JButton forthMap = new JButton(fortImage);
    private JButton fifthMap = new JButton(firstImage);
    private JButton sixThMap = new JButton(firstImage);


    private JButton back = new JButton("Back");

    /**
     * Country UI's Constructor
     * @param controller of type MyController
     */
    public CountryUI(MyController controller,AdminController aController) {

        super("Country UI");
        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        countryButtons();
    }

    /**
     * sets up the functionality for the Country "UI"
     */
    private void countryButtons()  {

        super.add(firstMap);
        super.add(secondMap);
        super.add(thirdMap);
        super.add(forthMap);
        super.add(fifthMap);
        super.add(sixThMap);

        super.add(back);
        
        setSize(900, 700);
        setLocation(250,50);

        firstMap.setBounds(10, 10, 250, 250);
        secondMap.setBounds(400, 10, 250, 250);
        thirdMap.setBounds(10, 300, 250, 250);
        forthMap.setBounds(400, 300, 250, 250);

        firstMap.setOpaque(false);
        firstMap.setContentAreaFilled(false);
        firstMap.setBorderPainted(false);

        secondMap.setOpaque(false);
        secondMap.setContentAreaFilled(false);
        secondMap.setBorderPainted(false);

        thirdMap.setOpaque(false);
        thirdMap.setContentAreaFilled(false);
        thirdMap.setBorderPainted(false);

        forthMap.setOpaque(false);
        forthMap.setContentAreaFilled(false);
        forthMap.setBorderPainted(false);


        firstMap.setText("capital");

        String firstMapRegion = firstMap.getText();

        back.setBounds(350, 700, 160, 40);

        firstMap.addActionListener(e -> {
                    dispose();
                    try {
                        controller.openDifficultyWindow(firstMapRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        secondMap.addActionListener(e -> dispose());
        thirdMap.addActionListener(e -> dispose());
        forthMap.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    LoginPageUI loginPageUI = new LoginPageUI(controller,aController);
                    loginPageUI.setVisible(true);
                });
    }

}