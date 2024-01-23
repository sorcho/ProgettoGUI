package Controller;

import DAO.GestionaleDAO;
import DAO.ImpiegatoDAO;
import DAO.LaboratorioDAO;
import ImplementazionePostgresDAO.ImplementazioneGestionaleDAO;
import ImplementazionePostgresDAO.ImplementazioneImpiegatoDAO;
import ImplementazionePostgresDAO.ImplementazioneLaboratorioDAO;
import Model.*;

import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    // TODO aggiungi documentazione funzioni
    private List<Impiegato> listaImpiegati = new ArrayList<>();
    private List<Laboratorio> listaLaboratori = new ArrayList<>();
    private List<Progetto> listaProgetti = new ArrayList<>();
    private List<Attrezzatura> listaAttrezzature = new ArrayList<>();
    private List<Promozione> listaPromozioni = new ArrayList<>();

    public Controller() {
        getImpiegatiDatabase();
        getLaboratoriDatabase();
        getPromozioniDatabase();
        getAttrezzatureDatabase();
        getProgettiDatabase();
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

    private void getLaboratoriDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaRespSci = new ArrayList<>();
        ArrayList<String> listaTopic = new ArrayList<>();
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();
        ArrayList<String> listaProgetti = new ArrayList<>();

        gestionale.getLaboratori(listaNomi, listaRespSci, listaTopic, listaNAfferenti, listaProgetti);

        for (int i = 0; i < listaNomi.size(); i++) {
            listaLaboratori.add(new Laboratorio(
                    listaNomi.get(i),
                    listaRespSci.get(i),
                    listaTopic.get(i),
                    listaNAfferenti.get(i),
                    listaProgetti.get(i)
            ));
        }
    }

    private void getPromozioniDatabase() {
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCF = new ArrayList<>();
        ArrayList<Date> listaDatePassaggio = new ArrayList<>();
        ArrayList<String> listaVecchieCategorie = new ArrayList<>();
        ArrayList<String> listaNuoveCategorie = new ArrayList<>();
        ArrayList<String> listaPromotoriDirigenti = new ArrayList<>();

        gestionale.getPromozioni(
                listaCF,
                listaDatePassaggio,
                listaVecchieCategorie,
                listaNuoveCategorie,
                listaPromotoriDirigenti
        );

        for (int i = 0; i < listaCF.size(); i++) {
            listaPromozioni.add(new Promozione(
                    listaDatePassaggio.get(i),
                    listaVecchieCategorie.get(i),
                    listaNuoveCategorie.get(i),
                    listaPromotoriDirigenti.get(i),
                    listaCF.get(i)));
        }
    }

    private void getProgettiDatabase(){
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaCUP = new ArrayList<>();
        ArrayList<String> listaRefSci = new ArrayList<>();
        ArrayList<String> listaResp = new ArrayList<>();
        ArrayList<String> listaNome = new ArrayList<>();
        ArrayList<Float> listaBudget = new ArrayList<>();

        gestionale.getProgetti(listaCUP, listaRefSci, listaResp, listaNome, listaBudget);

        for (int i = 0; i < listaCUP.size(); i++) {
            listaProgetti.add(new Progetto(
                    listaCUP.get(i),
                    listaRefSci.get(i),
                    listaResp.get(i),
                    listaNome.get(i),
                    listaBudget.get(i)
            ));
        }
    }

    private void getAttrezzatureDatabase(){
        GestionaleDAO gestionale = new ImplementazioneGestionaleDAO();

        ArrayList<String> listaSeriale = new ArrayList<>();
        ArrayList<String> listaTipo = new ArrayList<>();
        ArrayList<Float> listaCosto = new ArrayList<>();
        ArrayList<String> listaNomiLab = new ArrayList<>();
        ArrayList<String> listaCUP = new ArrayList<>();

        gestionale.getAttrezzatura(
                listaSeriale,
                listaTipo,
                listaCosto,
                listaNomiLab,
                listaCUP
        );

        for (int i = 0; i < listaSeriale.size(); i++) {
            listaAttrezzature.add(new Attrezzatura(
                    listaSeriale.get(i),
                    listaTipo.get(i),
                    listaCosto.get(i),
                    listaNomiLab.get(i),
                    listaCUP.get(i)
            ));
        }
    }

    public ArrayList<String> getListaCF() {
        ArrayList<String> listaCF = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCF.add(i.getCf());

        return listaCF;
    }

    public ArrayList<String> getListaNomi() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaNomi.add(i.getNome());

        return listaNomi;
    }

    public ArrayList<String> getListaCognomi() {
        ArrayList<String> listaCognomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCognomi.add(i.getCognome());

        return listaCognomi;
    }

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
            impiegato.setListaLaboratori(listaLaboratoriImpiegato);
        }

        return listaLaboratoriImpiegato;
    }

    public ArrayList<Promozione> getListaPromozioniFromCF(String CF) {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        Impiegato impiegato = null;

        for (Impiegato i : listaImpiegati) {
            if (i.getCf().equals(CF)) {
                impiegato = i;
                break;
            }
        }

        ArrayList<Promozione> listaPromozioniImpiegato = new ArrayList<>();
        boolean risultato = impiegatoDAO.getPromozioni(CF, listaPromozioniImpiegato);

        if (risultato) {
            assert impiegato != null;
            impiegato.setListaPromozioni(listaPromozioniImpiegato);
        }

        return listaPromozioniImpiegato;
    }

    public void aggiungiImpiegato(String cf, String nome, String cognome, Date dataNasc, Date dataAss, Date dataScad, String categoria, String cup) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        if (dataScad == null)
            impiegatoDAO.assumiImpiegato(cf, nome, cognome, dataNasc, dataAss, categoria);
        else
            impiegatoDAO.assumiImpiegatoProgetto(cf, nome, cognome, dataNasc, dataAss, dataScad, cup);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    public void rimuoviImpiegato(String cf) throws SQLException {
        ImpiegatoDAO impiegatoDAO = new ImplementazioneImpiegatoDAO();

        impiegatoDAO.licenziaImpiegato(cf);

        listaImpiegati.clear();
        getImpiegatiDatabase();
    }

    public Impiegato getDatiProfilo(String cf) {
        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(cf, i.getCf())) {
                return i;
            }
        }

        return null;
    }

    public ArrayList<String> getListaNomiLaboratori() {
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNomi.add(l.getNome());

        return listaNomi;
    }

    public ArrayList<String> getListaRespSci() {
        ArrayList<String> listaRespSci = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaRespSci.add(l.getRespSci());

        return listaRespSci;
    }

    public ArrayList<String> getListaTopic() {
        ArrayList<String> listaTopic = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaTopic.add(l.getTopic());

        return listaTopic;
    }

    public ArrayList<Integer> getListaNAfferenti() {
        ArrayList<Integer> listaNAfferenti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaNAfferenti.add(l.getN_afferenti());

        return listaNAfferenti;
    }

    public ArrayList<String> getListaProgetti() {
        ArrayList<String> listaProgetti = new ArrayList<>();

        for (Laboratorio l : listaLaboratori)
            listaProgetti.add(l.getProgetto());

        return listaProgetti;
    }

    public ArrayList<String> getListaSenior () {
        ArrayList<String> listaSenior = new ArrayList<>();

        for (Impiegato i : listaImpiegati) {
            if (Objects.equals(i.getCategoria(), "senior")) {
                listaSenior.add(i.getCf());
            }
        }
        return listaSenior;
    }

    public ArrayList<String> getListaDirigenti (){
        ArrayList<String> listaDirigenti = new ArrayList<>();

        for (Impiegato i : listaImpiegati){
            if (Objects.equals(i.getCategoria(), "senior")) {
                listaDirigenti.add(i.getCf());
            }
        }

        return listaDirigenti;
    }

    public ArrayList<String> getCupProgetti(){
        ArrayList<String> listaCupProgetti = new ArrayList<>();

        listaCupProgetti.add(null);

        for(Progetto p : listaProgetti)
            listaCupProgetti.add(p.getCup());

        return listaCupProgetti;
    }

    public void aggiungiLaboratorio(String nome, String respSci, String topic) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.aggiungiLaboratorio(nome, respSci, topic);

        listaLaboratori.clear();
        getLaboratoriDatabase();
    }

    public void rimuoviLaboratorio(String nomeLab) throws SQLException {
        LaboratorioDAO laboratorioDAO = new ImplementazioneLaboratorioDAO();

        laboratorioDAO.rimuoviLaboratorio(nomeLab);

        listaLaboratori.clear();
        getLaboratoriDatabase();
    }

    public void associaLaboratorioProgetto(){


    }
}