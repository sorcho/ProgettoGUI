package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgettoGUI {
    private JPanel progMainPanel;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JButton homeButton;
    private JButton addButton;
    private JButton rimuoviButton;
    private JButton acquistaButton;
    private JScrollPane tableScrollPane;
    private JTable progTable;
    private JFrame frame;

    public ProgettoGUI(Controller controller, JFrame frameChiamante) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Progetti");
        frame.setContentPane(progMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
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

        progTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 16)); //setta il font delle celle della tabella
        progTable.setModel(tableModel);
        progTable.setRowHeight(30);
        progTable.setPreferredScrollableViewportSize(new Dimension(950, 700));

        progTable.getTableHeader().setReorderingAllowed(false); //fa in modo che le colonne non si spostino
        progTable.getTableHeader().setResizingAllowed(false); //fa in modo che la dimensione delle colenne non sia personalizzabile dall'utente

        resizeWidthTable(progTable); //serve per settare la larghezza delle colonne

        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 16);
        progTable.getTableHeader().setFont(headerFont);

        // SETTO TUTTI GLI ACTION LISTENER PER I PULSANTI

        homeButton.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        addButton.addActionListener(e -> {
            new AddProgettoGUI(controller, frame);
            loadTable(controller, colonne);
            resizeWidthTable(progTable);
        });

        rimuoviButton.addActionListener(e -> {
            String cupSelezionato = progTable.getValueAt(progTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il Progetto?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviProgetto(cupSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                loadTable(controller, colonne);
                resizeWidthTable(progTable);
            }
        });
    }

    private void loadTable(Controller controller, String[] colonne) {
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
            righe[i][4] = listaBudget.get(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) progTable.getModel();
        dtm.setDataVector(righe, colonne);
    }

    private void resizeWidthTable(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);

        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(3).setMinWidth(200);
        table.getColumnModel().getColumn(4).setMinWidth(200);

        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
    }
}
