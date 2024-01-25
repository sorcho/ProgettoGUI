package ImplementazionePostgresDAO;

import DAO.AttrezzaturaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplementazioneAttrezzaturaDAO implements AttrezzaturaDAO {
    private Connection connection;

    public ImplementazioneAttrezzaturaDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean aggiungiAttrezzatura(String seriale, String tipo) throws SQLException {
        PreparedStatement query;
        query = connection.prepareCall("call add_attrezzatura(?, ?)");
        query.setString(1, seriale);
        query.setString(2, tipo);
        int risultato = query.executeUpdate();
        return risultato == 1;
    }

    @Override
    public boolean eliminaAttrezzatura(String seriale) throws SQLException {
        PreparedStatement query;
        query = connection.prepareStatement("delete from attrezzatura where seriale = ?");

        query.setString(1, seriale);

        int risultato = query.executeUpdate();
        return risultato == 1;
    }
}
