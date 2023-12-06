package ImplementazioneDatabasePostgres;

import DAO.ListaImpiegatiDAO;
import Database.ConnessioneDatabase;
import Model.Impiegato;

import java.sql.ClientInfoStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplementazioneListaImpiegati implements ListaImpiegatiDAO {
    private ConnessioneDatabase connessione;

    public ImplementazioneListaImpiegati() {
        try{
            connessione = ConnessioneDatabase.getInstance();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Impiegato> findAll() {
        List<Impiegato> listImpiegato = new ArrayList<Impiegato>();
        try {
            PreparedStatement query = this.connessione.connection.prepareStatement("select * from public.impiegato"); // sempre meglio specificare i campi invece di fare select *
            ResultSet rs = query.executeQuery();

            while (rs.next()){
                listImpiegato.add(new Impiegato(rs.getString("cf"), rs.getString("nome"),rs.getString("cognome"),rs.getString("categoria"),rs.getString("tipo_contratto"), rs.getDate("data_nascita"), rs.getDate("data_assunzione"), rs.getDate("data_scadenza"), rs.getInt("eta"), rs.getFloat("salario")));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return listImpiegato;
    }
}
