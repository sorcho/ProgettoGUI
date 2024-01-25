package GUI;

import Controller.Controller;
import Model.Attrezzatura;

import javax.swing.*;

public class ProfiloAttrezzaturaGUI {
    private JPanel profAttMainPanel;
    private JLabel titleLabel;
    private JLabel serialeLabel;
    private JLabel tipoLabel;
    private JLabel labLabel;
    private JLabel progLabel;
    private JLabel costoLabel;
    private JFrame frame;

    public ProfiloAttrezzaturaGUI(Controller controller, JFrame frameChiamante, String serialeSelezionato){
        frame = new JFrame("Profilo Attrezzatura");
        frame.setContentPane(profAttMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        Attrezzatura a = controller.getDatiAttrezzatura(serialeSelezionato);

        serialeLabel.setText(serialeLabel.getText() + a.getSeriale());
        tipoLabel.setText(tipoLabel.getText() + a.getTipo());
        labLabel.setText(labLabel.getText() + a.getNomeLab());
        progLabel.setText(progLabel.getText() + a.getCup());
        costoLabel.setText(costoLabel.getText() + a.getCosto() + "€");
    }
}
