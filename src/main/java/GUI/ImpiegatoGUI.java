package GUI;

import Controller.Controller;

import javax.swing.*;
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

    public ImpiegatoGUI(Controller controller, JFrame frameChiamante) {
        // Inizializzazione e settaggio del frame principale
        frame = new JFrame("Impiegati");
        frame.setSize(1000, 700);
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

        // Settaggio della tabella contenente tutti gli impiegati dell'azienda
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

        impTable.setModel(new DefaultTableModel(righe, colonne));
        impTable.setRowHeight(30);

        Font headerFont = new Font("JetBrains Mono", Font.BOLD, 20);
        impTable.getTableHeader().setFont(headerFont);

        // Aggiunta del mouse listener che permette, alla selezione di un impiegato nella tabella, la visualizzazione dei laboratori e progetti associati allo stesso
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

        // BOTTONI
        // settaggio del bottone per il ritorno alla home
        homeButton.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        // settaggio del bottone per l'aggiunta di un impiegato
        addButton.addActionListener(e -> {
            AddImpiegato addImpiegato = new AddImpiegato(controller, frame);
            addImpiegato.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadTable(controller, colonne);
                }
            });
        });

        // settaggio del bottone per l'eliminazione di un impiegato
        removeButton.addActionListener(e -> {
            String cfSelezionato = impTable.getValueAt(impTable.getSelectedRow(), 0).toString();

            int selezione = JOptionPane.showConfirmDialog(null, "Sicuro di voler licenziare l'impigato?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (selezione == JOptionPane.YES_OPTION) {
                try {
                    controller.rimuoviImpiegato(cfSelezionato);
                    JOptionPane.showMessageDialog(null, "Eliminazione avvenuta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                loadTable(controller, colonne);
            }
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
}
