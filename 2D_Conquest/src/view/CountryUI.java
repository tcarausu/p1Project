package view;


import javax.swing.*;

public class CountryUI extends JPanel {

    public CountryUI(Application app) {
        countryButtons(app);
    }

    public void countryButtons(Application app) {
        app.setTitle("Country Page");

        JPanel panel = new JPanel();
        JButton start = new JButton("Start_Game");
        JButton quit = new JButton("QUIT_Country_Game");

        JButton back = new JButton("Back");

        panel.add(start);
        panel.add(quit);
        panel.add(back);
        app.add(panel);

        app.setSize(800, 400);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);
        back.setBounds(70, 350, 160, 40);

        start.addActionListener(e -> app.dispose());
        quit.addActionListener(e -> app.dispose());
        back.addActionListener(e -> {

//            this.setVisible(false);
            MainUI mainUI = new MainUI(app);
            mainUI.setVisible(true);

        });
    }

}
