package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddAttrezzaturaGUI {
    private JPanel addAttMainPanel;
    private JPanel insPanel;
    private JTextField serialeTextField;
    private JLabel serialeLabel;
    private JTextField tipoTextField;
    private JLabel tipoLabel;
    private JPanel buttonsPanel;
    private JButton annullaButton;
    private JButton okButton;
    JFrame frame;

    public AddAttrezzaturaGUI(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Inserisci Attrezzatura");
        frame.setContentPane(addAttMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(new Dimension(300,200));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        annullaButton.addActionListener(e -> {
            frame.dispose();
        });

        okButton.addActionListener(e -> {
            int risposta = JOptionPane.showConfirmDialog(null, "Confermi di voler aggiungere questa Attrezzatura?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (risposta == JOptionPane.YES_OPTION) {
                String seriale = serialeTextField.getText();
                String tipo = tipoTextField.getText();

                try {
                    controller.aggiungiAttrezzatura(seriale, tipo);
                    JOptionPane.showMessageDialog(null, "Aggiunta avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
