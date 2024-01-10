package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Home {
    public Home() {
        // Frame
        JFrame frame = new JFrame("Menu Principale");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panels
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        // GridBagConstraints
        GridBagConstraints c = new GridBagConstraints();

        // Fonts
        Font buttonsFont = new Font("Intellij Mono", Font.PLAIN, 20);
        Font labelFont = new Font("Intellij Mono", Font.PLAIN, 40);

        // Label
        JLabel homeLabel = new JLabel("Gestionale dell'Azienda");
        homeLabel.setFont(labelFont);

        // Colors
        Color backgroundColor = new Color(11,11,84);
        Color buttonsColor = new Color(17,16,122);
        Color textColor = Color.WHITE;

        // Buttons
        JButton impiegatiButton = new JButton("Impiegati");
        JButton progettiButton = new JButton("Progetti");
        JButton laboratoriButton = new JButton("Laboratori");
        JButton attrezzatureButton = new JButton("Attrezzature");

        // Creating the variable that holds the dimension of the various buttons
        Dimension buttonDimension = new Dimension(300,75);

        // Setting the buttons dimension
        impiegatiButton.setPreferredSize(buttonDimension);
        progettiButton.setPreferredSize(buttonDimension);
        laboratoriButton.setPreferredSize(buttonDimension);
        attrezzatureButton.setPreferredSize(buttonDimension);

        // Setting the buttons font
        impiegatiButton.setFont(buttonsFont);
        progettiButton.setFont(buttonsFont);
        laboratoriButton.setFont(buttonsFont);
        attrezzatureButton.setFont(buttonsFont);

        // Adding Components to Panels using the Constraints
        titlePanel.add(homeLabel);
        c.gridx = 0;
        c.gridy = 0;
        buttonsPanel.add(impiegatiButton, c);
        c.gridx = 0;
        c.gridy = 1;
        buttonsPanel.add(laboratoriButton, c);
        c.gridx = 1;
        c.gridy = 0;
        buttonsPanel.add(progettiButton, c);
        c.gridx = 1;
        c.gridy = 1;
        buttonsPanel.add(attrezzatureButton, c);

        /*impiegatiButton.setBackground(buttonsColor);
        progettiButton.setBackground(buttonsColor);
        attrezzatureButton.setBackground(buttonsColor);
        laboratoriButton.setBackground(buttonsColor);

        impiegatiButton.setBorderPainted(false);
        progettiButton.setBorderPainted(false);
        attrezzatureButton.setBorderPainted(false);
        laboratoriButton.setBorderPainted(false);

        impiegatiButton.setForeground(textColor);
        progettiButton.setForeground(textColor);
        attrezzatureButton.setForeground(textColor);
        laboratoriButton.setForeground(textColor);

        titlePanel.setBackground(backgroundColor);
        homeLabel.setForeground(textColor);

        buttonsPanel.setBackground(backgroundColor);*/

        // Adding Panels to Frame
        frame.add(BorderLayout.NORTH, titlePanel);
        frame.add(BorderLayout.CENTER, buttonsPanel);

        // Making the Frame better
        //frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
