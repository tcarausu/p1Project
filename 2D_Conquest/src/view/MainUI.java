package view;

import javax.swing.*;


public class MainUI extends JPanel {


    public MainUI(Application app) {

        introduceButton(app);
    }

    public void introduceButton(Application app) {
        app.setTitle("Main UI");

//        JPanel panel = new JPanel();
        JButton start = new JButton("Start");
        JButton quit = new JButton("QUIT");

        app.add(start);
        app.add(quit);
//        app.add(panel);

//        app.add(start, BorderLayout.CENTER);
//        app.add(quit, BorderLayout.CENTER);
        app.setSize(400, 400);

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);

        start.addActionListener(
                e -> {

                    this.setVisible(false);
                    CountryUI cui = new CountryUI(app);
                    cui.setVisible(true);

                }
                /* e -> new CountryUI(app)*/
        );
        quit.addActionListener(e -> app.dispose());
    }


}