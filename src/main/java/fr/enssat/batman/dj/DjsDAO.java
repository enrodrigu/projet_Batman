package fr.enssat.batman.dj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DjsDAO {

    // Les détails de la connexion à la base de données
    private static final String URL = "jdbc:mysql://vps817240.ovh.net:3306/info_batman_schema";
    private static final String USERNAME = "info_batman";
    private static final String PASSWORD = "811p!er&Nm6";

    // Requêtes SQL
    private static final String SELECT_ALL_DJs = "SELECT * FROM DJ";
    private static final String SELECT_DJ_BY_NAME = "SELECT * FROM DJ WHERE nom_scene = ?";
    private static final String INSERT_DJ = "INSERT INTO DJ (nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String EDIT_DJ_BY_ID = "UPDATE DJ SET nom = ?, prenom = ?, nom_scene = ?, date_naissance = ?, lieu_residence = ?, style_musical = ? WHERE nom_scene = ?";
    private static final String DELETE_DJ_BY_ID = "DELETE FROM DJ WHERE nom_scene = ?";

    // Méthode pour récupérer tous les Djs
    public List<Dj> getAllDJs() {
        List<Dj> djs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DJs);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String nom_scene = resultSet.getString("nom_scene");
                String date_naissance = resultSet.getString("date_naissance");
                String lieu_residence = resultSet.getString("lieu_residence");
                String style_musical = resultSet.getString("style_musical");
                djs.add(new Dj(nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return djs;
    }
    
    // Méthode pour récupérer un DJ selon son nom de scene
    public Dj getDJ(String nom_scene) {
    	// Creation de la variable vide à retourner
    	Dj dj = new Dj();
    	
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DJ_BY_NAME)) {
        	// Creation de la query complete
        	preparedStatement.setString(1, nom_scene);
        	//Execution de la query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Recupération des données reçues
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String date_naissance = resultSet.getString("date_naissance");
            String lieu_residence = resultSet.getString("lieu_residence");
            String style_musical = resultSet.getString("style_musical");
            
            //instanciation de dj
            dj = new Dj(nom, prenom, nom_scene, date_naissance, lieu_residence, style_musical);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dj;
    }
    
    
    // Méthode pour insérer un nouvel Dj
    public void addDj(Dj dj) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DJ)) {
            preparedStatement.setString(1, dj.getNom());
            preparedStatement.setString(2, dj.getPrenom());
            preparedStatement.setString(3, dj.getNom_scene());
            preparedStatement.setString(4, dj.getDate_naissance());
            preparedStatement.setString(5, dj.getLieu_residence());
            preparedStatement.setString(6, dj.getStyle_musical());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
