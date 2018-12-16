package view;


import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

public class CountryUI extends JFrame {
    private ImageIcon firstImage = new ImageIcon("images/Region-Top.png");
    private ImageIcon secondImage = new ImageIcon("images/Region-Middle.png");
    private ImageIcon thirdImage = new ImageIcon("images/Region-Bottom.png");
    private ImageIcon forthImage = new ImageIcon("images/Region-Side.png");

    private JButton firstMap = new JButton("northDenmark", firstImage);
    private JButton secondMap = new JButton("midDenmark", secondImage);
    private JButton thirdMap = new JButton("southDenmark", thirdImage);
    private JButton forthMap = new JButton("capital", forthImage);

    private JButton back = new JButton("Back");

    private MyController controller;
    private AdminController aController;

    /**
     * Country UI's Constructor. This displays our regional maps for quiz selection.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     * @param aController represent the AdminController Controller needed to instantiate the constructor
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
     */
    @SuppressWarnings("Duplicates")
    private void countryButtons() {
        super.setBounds(0, 0, 900, 800);
        setLocation(250, 50);

        firstMap.setBounds(70, 50, 300, 300);
        secondMap.setBounds(70, 350, 300, 300);
        thirdMap.setBounds(370, 50, 300, 300);
        forthMap.setBounds(370, 350, 300, 300);

        back.setBounds(350, 650, 160, 40);

        String firstMapRegion = firstMap.getText();
        String secondMapRegion = secondMap.getText();
        String thirdMapRegion = thirdMap.getText();
        String forthMapRegion = forthMap.getText();

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

        secondMap.addActionListener(e -> {
            dispose();
            try {
                controller.openDifficultyWindow(secondMapRegion);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        thirdMap.addActionListener(e -> {
            dispose();
            try {
                controller.openDifficultyWindow(thirdMapRegion);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        forthMap.addActionListener(e -> {
            dispose();
            try {
                controller.openDifficultyWindow(forthMapRegion);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(
                e -> {
                    dispose();
                    LoginPageUI loginPageUI = new LoginPageUI(controller, aController);
                    loginPageUI.setVisible(true);
                });
    }

}