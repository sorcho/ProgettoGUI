package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class LaboratoriGUI {
    private JPanel labMainPanel;
    private JPanel buttonsPanel;
    private JPanel labPanel;
    private JButton homeButton;
    private JButton addButton;
    private JButton deleteButton;
    private JScrollPane tableScrollPane;
    private JTable labTable;
    private JButton collegaButton;
    private JButton afferentiButton;
    private JButton addAfferenteButton;
    private JButton acquistaButton;
    private JFrame frame;

    public LaboratoriGUI(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Laboratori");
        frame.setContentPane(labMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        homeButton.addActionListener(e -> {
            frame.dispose();
            frameChiamante.setVisible(true);
        });

        ArrayList<String> listaNomiLaboratori = controller.getListaNomiLaboratori();
        ArrayList<String> listaRespSci = controller.getListaRespSci();
        ArrayList<String> listaTopic = controller.getListaTopic();
        ArrayList<Integer> listaNAfferenti = controller.getListaNAfferenti();
        ArrayList<String> listaProgetti = controller.getListaProgettiAssociati();

        String[] colonne = {"Nome", "Responsabile Scientifico", "Topic", "Afferenti", "Progetto Associato"};
        Object[][] righe = new Object[listaNomiLaboratori.size()][5];

        for (int i = 0; i < listaNomiLaboratori.size(); i++) {
            righe[i][0] = listaNomiLaboratori.get(i);
            righe[i][1] = listaRespSci.get(i);
            righe[i][2] = listaTopic.get(i);
            righe[i][3] = listaNAfferenti.get(i);
            righe[i][4] = listaProgetti.get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        labTable.setModel(tableModel);
        labTable.setRowHeight(30);
        labTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 16)); //setta il font delle celle della tabella

        labTable.getTableHeader().setReorderingAllowed(false); //fa in modo che le colonne non si spostino
        labTable.getTableHeader().setResizingAllowed(false); //fa in modo che la dimensione delle colenne non sia personalizzabile dall'utente

        resizeWidthTable(labTable); //serve per settare la larghezza delle colonne

        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 16);
        labTable.getTableHeader().setFont(headerFont);

        addButton.addActionListener(e -> {
            AddLaboratorioGUI addLaboratorioGUI = new AddLaboratorioGUI(controller, frame);

            addLaboratorioGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                    resizeWidthTable(labTable);
                }
            });
        });

        deleteButton.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare il Laboratorio?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviLaboratorio(labSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                loadTable(controller, colonne);
                resizeWidthTable(labTable);
            }
        });

        collegaButton.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();
            SelezionaCupGUI selezionaCupGUI = new SelezionaCupGUI(controller, frame, labSelezionato);

            selezionaCupGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                    resizeWidthTable(labTable);
                }
            });
        });

        afferentiButton.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            new AfferentiGUI(controller, frame, labSelezionato);
        });

        addAfferenteButton.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();
            AddAfferenteGUI addAfferenteGUI = new AddAfferenteGUI(controller, frame, labSelezionato);
            addAfferenteGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                    resizeWidthTable(labTable);
                }
            });
        });

        acquistaButton.addActionListener(e -> {
            String labSelezionato = labTable.getValueAt(labTable.getSelectedRow(), 0).toString();

            new SelezionaSerialeGUI(controller, frame, labSelezionato);
        });
    }

    private void loadTable(Controller controller, String[] colonne) {
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

    private void resizeWidthTable(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);

        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(250);
        table.getColumnModel().getColumn(2).setMinWidth(300);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(4).setMinWidth(200);

        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
        table.getColumnModel().getColumn(2).setMaxWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(150);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
    }
}
