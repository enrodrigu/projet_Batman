package fr.enssat.batman.dj;

public class Dj {
	private String nom;
	private String prenom;
	private String nomScene;
	private String dateNaissance;
	private String lieuResidence;
	private String styleMusical;
	
	public Dj() {
		
	}
	
	public Dj(String nom, String prenom, String nomScene, String dateNaissance, String lieuResidence, String styleMusical) {
		this.nom = nom;
		this.prenom = prenom;
		this.nomScene = nomScene;
		this.dateNaissance = dateNaissance;
		this.lieuResidence = lieuResidence;
		this.styleMusical = styleMusical;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomScene() {
		return nomScene;
	}

	public void setNomScene(String nomScene) {
		this.nomScene = nomScene;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuResidence() {
		return lieuResidence;
	}

	public void setLieuResidence(String lieuResidence) {
		this.lieuResidence = lieuResidence;
	}

	public String getStyleMusical() {
		return styleMusical;
	}

	public void setStyleMusical(String styleMusical) {
		this.styleMusical = styleMusical;
	}
	
	
}
