package fr.enssat.batman.dj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.enssat.batman.DBManager;
import fr.enssat.batman.Pair;

public class DjsDAO {
	
	public DjsDAO() {
		
	}
    // Requêtes SQL
    private static final String SELECT_ALL_DJs = "SELECT * FROM DJ";
    private static final String SELECT_DJ_BY_NAME = "SELECT * FROM DJ WHERE nom_scene = ?";
    private static final String INSERT_DJ = "INSERT INTO DJ (nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String EDIT_DJ_BY_ID = "UPDATE DJ SET nom = ?, prenom = ?, nom_scene = ?, date_naissance = ?, lieu_residence = ?, style_musical = ? WHERE nom_scene = ?";
    private static final String DELETE_DJ_BY_ID = "DELETE FROM DJ WHERE nom_scene = ?";
    private static final String SELECT_TOP_DJ = "SELECT DJ.nom_scene, COUNT(*) as nb_evenements FROM DJ JOIN Evenement ON DJ.nom_scene = Evenement.DJ WHERE Evenement.date_fin_evenement < CURRENT_DATE() GROUP BY DJ.nom_scene ORDER BY nb_evenements DESC LIMIT 5";

    // Méthode pour récupérer tous les Djs
    public List<Dj> getAllDJs() {
        List<Dj> djs = new ArrayList<>();
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DJs);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String nomScene = resultSet.getString("nom_scene");
                String dateNaissance = resultSet.getString("date_naissance");
                String lieuResidence = resultSet.getString("lieu_residence");
                String styleMusical = resultSet.getString("style_musical");
                djs.add(new Dj(nom, prenom, nomScene, dateNaissance, lieuResidence, styleMusical));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return djs;
    }
    
    // Méthode pour récupérer un DJ selon son nom de scene
    public Dj getDJ(String nomScene) {
    	// Creation de la variable vide à retourner
    	Dj dj = new Dj();
    	
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DJ_BY_NAME)) {
        	// Creation de la query complete
        	preparedStatement.setString(1, nomScene);
        	//Execution de la query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Recupération des données reçues
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String dateNaissance = resultSet.getString("date_naissance");
            String lieuResidence = resultSet.getString("lieu_residence");
            String styleMusical = resultSet.getString("style_musical");
            
            //instanciation de dj
            dj = new Dj(nom, prenom, nomScene, dateNaissance, lieuResidence, styleMusical);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dj;
    }
    
    
    // Méthode pour insérer un nouvel Dj
    public void addDj(Dj dj) {
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DJ)) {
            preparedStatement.setString(1, dj.getNom());
            preparedStatement.setString(2, dj.getPrenom());
            preparedStatement.setString(3, dj.getNomScene());
            preparedStatement.setString(4, dj.getDateNaissance());
            preparedStatement.setString(5, dj.getLieuResidence());
            preparedStatement.setString(6, dj.getStyleMusical());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour éditer un DJ
    public void editDj(Dj dj) {
    	try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_DJ_BY_ID)) {
            preparedStatement.setString(1, dj.getNom());
            preparedStatement.setString(2, dj.getPrenom());
            preparedStatement.setString(3, dj.getNomScene());
            preparedStatement.setString(4, dj.getDateNaissance());
            preparedStatement.setString(5, dj.getLieuResidence());
            preparedStatement.setString(6, dj.getStyleMusical());
            preparedStatement.setString(7, dj.getNomScene());
            preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    // Méthode pour supprimer un DJ
    public void deleteDj(String nomScene) {
    	try (Connection connection = DBManager.getInstance().getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DJ_BY_ID)) {
    		// Creation de la query complete
        	preparedStatement.setString(1, nomScene);
        	//Execution de la query
            preparedStatement.executeUpdate();
            
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    // Méthode pour récupérer les top dj
    public List<Pair<String,Integer>> topDj() {
    	List<Pair<String, Integer>> topDjs = new ArrayList();
    	try (Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOP_DJ);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String dj = resultSet.getString("nom_scene");
                    int nbEvenements = resultSet.getInt("nb_evenements");
                    topDjs.add(new Pair<String,Integer>(dj,nbEvenements));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return topDjs;   	
    }
}
