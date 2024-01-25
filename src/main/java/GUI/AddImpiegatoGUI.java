package GUI;

import Controller.Controller;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddImpiegatoGUI {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ButtonGroup bg = new ButtonGroup();
    private JPanel addImpMainPanel;
    private JPanel insPanel;
    private JPanel buttonsPanel;
    private JButton annullaButton;
    private JButton okButton;
    private JLabel cfLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JTextField cfTextField;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JTextField dataNascitaTextField;
    private JTextField dataAssTextField;
    private JTextField dataScadTextField;
    private JLabel dataNascitaLabel;
    private JLabel dataAssLabel;
    private JLabel contrattoLabel;
    private JLabel dataScadLabel;
    private JRadioButton indRadioButton;
    private JRadioButton progRadioButton;
    private JLabel categoriaLabel;
    private JTextField categoriaTextField;
    private JLabel cupLabel;
    private JTextField cupTextField;
    JFrame frame;

    public AddImpiegatoGUI(Controller controller, JFrame frameChiamante) {
        // Inizializzazione e settaggio del frame principale
        frame = new JFrame("Impiegati");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(addImpMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        bg.add(indRadioButton);
        bg.add(progRadioButton);

        indRadioButton.addActionListener(e -> {
            dataScadTextField.setEnabled(false);
            cupTextField.setEnabled(false);
        });

        progRadioButton.addActionListener(e -> {
            dataScadTextField.setEnabled(true);
            cupTextField.setEnabled(true);
        });

        annullaButton.addActionListener(e -> frame.dispose());

        okButton.addActionListener(e -> {
            String cf;
            String nome;
            String cognome;
            String dataNascitaTemp;
            String dataAssunzioneTemp;
            String dataScadenzaTemp;
            String categoria;
            String cup;

            Date dataNascita = null;
            Date dataAssunzione = null;
            Date dataScadenza = null;

            int risposta = JOptionPane.showConfirmDialog(null, "Confermi di voler assumere questo impiegato?", "Conferma", JOptionPane.YES_NO_OPTION);

            if (risposta == JOptionPane.YES_OPTION) {
                cf = cfTextField.getText();
                nome = nomeTextField.getText();
                cognome = cognomeTextField.getText();
                dataNascitaTemp = dataNascitaTextField.getText();
                dataAssunzioneTemp = dataAssTextField.getText();
                dataScadenzaTemp = dataScadTextField.getText();
                cup = cupTextField.getText();

                if (!categoriaTextField.getText().isEmpty())
                    categoria = categoriaTextField.getText();
                else
                    categoria = null;

                try {
                    dataNascita = new Date(sdf.parse(dataNascitaTemp).getTime());
                    dataAssunzione = new Date(sdf.parse(dataAssunzioneTemp).getTime());

                    if (!dataScadenzaTemp.isEmpty())
                        dataScadenza = new Date(sdf.parse(dataScadenzaTemp).getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Errore nell'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    controller.aggiungiImpiegato(cf, nome, cognome, dataNascita, dataAssunzione, dataScadenza, categoria, cup);
                    JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
            }
        });
    }
}
