package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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

        DefaultTableModel tableModel = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        attTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 16)); //setta il font delle celle della tabella
        attTable.setModel(tableModel);
        attTable.setRowHeight(30);

        attTable.getTableHeader().setReorderingAllowed(false); //fa in modo che le colonne non si spostino
        attTable.getTableHeader().setResizingAllowed(false); //fa in modo che la dimensione delle colenne non sia personalizzabile dall'utente

        resizeWidthTable(attTable); //serve per settare la larghezza delle colonne

        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 16);
        attTable.getTableHeader().setFont(headerFont);

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
                resizeWidthTable(attTable);
            }

        });

        addButton.addActionListener(e -> {
            AddAttrezzaturaGUI addAttrezzaturaGUI = new AddAttrezzaturaGUI(controller, frame);

            addAttrezzaturaGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                    resizeWidthTable(attTable);
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

    private void resizeWidthTable(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        table.getColumnModel().getColumn(0).setMinWidth(150);
        table.getColumnModel().getColumn(1).setMinWidth(250);
        table.getColumnModel().getColumn(2).setMinWidth(100);

        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
        table.getColumnModel().getColumn(2).setMaxWidth(100);
    }
}
