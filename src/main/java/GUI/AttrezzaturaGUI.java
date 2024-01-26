package GUI;

import Controller.Controller;
import org.jetbrains.annotations.NotNull;

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
    private JButton homeBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton profileBtn;
    private JScrollPane tableScrollPane;
    private JTable attTable;
    private JFrame frame;

    public AttrezzaturaGUI(@NotNull Controller controller, JFrame frameChiamante) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Attrezzatura");
        frame.setContentPane(attMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        // POPOLO LA TABELLA DELLE ATTREZZATURE

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

        // IMPOSTO TUTTI GLI ACTION LISTENER

        homeBtn.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        removeBtn.addActionListener(e -> {
            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare l'Attrezzatura?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (selezione == JOptionPane.YES_OPTION) {
                String serialeSelezionato = attTable.getValueAt(attTable.getSelectedRow(), 0).toString();

                try {
                    controller.rimuoviAttrezzatura(serialeSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    loadTable(controller, colonne);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addBtn.addActionListener(e -> {
            AddAttrezzaturaGUI addAttrezzaturaGUI = new AddAttrezzaturaGUI(controller);

            addAttrezzaturaGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        profileBtn.addActionListener(e -> {
            String serialeSelezionato = attTable.getValueAt(attTable.getSelectedRow(), 0).toString();

            new ProfiloAttrezzaturaGUI(controller, serialeSelezionato);
        });
    }

    private void loadTable(@NotNull Controller controller, @NotNull String[] colonne) {
        ArrayList<String> listaSeriali = controller.getSerialiAttrezzature();
        ArrayList<String> listaTipi = controller.getListaTipiAttrezzature();
        ArrayList<Float> listaCosti = controller.getListaCostiAttrezzature();

        Object[][] righe = new Object[listaSeriali.size()][3];

        for (int i = 0; i < listaSeriali.size(); i++) {
            righe[i][0] = listaSeriali.get(i);
            righe[i][1] = listaTipi.get(i);
            righe[i][2] = listaCosti.get(i) + "€";
        }

        DefaultTableModel dtm = (DefaultTableModel) attTable.getModel();
        dtm.setDataVector(righe, colonne);
    }
}
