package ImplementazionePostgresDAO;

import DAO.ImpiegatoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplementazioneImpiegatoDAO implements ImpiegatoDAO {
    private Connection connection;

    public ImplementazioneImpiegatoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean assumiImpiegato(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, String categoria) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("{call add_impiegato(?, ?, ?, ?, ?, ?)}");
        query.setString(1, cf);
        query.setString(2, nome);
        query.setString(3, cognome);
        query.setDate(4, dataNascita);
        query.setDate(5, dataAssunzione);
        query.setString(6, categoria);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean assumiImpiegatoProgetto(String cf, String nome, String cognome, Date dataNascita, Date dataAssunzione, Date dataScadenza, String cup) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("{call add_impiegato_progetto(?, ?, ?, ?, ?, ?, ?)}");
        query.setString(1, cf);
        query.setString(2, nome);
        query.setString(3, cognome);
        query.setDate(4, dataNascita);
        query.setDate(5, dataAssunzione);
        query.setDate(6, dataScadenza);
        query.setString(7, cup);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean licenziaImpiegato(String cf) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("{call delete_impiegato(?)}");
        query.setString(1, cf);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean sostituisciImpiegato(String vecchioCF, String nuovoCF) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("{call substitute_impiegato(?, ?)}");
        query.setString(1, vecchioCF);
        query.setString(2, nuovoCF);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean promuoviImpiegato(String cf, String promotoreDirigente) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("{call add_promozione(?, ?)}");
        query.setString(1, cf);
        query.setString(2, promotoreDirigente);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }
}
