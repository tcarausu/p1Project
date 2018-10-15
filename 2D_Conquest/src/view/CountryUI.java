package view;


import javax.swing.*;
import java.awt.*;

public class CountryUI extends JPanel {

    public CountryUI(Application app) {
        JButton start = new JButton("Start_Game");
        JButton quit = new JButton("QUIT_Country_Game");
        JPanel panel = new JPanel();

        app.setTitle("Country Page");

        setSize(800, 800);
        setLocation(500, 280);
        panel.setLayout(null);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);

        app.add(panel);
        app.add(start, BorderLayout.CENTER);
        app.add(quit, BorderLayout.CENTER);

        start.addActionListener(e -> app.dispose());
        quit.addActionListener(e -> app.dispose());
    }

}
