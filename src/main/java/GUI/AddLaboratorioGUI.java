package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddLaboratorioGUI {
    private JPanel addLabMainPanel;
    private JPanel insPanel;
    private JTextField nomeTextField;
    private JLabel nomeLabel;
    private JLabel respSciLabel;
    private JTextField topicTextField;
    private JLabel topicLabel;
    private JPanel buttonsPanel;
    private JButton annullaButton;
    private JButton okButton;
    private JComboBox seniorComboBox;
    private JComboBox cupComboBox;
    protected JFrame frame;

    public AddLaboratorioGUI (Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Aggiungi Laboratorio");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(addLabMainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ArrayList<String> listaSenior = controller.getListaSenior();

        for (String s : listaSenior)
            seniorComboBox.addItem(s);

        okButton.addActionListener(e -> {
            String nome = nomeTextField.getText();
            String respSci = seniorComboBox.getSelectedItem().toString();
            String topic = topicTextField.getText();

            int risposta = JOptionPane.showConfirmDialog(null, "Vuoi aggiungere questo Laboratorio?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (risposta == JOptionPane.YES_OPTION) {
                try {
                    controller.aggiungiLaboratorio(nome, respSci, topic);
                    JOptionPane.showMessageDialog(null, "Laboratorio inserito con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }

            frame.dispose();
        });

        annullaButton.addActionListener(e ->{
            frame.dispose();
        });
    }
}
