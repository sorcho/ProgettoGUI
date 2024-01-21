package Controller;

import DAO.GestionaleDAO;
import DAO.ImpiegatoDAO;
import ImplementazionePostgresDAO.ImplementazioneGestionaleDAO;
import ImplementazionePostgresDAO.ImplementazioneImpiegatoDAO;
import Model.Impiegato;
import Model.Laboratorio;
import Model.Progetto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    // TODO aggiungi documentazione funzioni
    private List<Impiegato> listaImpiegati = new ArrayList<>();
    private List<Laboratorio> listaLaboratori = new ArrayList<>();
    private List<Progetto> listaProgetti = new ArrayList<>();

    public Controller() {
        getImpiegatiDatabase();
        getLaboratoriDatabase();
    }

    private void getImpiegatiDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCF = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<Date> listaDateNascita = new ArrayList<>();
        ArrayList<Date> listaDateAssunzione = new ArrayList<>();
        ArrayList<Integer> listaEta = new ArrayList<>();
        ArrayList<String> listaCategorie = new ArrayList<>();
        ArrayList<Float> listaSalari = new ArrayList<>();
        ArrayList<Date> listaDateScadenza = new ArrayList<>();
        ArrayList<String> listaTipiContratto = new ArrayList<>();

        gestionale.getImpiegati(
                listaCF,
                listaNomi,
                listaCognomi,
                listaDateNascita,
                listaDateAssunzione,
                listaEta,
                listaCategorie,
                listaSalari,
                listaDateScadenza,
                listaTipiContratto
        );

        for (int i = 0; i < listaCF.size(); i++) {
            listaImpiegati.add(new Impiegato(
                    listaCF.get(i),
                    listaNomi.get(i),
                    listaCognomi.get(i),
                    listaDateNascita.get(i),
                    listaDateAssunzione.get(i),
                    listaEta.get(i),
                    listaCategorie.get(i),
                    listaSalari.get(i),
                    listaDateScadenza.get(i),
                    listaTipiContratto.get(i)
            ));
        }
    }

    ;

    private void getLaboratoriDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaRespSci = new ArrayList<>();
        ArrayList<String> listaTopic = new ArrayList<>();
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();

        gestionale.getLaboratori(listaNomi, listaRespSci, listaTopic, listaNAfferenti);

        for (int i = 0; i < listaNomi.size(); i++) {
            listaLaboratori.add(new Laboratorio(
                    listaNomi.get(i),
                    listaRespSci.get(i),
                    listaTopic.get(i)
            ));
        }
    }

    public ArrayList<String> getListaCF() {
        ArrayList<String> listaCF = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCF.add(i.getCf());

        return listaCF;
    }

    ;

    public ArrayList<String> getListaNomi() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaNomi.add(i.getNome());

        return listaNomi;
    }

    ;

    public ArrayList<String> getListaCognomi() {
        ArrayList<String> listaCognomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCognomi.add(i.getCognome());

        return listaCognomi;
    }

    ;

    public ArrayList<String> getListaLaboratoriFromCF(String CF) {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for (Impiegato i : listaImpiegati) {
            if (i.getCf().equals(CF)) {
                impiegato = i;
                break;
            }
        }

        ArrayList<String> listaLaboratoriImpiegato = new ArrayList<>();
        boolean risultato = impiegatoDAO.getAfferenze(CF, listaLaboratoriImpiegato);

        if (risultato) {
            assert impiegato != null;
            for (Laboratorio l : listaLaboratori)
                for(String s : listaLaboratoriImpiegato)
                    if (l.getNome().equals(s))
                        impiegato.addLaboratorio(l);
        }

        return listaLaboratoriImpiegato;
    };

    public void aggiungiImpiegato(String cf, String nome, String cognome, Date dataNasc, Date dataAss, Date dataScad, String categoria, String cup) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        boolean risultato;

        if (dataScad == null)
            risultato = impiegatoDAO.assumiImpiegato(cf, nome, cognome, dataNasc, dataAss, categoria);
        else
            risultato = impiegatoDAO.assumiImpiegatoProgetto(cf, nome, cognome, dataNasc, dataAss, dataScad, cup);

        if (risultato)
            getImpiegatiDatabase();
    };

    public void rimuoviImpiegato(String cf) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        boolean risultato = impiegatoDAO.licenziaImpiegato(cf);

        if (risultato)
            getImpiegatiDatabase();
    }
}
