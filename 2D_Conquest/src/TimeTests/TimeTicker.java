package TimeTests;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File created on 12/10/2018
 * BY Toader
 **/
public class TimeTicker {

    private static final int STUDY_TIME = 15;
    AtomicInteger atomicDown = new AtomicInteger(STUDY_TIME);
    JButton b_study;
    JButton b_exit;
    JLabel l_studyTime;

    private TimeTicker() {
        JPanel gui = new JPanel();

        b_study = new JButton("Study");
        ClickListener listener = new ClickListener();
        b_study.addActionListener(listener);
        gui.add(b_study);

        b_exit = new JButton("Exit");
        b_exit.addActionListener(listener);
        gui.add(b_exit);

        l_studyTime = new JLabel("" + atomicDown.get());
        gui.add(l_studyTime);

        JOptionPane.showMessageDialog(null, gui);
    }

    private class ClickListener implements ActionListener {

        Timer timer;

        public void actionPerformed(ActionEvent e){
            if (e.getSource() == b_study) {
                ActionListener countDown = ae -> {
                    if (!(atomicDown.get() > 0)) {
                        timer.stop();
                        // reset the count.
                        atomicDown.set(STUDY_TIME);
                    } else {
                        l_studyTime.setText(
                                Integer.toString(
                                        atomicDown.decrementAndGet()));
                    }
                };
                timer = new Timer(1000,countDown);
                timer.start();
            } else if(e.getSource() == b_exit) {
                System.exit(0);
            } else {
                System.out.println("ERROR: button troll");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeTicker::new);
    }
}