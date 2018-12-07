package TimeTests;

import javax.swing.*;

/**
 * File created by Jesus
 **/
public class TimeLabel extends JFrame {

    static int miliseconds = 0;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;

    static boolean state = true;

    JPanel panel = new JPanel();

    JLabel hour = new JLabel("00: ");
    JLabel minute = new JLabel("00: ");
    JLabel second = new JLabel("00: ");
    JLabel milisecond = new JLabel("00");

    JButton pause = new JButton ("Reset");
    JButton start = new JButton ("Start");
    JFrame frame = new JFrame("Timer");

    public TimeLabel(){
        frame.setSize(650,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,400);
        frame.setVisible(true);
        frame.setLayout(null);

        frame.add(hour);
        frame.add(minute);
        frame.add(second);
        frame.add(milisecond);

        frame.add(start);
        frame. add(pause);

        hour.setBounds(10,10,30,30);
        minute.setBounds(40,10,30,30);
        second.setBounds(70,10,30,30);
        milisecond.setBounds(110,10,30,30);

        start.setBounds(2,45,65,50);
        pause.setBounds(78,45,100,50);


        start.addActionListener(
                e -> {
                    state = true;

                    Thread t = new Thread(() -> {
                        for (;;)
                            if (state==true){
                                try
                                {
                                    Thread.sleep(1);

                                    if (miliseconds>762){
                                        miliseconds=0;
                                        seconds++;
                                    }
                                    if (seconds>60){
                                        miliseconds=0;
                                        seconds=0;
                                        minutes++;
                                    }
                                    if (minutes>60){
                                        miliseconds=0;
                                        seconds=0;
                                        minutes=0;
                                        hours++;
                                    }
                                    milisecond.setText(" : "+miliseconds);

                                    miliseconds++;
                                    second.setText(" : "+seconds);
                                    minute.setText(" : "+minutes);
                                    hour.setText(""+hours);
                                }
                                catch (Exception ev)
                                {

                                }

                            } else
                            {
                                break;
                            }

                    });
                    t.start();
                }
        );
        pause.addActionListener(
                e -> {
                    state = false;
                    System.out.printf("You finished in this time: %d h:%d m:%d s",hours,minutes,seconds);
                    System.out.println();
                    hours=0;
                    minutes=0;
                    seconds=0;
                    miliseconds=0;
                }
        );



    }

    public static void main(String[] args) {
       new TimeLabel();
    }
}
