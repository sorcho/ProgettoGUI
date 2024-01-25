package GUI;

import Controller.Controller;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromuoviGUI {
    private JPanel promMainPanel;
    private JPanel listPanel;
    private JPanel buttonsPanel;
    private JLabel titleLabel;
    private JList dirList;
    private JScrollPane listScrollPane;
    private JButton annullaButton;
    private JButton okButton;
    private JFrame frame;

    public PromuoviGUI(Controller controller, JFrame frameChiamante, String cfSelezionato) {
        frame = new JFrame("Promuovi Senior");
        frame.setContentPane(promMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        annullaButton.addActionListener(e -> {
            frame.dispose();
        });

        dirList.setModel(new DefaultListModel());
        DefaultListModel dfl = (DefaultListModel) dirList.getModel();

        dirList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer =
                (DefaultListCellRenderer) dirList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        ArrayList<String> listaDirigenti = controller.getListaDirigenti();

        for (String s : listaDirigenti)
            dfl.addElement(s);

        okButton.addActionListener(e -> {
            String dirSelezionato = dirList.getSelectedValue().toString();
            try {
                controller.promuovi(cfSelezionato, dirSelezionato);
                JOptionPane.showMessageDialog(null, "Promozione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
            frame.dispose();
        });
    }
}
