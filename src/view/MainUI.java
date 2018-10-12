package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainUI extends JPanel {

    private Image image;

    MainUI(Application app) {
        introduceButton(app);
    }

    private void introduceButton(Application app) {
        JButton start = new JButton("Start");
        JButton quit = new JButton("QUIT");

        start.setBounds(70, 50, 160, 40);
        quit.setBounds(70, 150, 160, 40);

        app.add(start,BorderLayout.CENTER);
        app.add(quit,BorderLayout.CENTER);

        if (quit.getModel().isPressed()) {
            quit.addActionListener(e -> app.dispose());
        }
    }
    private void initBoard() {

        loadImage();

        int w = image.getWidth(this);
        int h =  image.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/Blank_Map_of_Denmark.png");
        image = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(image, 0, 0, null);
    }
    private static class buttonActions implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}