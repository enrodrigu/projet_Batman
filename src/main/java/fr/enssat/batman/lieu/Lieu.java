package fr.enssat.batman.lieu;

public class Lieu {
	private int id;
	private String nom_lieu;
	private String ville;
	private String pays;
	private String continent;
	
	public Lieu() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_lieu() {
		return nom_lieu;
	}

	public void setNom_lieu(String nom_lieu) {
		this.nom_lieu = nom_lieu;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Lieu(String nom_lieu, String ville, String pays, String continent) {
		this.nom_lieu = nom_lieu;
		this.ville = ville;
		this.pays = pays;
		this.continent = continent;
	}
}
