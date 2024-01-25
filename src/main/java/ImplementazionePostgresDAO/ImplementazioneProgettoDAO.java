package ImplementazionePostgresDAO;

import DAO.ProgettoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplementazioneProgettoDAO implements ProgettoDAO {
    private Connection connection;

    public ImplementazioneProgettoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean aggiungiProgetto(String cup, String ref_sci, String resp, String nome) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("call add_progetto(?, ?, ?, ?)");
        query.setString(1, cup);
        query.setString(2, ref_sci);
        query.setString(3, resp);
        query.setString(4, nome);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean collegaLaboratorio(String nomeLab, String cup) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("call add_lavora(?, ?)");
        query.setString(1, nomeLab);
        query.setString(2, cup);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean eliminaProgetto(String cup) throws SQLException {
        PreparedStatement query;
        query = connection.prepareStatement("DELETE FROM progetto WHERE cup = ?");
        query.setString(1, cup);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }
}
