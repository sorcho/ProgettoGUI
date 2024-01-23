package GUI;

import Controller.Controller;

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
    private JButton homeButton;
    private JButton addButton;
    private JButton deleteButton;
    private JScrollPane tableScrollPane;
    private JTable labTable;
    private JFrame frame;

    public LaboratoriGUI(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Laboratori");
        frame.setContentPane(labMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
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
        ArrayList<String> listaProgetti = controller.getListaProgetti();

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

        addButton.addActionListener(e -> {
            AddLaboratorioGUI addLaboratorioGUI = new AddLaboratorioGUI(controller, frame);

            addLaboratorioGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
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
            }
        });
    }

    private void loadTable(Controller controller, String[] colonne) {
        ArrayList<String> listaNomiLaboratori = controller.getListaNomiLaboratori();
        ArrayList<String> listaRespSci = controller.getListaRespSci();
        ArrayList<String> listaTopic = controller.getListaTopic();
        ArrayList<Integer> listaNAfferenti = controller.getListaNAfferenti();
        ArrayList<String> listaProgetti = controller.getListaProgetti();

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
