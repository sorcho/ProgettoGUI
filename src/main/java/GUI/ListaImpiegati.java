package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaImpiegati {
    private JPanel panel1;
    private JTextPane ezTextPane;
    private JButton tornaIndietroButton;
    public JFrame frame;

    public ListaImpiegati(JFrame frameChiamante){
        frame = new JFrame("Home");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
