package fr.enssat.batman.evenement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.dj.DjsDAO;
import fr.enssat.batman.lieu.LieuxDAO;

public class EvenementsDAO {
	
	// Les détails de la connexion à la base de données
    private static final String URL = "jdbc:mysql://vps817240.ovh.net:3306/info_batman_schema";
    private static final String USERNAME = "info_batman";
    private static final String PASSWORD = "811p!er&Nm6";
    
    // Requêtes SQL
    private static final String SELECT_ALL_Events = "SELECT * FROM Evenement";
    private static final String INSERT_EVENT = "INSERT INTO Evenement (date, h_debut, h_fin, DJ, lieu) VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_EVENT_BY_ID = "UPDATE Evenement SET date = ?, h_debut = ?, h_fin = ?, DJ = ?, lieu = ? WHERE (date, DJ) = ?";
    private static final String DELETE_EVENT_BY_ID = "DELETE FROM DJ WHERE (date, DJ) = ?";
    
 // Méthode pour récupérer tous les Évènements
    public List<Evenement> getAllEvents() {
        List<Evenement> events = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Events);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                int h_debut = resultSet.getInt("h_debut");
                int h_fin = resultSet.getInt("h_fin");
                String dj = resultSet.getString("DJ");
                String lieu = resultSet.getString("lieu");
                events.add(new Evenement(date, h_debut, h_fin, new DjsDAO().getDJ(dj), new LieuxDAO().getLieu(lieu)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}
