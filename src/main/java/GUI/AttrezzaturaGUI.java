package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttrezzaturaGUI {
    private JPanel attMainPanel;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JButton homeButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton profButton;
    private JScrollPane tableScrollPane;
    private JTable attTable;
    private JFrame frame;

    public AttrezzaturaGUI(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Attrezzatura");
        frame.setContentPane(attMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        ArrayList<String> listaSeriali = controller.getSerialiAttrezzature();
        ArrayList<String> listaTipi = controller.getListaTipiAttrezzature();
        ArrayList<Float> listaCosti = controller.getListaCostiAttrezzature();

        String[] colonne = {"Seriale", "Tipo", "Costo"};
        Object[][] righe = new Object[listaSeriali.size()][3];

        for (int i = 0; i < listaSeriali.size(); i++) {
            righe[i][0] = listaSeriali.get(i);
            righe[i][1] = listaTipi.get(i);
            righe[i][2] = listaCosti.get(i) + "€";
        }

        attTable.setModel(new DefaultTableModel(righe, colonne));
        attTable.setRowHeight(30);

        homeButton.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        removeButton.addActionListener(e -> {
            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare l'Attrezzatura?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (selezione == JOptionPane.YES_OPTION) {
                String serialeSelezionato = attTable.getValueAt(attTable.getSelectedRow(), 0).toString();

                try {
                    controller.rimuoviProgetto(serialeSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                loadTable(controller, colonne);
            }
        });

        addButton.addActionListener(e -> {
            AddAttrezzaturaGUI addAttrezzaturaGUI = new AddAttrezzaturaGUI(controller, frame);

            addAttrezzaturaGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        profButton.addActionListener(e -> {
            String serialeSelezionato = attTable.getValueAt(attTable.getSelectedRow(), 0).toString();

            new ProfiloAttrezzaturaGUI(controller, frame, serialeSelezionato);
        });
    }

    private void loadTable(Controller controller, String[] colonne) {
        ArrayList<String> listaSeriali = controller.getSerialiAttrezzature();
        ArrayList<String> listaTipi = controller.getListaTipiAttrezzature();
        ArrayList<Float> listaCosti = controller.getListaCostiAttrezzature();

        Object[][] righe = new Object[listaSeriali.size()][3];

        for (int i = 0; i < listaSeriali.size(); i++) {
            righe[i][0] = listaSeriali.get(i);
            righe[i][1] = listaTipi.get(i);
            righe[i][2] = listaCosti.get(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) attTable.getModel();
        dtm.setDataVector(righe, colonne);
    }
}
