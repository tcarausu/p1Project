package view;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private Application() {

        initUI();

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();

            ex.setVisible(true);
        });

    }

    private void initUI() {
//      add(new LoginPageUI());
        add(new MainUI(this));
//      add(new LoginForm());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}