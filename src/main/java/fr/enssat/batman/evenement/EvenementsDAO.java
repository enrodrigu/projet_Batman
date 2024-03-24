package fr.enssat.batman.evenement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Date date = resultSet.getDate("date");
                int h_debut = resultSet.getInt("h_debut");
                int h_fin = resultSet.getInt("h_fin");
                String dj = resultSet.getString("DJ");
                String nom_lieu = resultSet.getString("lieu");
                events.add(new Evenement(date, h_debut, h_fin, new DjsDAO().getDJ(dj), new LieuxDAO().getLieu(nom_lieu)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public Evenement getEventById(Date date, Dj dj) {
    	Evenement event = new Evenement();
    	try (Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            	// Creation de la query complete
            	preparedStatement.setDate(1, date);
            	preparedStatement.setString(2, dj.getNom_scene());
            	//Execution de la query
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Recupération des données reçues
                int h_debut = resultSet.getInt("h_debut");
                int h_fin = resultSet.getInt("h_fin");
                String nom_lieu = resultSet.getString("lieu");
                
                //instanciation de dj
                event = new Evenement(date, h_debut, h_fin, dj, new LieuxDAO().getLieu(nom_lieu));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return event;
    }
    
    public void addEvent(Evenement event) {
    	try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT)){
    		preparedStatement.setDate(0, event.getDate());
    		preparedStatement.setInt(1, event.getH_debut());
    		preparedStatement.setInt(2, event.getH_fin());
    		preparedStatement.setString(3, event.getDj().getNom_scene());
    		preparedStatement.setString(4, event.getLieu().getNom_lieu());
    		preparedStatement.executeUpdate();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
