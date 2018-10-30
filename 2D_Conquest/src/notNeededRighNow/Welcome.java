package notNeededRighNow;

import javax.swing.*;

public class Welcome extends JFrame {

//    JLabel welcome = new JLabel("Welcome to a New Frame");
    JPanel panel = new JPanel();

    JButton easy = new JButton("easy");
    JButton medium = new JButton("medium");
    JButton hard = new JButton("hard");

    Welcome() {
        super("Welcome");
        setSize(650, 300);
        setLocation(500, 280);
        panel.setLayout(null);

        easy.setBounds(50, 50, 150, 60);
        medium.setBounds(250, 50, 150, 60);
        hard.setBounds(450, 50, 150, 60);

        panel.add(easy);
        panel.add(medium);
        panel.add(hard);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}