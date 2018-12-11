package adminUI.adminQuestionPage;

import controller.AdminController;
import controller.MyController;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * File created on 11/17/2018
 * by Toader
 **/
public class    AdminQuestionCreate extends JFrame {
    private MyController controller;
    private AdminController aController;

    private JButton createNewQuestion = new JButton("Create Question");
    private JButton back = new JButton("Back");

    private JTextField subject = new JTextField(15);
    private JTextField typeOfQuestion = new JTextField(15);
    private JTextField difficultyLevel = new JTextField(15);
    private JTextField region = new JTextField(15);

    private JLabel lsubject = new JLabel("Subject");
    private JLabel ltype = new JLabel("Type Of Question");
    private JLabel ldiff = new JLabel("Difficulty");
    private JLabel lregion = new JLabel("Region");

    /**
     * Login Page UI's Constructor
     *
     * @param controller of type MyController
     */
    public AdminQuestionCreate(MyController controller,AdminController aController) {

        super("Create a New Question");

        this.controller = controller;
        this.aController = aController;

        setLayout(null);
        setResizable(false);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        introduceLogin();

    }

    private void introduceLogin() {
        setSize(500, 300);
        setLocation(500, 280);


        lsubject.setBounds(20, 30, 140, 20);
        subject.setBounds(160, 30, 250, 20);

        ltype.setBounds(20, 60, 140, 20);
        typeOfQuestion.setBounds(160, 60, 250, 20);

        ldiff.setBounds(20, 90, 140, 20);
        difficultyLevel.setBounds(160, 90, 250, 20);

        lregion.setBounds(20, 120, 140, 20);
        region.setBounds(160, 120, 250, 20);

        createNewQuestion.setBounds(30, 190, 120, 40);
        back.setBounds(350, 190, 120, 40);

        super.add(lsubject);
        super.add(createNewQuestion);

        super.add(ltype);
        super.add(typeOfQuestion);

        super.add(ldiff);
        super.add(difficultyLevel);

        super.add(lregion);
        super.add(region);

        super.add(back);
        super.add(subject);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        region.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createNewQuestion();
                }
            }
        });


        createNewQuestion.addActionListener(ae -> {
            createNewQuestion();
        });
        back.addActionListener(ae -> {
            dispose();
            aController.openAdminPageUI();
        });
    }

    private void createNewQuestion() {
        String subjectT = subject.getText();
        String type = typeOfQuestion.getText();
        String diff = difficultyLevel.getText();
        String regionT = region.getText();
        try {
            dispose();
            if ((subjectT != null &&
                    type != null &&
                    diff != null &&
                    regionT != null) &&

                    !subjectT.equals("")
                    && !type.equals("")
                    && !diff.equals("")
                    && !regionT.equals("")
            ) {

                controller.verifyAdminDataOnQuestionCreate(subjectT,
                        type, diff, regionT);
            }  else {
                clearFieldsWhenNeeded();
                aController.openAdminQuestionUI();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFieldsWhenNeeded() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is cleared",
                "Reset", JOptionPane.INFORMATION_MESSAGE);
        subject.setText("");
        typeOfQuestion.setText("");
        difficultyLevel.setText("");
        region.setText("");
        subject.requestFocus();
    }

    public void clearFieldsWhenDataAlreadyExists() {
        JOptionPane.showMessageDialog(null,
                "The information introduced is already in use.\n" +
                        " Please make sure your information you are trying to introduce is not already in use",
                "Already In use", JOptionPane.INFORMATION_MESSAGE);
        subject.setText("");
        typeOfQuestion.setText("");
        difficultyLevel.setText("");
        region.setText("");
        subject.requestFocus();
    }
}