package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImpiegatoGUI {
    private JPanel impMainPanel;
    DefaultTableModel tableModel;
    private JFrame frame;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JPanel listPanel;
    private JScrollPane tableScrollPane;
    private JTable impTable;
    private JButton homeButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton profButton;
    private JList labList;
    private JLabel labLabel;
    private JButton promuoviButton;

    public ImpiegatoGUI(Controller controller, JFrame frameChiamante) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Impiegati");
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(impMainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Settaggio della lista dei laboratori associati a un impiegato
        labList.setModel(new DefaultListModel());
        DefaultListModel dfl = (DefaultListModel) labList.getModel();

        labList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer =
                (DefaultListCellRenderer) labList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // POPOLO LA TABELLA DEGLI IMPIEGATI

        String[] colonne = {"CF", "Nome", "Cognome"};

        ArrayList<String> listaCF = controller.getListaCF();
        ArrayList<String> listaNomi = controller.getListaNomi();
        ArrayList<String> listaCognomi = controller.getListaCognomi();

        String[][] righe = new String[listaCF.size()][3];

        for (int i = 0; i < listaCF.size(); i++) {
            righe[i][0] = listaCF.get(i);
            righe[i][1] = listaNomi.get(i);
            righe[i][2] = listaCognomi.get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(righe, colonne) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        impTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 16)); //setta il font delle celle della tabella
        impTable.setModel(tableModel);
        impTable.setRowHeight(30);

        impTable.getTableHeader().setReorderingAllowed(false); //fa in modo che le colonne non si spostino
        impTable.getTableHeader().setResizingAllowed(false); //fa in modo che la dimensione delle colenne non sia personalizzabile dall'utente

        resizeWidthTable(impTable); //serve per settare la larghezza delle colonne

        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 16);
        impTable.getTableHeader().setFont(headerFont);

        // IMPOSTO IL MOUSE LISTENER CHE VISUALIZZA I LABORATORI AI QUALI L'IMPIEGATO LAVORA
        impTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String cfSelezionato = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                String[] arrayLaboratori = controller.getListaLaboratoriFromCF(cfSelezionato).toArray(new String[0]);

                dfl.removeAllElements();

                for (String s : arrayLaboratori) dfl.addElement(s);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // SETTO TUTTI GLI ACTION LISTENER PER I PULSANTI

        // RITORNO ALLA HOME
        homeButton.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        // AGGIUNTA DI UN IMPIEGATO
        addButton.addActionListener(e -> {
            AddImpiegatoGUI addImpiegatoGUI = new AddImpiegatoGUI(controller, frame);
            addImpiegatoGUI.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                    resizeWidthTable(impTable);
                }
            });
        });

        // ELIMINAZIONE DI UN IMPIEGATO
        removeButton.addActionListener(e -> {
            String cfSelezionato = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler licenziare l'impiegato?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviImpiegato(cfSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                loadTable(controller, colonne);
                resizeWidthTable(impTable);
            }
        });

        // VISUALIZZAZIONE DEL PROFILO DI UN IMPIEGATO
        profButton.addActionListener(e -> {
            String cfSelezionato = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
            new ProfiloImpiegatoGUI(controller, frame, cfSelezionato);
        });

        // PROMOZIONE DI UN IMPIEGATO
        promuoviButton.addActionListener(e -> {
            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler promuovere il seguente impiegato?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (selezione == JOptionPane.YES_OPTION) {
                String cfSelezionato = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();
                boolean trovato = false;

                try {
                    for (String s : controller.getListaSenior()) {
                        if (s.equals(cfSelezionato)) {
                            trovato = true;
                            new PromuoviGUI(controller, frame, cfSelezionato);
                        }
                    }

                    if (!trovato)
                        controller.promuovi(cfSelezionato, null);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
            loadTable(controller, colonne);
            resizeWidthTable(impTable);
        });
    }

    private void loadTable(Controller controller, String[] colonne) {
        ArrayList<String> listaCF = controller.getListaCF();
        ArrayList<String> listaNomi = controller.getListaNomi();
        ArrayList<String> listaCognomi = controller.getListaCognomi();

        String[][] righe = new String[listaCF.size()][3];

        for (int i = 0; i < listaCF.size(); i++) {
            righe[i][0] = listaCF.get(i);
            righe[i][1] = listaNomi.get(i);
            righe[i][2] = listaCognomi.get(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) impTable.getModel();
        dtm.setDataVector(righe, colonne);
    }

    private void resizeWidthTable(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);

        table.getColumnModel().getColumn(0).setMinWidth(250);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(120);

        table.getColumnModel().getColumn(0).setMaxWidth(250);
        table.getColumnModel().getColumn(1).setMaxWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(120);
    }
}
