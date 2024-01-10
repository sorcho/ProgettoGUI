package ImplementazionePostgresDAO;

import DAO.GestionaleDAO;
import Database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

public class ImplementazioneGestionaleDAO implements GestionaleDAO {
    Connection connection;

    public ImplementazioneGestionaleDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getImpiegati(ArrayList<String> listaCF, ArrayList<String> listaNomi, ArrayList<String> listaCognomi, ArrayList<Date> listaDateNascita, ArrayList<Date> listaDateAssunzione, ArrayList<Integer> listaEta, ArrayList<String> listaCategorie, ArrayList<Float> listaSalari, ArrayList<Date> listaDateScadenza, ArrayList<String> listaTipiContratto) {
        try {
            PreparedStatement query;
            query = connection.prepareStatement("SELECT * FROM impiegato");
            ResultSet rs = query.executeQuery();

            while (rs.next()){
                listaCF.add(rs.getString("cf"));
                listaNomi.add(rs.getString("nome"));
                listaCognomi.add(rs.getString("cognome"));
                listaDateNascita.add(rs.getDate("data_nascita"));
                listaDateAssunzione.add(rs.getDate("data_assunzione"));
                listaEta.add(rs.getInt("eta"));
                listaCategorie.add(rs.getString("categoria"));
                listaSalari.add(rs.getFloat("salario"));
                listaDateScadenza.add(rs.getDate("data_scadenza"));
                listaTipiContratto.add(rs.getString("tipo_contratto"));
            }

            connection.close();
        }catch (SQLException e){
            System.out.println("Errore nella lettura dei dati dal database.");
        }
    }

    @Override
    public void getLaboratori(ArrayList<String> listaNomi, ArrayList<String> listaRespSci, ArrayList<String> listaTopic, ArrayList<Integer> listaNAfferenti) {

    }

    @Override
    public void getAttrezzatura(ArrayList<String> listaSeriali, ArrayList<String> listaTipi, ArrayList<Float> listaCosti, ArrayList<String> listaNomiLab, ArrayList<String> listaCup) {

    }

    @Override
    public void getProgetti(ArrayList<String> listaCup, ArrayList<String> listaRefSci, ArrayList<String> listaResp, ArrayList<String> listaNomi, ArrayList<Float> listaBudget) {

    }
}
