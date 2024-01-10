package Controller;

import DAO.GestionaleDAO;
import ImplementazionePostgresDAO.ImplementazioneGestionaleDAO;
import Model.Impiegato;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Impiegato> listaImpiegati = new ArrayList<>();

    public Controller() {
        getImpiegatiDatabase();
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
    };
    public ArrayList<String> getListaCF(){
        ArrayList<String> listaCF = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCF.add(i.getCf());

        return listaCF;
    };
    public ArrayList<String> getListaNomi(){
        ArrayList<String> listaNomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaNomi.add(i.getNome());

        return listaNomi;
    };
    public ArrayList<String> getListaCognomi(){
        ArrayList<String> listaCognomi = new ArrayList<>();

        for (Impiegato i : listaImpiegati)
            listaCognomi.add(i.getCognome());

        return listaCognomi;
    };
}
