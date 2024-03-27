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
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM Evenement WHERE date_debut_evenement = ? AND DJ = ?";
    private static final String INSERT_EVENT = "INSERT INTO Evenement (date_debut_evenement, date_fin_evenement, DJ, lieu_evenement, ville_evenement) VALUES (?, ?, ?, ?, ?)";
    private static final String EDIT_EVENT_BY_ID = "UPDATE Evenement SET date = ?, h_debut = ?, h_fin = ?, DJ = ?, lieu = ? WHERE (date, DJ) = ?";
    private static final String DELETE_EVENT_BY_ID = "DELETE FROM DJ WHERE (date, DJ) = ?";
    
 // Méthode pour récupérer tous les Évènements
    public List<Evenement> getAllEvents() {
        List<Evenement> events = new ArrayList<>();
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Events);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String dateDebut = resultSet.getString("date_debut_evenement");
                String dateFin = resultSet.getString("date_fin_evenement");
                String dj = resultSet.getString("DJ");
                String nomLieu = resultSet.getString("lieu_evenement");
                String ville = resultSet.getString("ville_evenement");
                
                events.add(new Evenement(dateDebut, dateFin, dj, nomLieu, ville));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public Evenement getEventById(String dateDebut, String nomDj) {
    	Evenement event = new Evenement();
    	try (Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID)) {
            	// Creation de la query complete
            	preparedStatement.setString(1, dateDebut);
            	preparedStatement.setString(2, nomDj);
            	//Execution de la query
            	System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                
                // Recupération des données reçues
                
                String dateFin = resultSet.getString("date_fin_evenement");
                String nomLieu = resultSet.getString("lieu_evenement");
                String ville = resultSet.getString("ville_evenement");
                
                //instanciation de dj
                event = new Evenement(dateDebut, dateFin, nomDj, nomLieu, ville);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return event;
    }
    
    public void addEvent(Evenement event) {
    	try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT)){
    		preparedStatement.setString(1, event.getDateDebut());
    		preparedStatement.setString(2, event.getDateFin());
    		preparedStatement.setString(3, event.getDj());
    		preparedStatement.setString(4, event.getLieu());
    		preparedStatement.setString(5, event.getVille());
    		preparedStatement.executeUpdate();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
