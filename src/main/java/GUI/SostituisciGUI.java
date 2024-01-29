package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SostituisciGUI {
    private JPanel sostMainPanel;
    private JLabel titleLabel;
    private JScrollPane listScrollPanel;
    private JList impList;
    private JFrame frame;
    public SostituisciGUI(Controller controller, ArrayList<String> listaImpiegati) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Lista Afferenti");
        frame.setContentPane(sostMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(320,350));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // POPOLO LA LISTA DEGLI AFFERENTI

        impList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer =
                (DefaultListCellRenderer) impList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        impList.setModel(new DefaultListModel());
        DefaultListModel dfl = (DefaultListModel) impList.getModel();
    }
}
