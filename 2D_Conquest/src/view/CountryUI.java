package view;


import controller.MyController;

import javax.swing.*;
import java.sql.SQLException;

public class CountryUI extends JFrame {
    private ImageIcon firstImage = new ImageIcon("images/TopOfDenmark.png");
    private ImageIcon secondImage = new ImageIcon("images/MiddleOfDenmark.png");
    private ImageIcon thirdImage = new ImageIcon("images/BottomOfDenmark.png");
    private ImageIcon forthImage = new ImageIcon("images/CapitalArea.png");

    private JButton firstMap = new JButton( firstImage);
    private JButton secondMap = new JButton( secondImage);
    private JButton thirdMap = new JButton( thirdImage);
    private JButton forthMap = new JButton( forthImage);

    private JButton back = new JButton("Back");

    private MyController controller;
    private String firstMapRegion = "northDenmark";
    private String secondMapRegion = "midDenmark";
    private String thirdMapRegion = "southDenmark";
    private String forthMapRegion = "capital";

    /**
     * Country UI's Constructor. This displays our regional maps for quiz selection.
     *
     * @param controller  represent the MyController Controller needed to instantiate the constructor
     */
    public CountryUI(MyController controller) {

        super("Country UI");
        this.controller = controller;

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
        super.setBounds(0, 0, 625, 800);
        setLocation(250, 5);

        firstMap.setBounds(20, 5, 300, 250);
        secondMap.setBounds(20, 255, 300, 250);
        thirdMap.setBounds(20, 505, 300, 200);
        forthMap.setBounds(320, 505, 300, 200);

        back.setBounds(250, 710, 160, 40);

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
                    controller.openLoginWindow();
                });
    }

}