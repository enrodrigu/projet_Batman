package fr.enssat.batman.lieu;

public class Lieu {
	private int id;
	private String nom_lieu;
	private String ville;
	private String pays;
	private String continent;
	
	public Lieu(int id, String nom_lieu, String ville, String pays, String continent) {
		this.id = id;
		this.nom_lieu = nom_lieu;
		this.ville = ville;
		this.pays = pays;
		this.continent = continent;
	}
}
