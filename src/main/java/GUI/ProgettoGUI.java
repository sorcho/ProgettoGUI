package GUI;

import Controller.Controller;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgettoGUI {
    private JPanel progMainPanel;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JButton homeBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton acquistaButton;
    private JScrollPane tableScrollPane;
    private JTable progTable;
    private JFrame frame;

    public ProgettoGUI(@NotNull Controller controller, JFrame frameChiamante) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Progetti");
        frame.setContentPane(progMainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // POPOLO LA TABELLA DEI PROGETTI

        String[] colonne = {"CUP", "Nome", "Referente Scientifico", "Responsabile", "Budget"};

        ArrayList<String> listaCup = controller.getCupProgetti();
        ArrayList<String> listaNomi = controller.getListaNomiProgetti();
        ArrayList<String> listaRefSci = controller.getListaRefSci();
        ArrayList<String> listaResp = controller.getListaResponsabili();
        ArrayList<Float> listaBudget = controller.getListaBudget();

        Object[][] righe = new Object[listaCup.size()][5];

        for (int i = 0; i < listaCup.size(); i++) {
            righe[i][0] = listaCup.get(i);
            righe[i][1] = listaNomi.get(i);
            righe[i][2] = listaRefSci.get(i);
            righe[i][3] = listaResp.get(i);
            righe[i][4] = listaBudget.get(i) + "€";
        }

        DefaultTableModel tableModel = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        progTable.setModel(tableModel);
        progTable.setRowHeight(30);

        // SETTO TUTTI GLI ACTION LISTENER PER I PULSANTI

        homeBtn.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        addBtn.addActionListener(e -> {
            new AddProgettoGUI(controller);
            loadTable(controller, colonne);
        });

        removeBtn.addActionListener(e -> {
            String cupSelezionato = progTable.getValueAt(progTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il Progetto?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviProgetto(cupSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    loadTable(controller, colonne);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadTable(@NotNull Controller controller, @NotNull String[] colonne) {
        ArrayList<String> listaCup = controller.getCupProgetti();
        ArrayList<String> listaNomi = controller.getListaNomiProgetti();
        ArrayList<String> listaRefSci = controller.getListaRefSci();
        ArrayList<String> listaResp = controller.getListaResponsabili();
        ArrayList<Float> listaBudget = controller.getListaBudget();

        Object[][] righe = new Object[listaCup.size()][5];

        for (int i = 0; i < listaCup.size(); i++) {
            righe[i][0] = listaCup.get(i);
            righe[i][1] = listaNomi.get(i);
            righe[i][2] = listaRefSci.get(i);
            righe[i][3] = listaResp.get(i);
            righe[i][4] = listaBudget.get(i) + "€";
        }

        DefaultTableModel dtm = (DefaultTableModel) progTable.getModel();
        dtm.setDataVector(righe, colonne);
    }
}
