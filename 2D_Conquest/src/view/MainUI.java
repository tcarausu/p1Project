package view;

import javax.swing.*;


public class MainUI extends JPanel {

//    private Image image;

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

    /*  private void initBoard() {

          loadImage();

          int w = image.getWidth(this);
          int h = image.getHeight(this);
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
  */

}