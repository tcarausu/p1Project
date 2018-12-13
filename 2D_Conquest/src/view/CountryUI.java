package view;


import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class CountryUI extends JFrame {
    private ImageIcon firstImage = new ImageIcon("images/Region-Top.png");
    private ImageIcon secondImage = new ImageIcon("images/Region-Middle.png");
    private ImageIcon thirdImage = new ImageIcon("images/Region-Bottom.png");
    private ImageIcon forthImage = new ImageIcon("images/Region-Side.png");

    private JButton firstMap = new JButton("capital",firstImage);
    private JButton secondMap = new JButton(secondImage);
    private JButton thirdMap = new JButton(thirdImage);
    private JButton forthMap = new JButton(forthImage);

    private JButton back = new JButton("Back");

    private MyController controller;
    private AdminController aController;

    /**
     * Country UI's Constructor. This displays our regional maps for quiz selection.
     *
     * @param controller represents the MyController class
     * @param aController represents the AdminController class
     */
    public CountryUI(MyController controller, AdminController aController) {

        super("Country UI");
        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        countryButtons();
    }

    /**
     * Sets up the functionality for the Country "UI." The countries that are displayed in Country UI will be interactable by
     * creating those maps into selectable buttons, done here.
     *
     *
     */
    private void countryButtons() {
        super.setBounds(0, 0, 900, 800);
        setLocation(250, 50);

        firstMap.setBounds(70, 50, 300, 300);
        secondMap.setBounds(70, 350, 300, 300);
        thirdMap.setBounds(370, 50, 300, 300);
        forthMap.setBounds(370, 350, 300, 300);

        back.setBounds(350, 650, 160, 40);

        String firstMapRegion = firstMap.getText();

        super.add(firstMap);
        super.add(secondMap);
        super.add(thirdMap);
        super.add(forthMap);

        super.add(back);

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
                    LoginPageUI loginPageUI = new LoginPageUI(controller, aController);
                    loginPageUI.setVisible(true);
                });
    }

}