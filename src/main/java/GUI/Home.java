package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel mainPanel;
    private JButton impiegatiButton;
    private JButton laboratoriButton;
    private JButton attrezzatureButton;
    private JButton progettiButton;
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel buttonsPPanel;

    public Home(){
        Controller controller = new Controller();
        JFrame frame = new JFrame("Home");
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        impiegatiButton.addActionListener(e -> {
            frame.setVisible(false);
            ImpiegatoGUI impiegatoGUI = new ImpiegatoGUI(controller, frame);
        });

        laboratoriButton.addActionListener(e -> {
            frame.setVisible(false);
            LaboratoriGUI laboratoriGUI = new LaboratoriGUI(controller, frame);
        });
    }
}
