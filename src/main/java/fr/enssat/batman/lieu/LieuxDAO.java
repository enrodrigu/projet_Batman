package fr.enssat.batman.lieu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LieuxDAO {

    // Les détails de la connexion à la base de données
    private static final String URL = "jdbc:mysql://vps817240.ovh.net:3306/info_batman_schema";
    private static final String USERNAME = "info_batman";
    private static final String PASSWORD = "811p!er&Nm6";

    // Requêtes SQL
    private static final String SELECT_ALL_PLACES = "SELECT * FROM Lieu";
    private static final String SELECT_PLACE_BY_NAME = "SELECT * FROM Lieu WHERE nom_lieu = ?";
    private static final String INSERT_PLACE = "INSERT INTO DJ (nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String EDIT_PLACE_BY_NAME = "UPDATE DJ SET nom = ?, prenom = ?, nom_scene = ?, date_naissance = ?, lieu_residence = ?, style_musical = ? WHERE nom_scene = ?";
    private static final String DELETE_PLACE_BY_NAME = "DELETE FROM DJ WHERE nom_scene = ?";

    // Méthode pour récupérer tous les Djs
    public List<Lieu> getAllPlaces() {
        List<Lieu> lieux = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLACES);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nom_lieu = resultSet.getString("nom");
                String ville = resultSet.getString("prenom");
                String pays = resultSet.getString("nom_scene");
                String continent = resultSet.getString("date_naissance");
                lieux.add(new Lieu(nom_lieu, ville, pays, continent));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lieux;
    }
    
    // Méthode pour récupérer un DJ selon son nom de scene
    public Lieu getLieu(String nom_lieu) {
    	// Creation de la variable vide à retourner
    	Lieu lieu = new Lieu();
    	
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLACE_BY_NAME)) {
        	// Creation de la query complete
        	preparedStatement.setString(1, nom_lieu);
        	//Execution de la query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Recupération des données reçues
            String ville = resultSet.getString("ville");
            String pays = resultSet.getString("pays");
            String continent = resultSet.getString("continent");
            
            //instanciation de dj
            lieu = new Lieu(nom_lieu, ville, pays, continent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lieu;
    }
}
