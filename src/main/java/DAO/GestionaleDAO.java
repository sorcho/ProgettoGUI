package DAO;

import java.sql.Date;
import java.util.ArrayList;

public interface GestionaleDAO {
    void getImpiegati(
            ArrayList<String> listaCF,
            ArrayList<String> listaNomi,
            ArrayList<String> listaCognomi,
            ArrayList<Date> listaDateNascita,
            ArrayList<Date> listaDateAssunzione,
            ArrayList<Integer> listaEta,
            ArrayList<String> listaCategorie,
            ArrayList<Float> listaSalari,
            ArrayList<Date> listaDateScadenza,
            ArrayList<String> listaTipiContratto
    );

    void getLaboratori(
            ArrayList<String> listaNomi,
            ArrayList<String> listaRespSci,
            ArrayList<String> listaTopic,
            ArrayList<Integer> listaNAfferenti
    );

    void getAttrezzatura(
            ArrayList<String> listaSeriali,
            ArrayList<String> listaTipi,
            ArrayList<Float> listaCosti,
            ArrayList<String> listaNomiLab,
            ArrayList<String> listaCup
    );

    void getProgetti(
            ArrayList<String> listaCup,
            ArrayList<String> listaRefSci,
            ArrayList<String> listaResp,
            ArrayList<String> listaNomi,
            ArrayList<Float> listaBudget
    );
}
