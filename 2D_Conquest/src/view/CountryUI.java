package view;


import javax.swing.*;

public class CountryUI extends JFrame {

    private JButton start = new JButton("Start_Game");
    private JButton quit = new JButton("QUIT_Country_Game");
    private JButton back = new JButton("Back");

    public CountryUI() {

        super("Country UI");
        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        countryButtons();
    }

    /**
     *  sets up the functionality for the Country "UI"
     */
    private void countryButtons() {

        super.add(start);
        super.add(quit);
        super.add(back);

        setSize(400, 400);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);
        back.setBounds(70, 250, 160, 40);

        start.addActionListener(e -> dispose());
        quit.addActionListener(e -> dispose());

        back.addActionListener(
                e -> {
                    dispose();
                    MainUI mainUI = new MainUI();
                    mainUI.setVisible(true);
                });
    }

}
