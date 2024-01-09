package GUI;

import javax.swing.*;
import java.awt.*;

public class Home {
    public Home() {
        JFrame frame = new JFrame("Menu Principale");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel homeLabel = new JLabel("Gestionale dell'Azienda");
        homeLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton impiegatiButton = new JButton("Impiegati");
        JButton progettiButton = new JButton("Progetti");
        JButton laboratoriButton = new JButton("Laboratori");
        JButton attrezzatureButton = new JButton("Attrezzature");

        Dimension buttonDimension = new Dimension(200,50);

        impiegatiButton.setMaximumSize(buttonDimension);
        progettiButton.setMaximumSize(buttonDimension);
        laboratoriButton.setMaximumSize(buttonDimension);
        attrezzatureButton.setMaximumSize(buttonDimension);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        impiegatiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        progettiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        laboratoriButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        attrezzatureButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(Box.createVerticalStrut(25));
        frame.add(homeLabel);
        frame.add(Box.createVerticalStrut(200));
        frame.add(impiegatiButton);
        frame.add(laboratoriButton);
        frame.add(progettiButton);
        frame.add(attrezzatureButton);

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Home();
    }
}
