package fr.enssat.batman.evenement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.enssat.batman.DBManager;
import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.dj.DjsDAO;
import fr.enssat.batman.lieu.Lieu;
import fr.enssat.batman.lieu.LieuxDAO;

public class EvenementsDAO {
    
    // Requêtes SQL
    private static final String SELECT_ALL_Events = "SELECT * FROM Evenement";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM Evenement WHERE date = ? AND DJ = ?";
    private static final String INSERT_EVENT = "INSERT INTO Evenement (date, h_debut, h_fin, DJ, lieu) VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_EVENT_BY_ID = "UPDATE Evenement SET date = ?, h_debut = ?, h_fin = ?, DJ = ?, lieu = ? WHERE (date, DJ) = ?";
    private static final String DELETE_EVENT_BY_ID = "DELETE FROM DJ WHERE (date, DJ) = ?";
    
 // Méthode pour récupérer tous les Évènements
    public List<Evenement> getAllEvents() {
        List<Evenement> events = new ArrayList<>();
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Events);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String date_debut = resultSet.getString("date_debut_evenement");
                String date_fin = resultSet.getString("date_fin_evenement");
                String dj = resultSet.getString("DJ");
                String nom_lieu = resultSet.getString("lieu_evenement");
                
                // Convertir les dates et heures en chaînes de caractères dans le format ISO 8601
                //String isoDate = date.toLocalDate().toString();
                
                events.add(new Evenement(date_debut, date_fin, dj, nom_lieu));
                //events.add(new Evenement(isoDate, h_debut.toLocalTime(), h_fin.toLocalTime(), dj, nom_lieu));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public Evenement getEventById(Date date, String nomDj) {
    	Evenement event = new Evenement();
    	try (Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            	// Creation de la query complete
            	preparedStatement.setDate(1, date);
            	preparedStatement.setString(2, nomDj);
            	//Execution de la query
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Recupération des données reçues
                Time h_debut = resultSet.getTime("h_debut");
                Time h_fin = resultSet.getTime("h_fin");
                String nom_lieu = resultSet.getString("lieu");
                
                //instanciation de dj
                event = new Evenement(date, h_debut, h_fin, nomDj, nom_lieu);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return event;
    }
    
    public void addEvent(Evenement event) {
    	try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT)){
    		preparedStatement.setDate(1, event.getDate());
    		preparedStatement.setTime(2, event.getH_debut());
    		preparedStatement.setTime(3, event.getH_fin());
    		preparedStatement.setString(4, event.getDj());
    		preparedStatement.setString(5, event.getLieu());
    		preparedStatement.executeUpdate();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
