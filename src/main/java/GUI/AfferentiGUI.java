package GUI;

import Controller.Controller;

import javax.swing.*;
import java.util.ArrayList;

public class AfferentiGUI {
    private JPanel affMainPanel;
    private JLabel titleLabel;
    private JList afferentiList;
    private JScrollPane listScrollPanel;
    private JFrame frame;

    public AfferentiGUI (Controller controller, JFrame frameChiamante, String nomeLab) {
        // IMPOSTO IL FRAME

        frame = new JFrame("Lista Afferenti");
        frame.setContentPane(affMainPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // POPOLO LA LISTA DEGLI AFFERENTI

        afferentiList.setEnabled(false);

        afferentiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListCellRenderer renderer =
                (DefaultListCellRenderer) afferentiList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        afferentiList.setModel(new DefaultListModel());
        DefaultListModel dfl = (DefaultListModel) afferentiList.getModel();

        ArrayList<String> listaAfferenti;

        listaAfferenti = controller.getListaAfferentiLaboratorio(nomeLab);

        for (String s : listaAfferenti)
            dfl.addElement(s);
    }
}
