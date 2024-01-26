package GUI;

import Controller.Controller;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class LaboratoriGUI {
    private JPanel labMainPanel;
    private JPanel buttonsPanel;
    private JPanel labPanel;
    private JButton homeBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JScrollPane tableScrollPane;
    private JTable labTable;
    private JButton collegaBtn;
    private JButton afferentiBtn;
    private JButton addAfferenteBtn;
    private JButton acquistaBtn;
    private JFrame frame;

    public LaboratoriGUI(@NotNull Controller controller, JFrame frameChiamante) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Laboratori");
        frame.setContentPane(labMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // POPOLO LA TABELLA DEI LABORATORI

        ArrayList<String> listaNomiLaboratori = controller.getListaNomiLaboratori();
        ArrayList<String> listaRespSci = controller.getListaRespSci();
        ArrayList<String> listaTopic = controller.getListaTopic();
        ArrayList<Integer> listaNAfferenti = controller.getListaNAfferenti();
        ArrayList<String> listaProgetti = controller.getListaProgettiAssociati();

        String[] colonne = {"Nome", "Responsabile Scientifico", "Topic", "Numero Afferenti", "Progetto Associato"};
        Object[][] righe = new Object[listaNomiLaboratori.size()][5];

        for (int i = 0; i < listaNomiLaboratori.size(); i++) {
            righe[i][0] = listaNomiLaboratori.get(i);
            righe[i][1] = listaRespSci.get(i);
            righe[i][2] = listaTopic.get(i);
            righe[i][3] = listaNAfferenti.get(i);
            righe[i][4] = listaProgetti.get(i);
        }

        labTable.setModel(new DefaultTableModel(righe, colonne));
        labTable.setRowHeight(30);

        // IMPOSTO TUTTI GLI ACTION LISTENER

        homeBtn.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        addBtn.addActionListener(e -> {
            AddLaboratorioGUI addLaboratorioGUI = new AddLaboratorioGUI(controller);

            addLaboratorioGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        removeBtn.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il Laboratorio?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviLaboratorio(labSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    loadTable(controller, colonne);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        collegaBtn.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();
            SelezionaCupGUI selezionaCupGUI = new SelezionaCupGUI(controller, labSelezionato);

            selezionaCupGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        afferentiBtn.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            new AfferentiGUI(controller, labSelezionato);
        });

        addAfferenteBtn.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();
            AddAfferenteGUI addAfferenteGUI = new AddAfferenteGUI(controller, labSelezionato);
            addAfferenteGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        acquistaBtn.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            new SelezionaSerialeGUI(controller, labSelezionato);
        });
    }

    private void loadTable(@NotNull Controller controller, @NotNull String[] colonne) {
        ArrayList<String> listaNomiLaboratori = controller.getListaNomiLaboratori();
        ArrayList<String> listaRespSci = controller.getListaRespSci();
        ArrayList<String> listaTopic = controller.getListaTopic();
        ArrayList<Integer> listaNAfferenti = controller.getListaNAfferenti();
        ArrayList<String> listaProgetti = controller.getListaProgettiAssociati();

        Object[][] righe = new Object[listaNomiLaboratori.size()][5];

        for (int i = 0; i < listaNomiLaboratori.size(); i++) {
            righe[i][0] = listaNomiLaboratori.get(i);
            righe[i][1] = listaRespSci.get(i);
            righe[i][2] = listaTopic.get(i);
            righe[i][3] = listaNAfferenti.get(i);
            righe[i][4] = listaProgetti.get(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) labTable.getModel();
        dtm.setDataVector(righe, colonne);
    }
}
