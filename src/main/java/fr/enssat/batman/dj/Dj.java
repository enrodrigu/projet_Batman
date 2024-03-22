package fr.enssat.batman.dj;

public class Dj {
	private String nom;
	private String prenom;
	private String nom_scene;
	private String date_naissance;
	private String lieu_residence;
	private String style_musical;
	
	public Dj(String nom, String prenom, String nom_scene, String date_naissance, String lieu_residence, String style_musical) {
		this.nom = nom;
		this.prenom = prenom;
		this.nom_scene = nom_scene;
		this.date_naissance = date_naissance;
		this.lieu_residence = lieu_residence;
		this.style_musical = style_musical;
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

	public String getNom_scene() {
		return nom_scene;
	}

	public void setNom_scene(String nom_scene) {
		this.nom_scene = nom_scene;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getLieu_residence() {
		return lieu_residence;
	}

	public void setLieu_residence(String lieu_residence) {
		this.lieu_residence = lieu_residence;
	}

	public String getStyle_musical() {
		return style_musical;
	}

	public void setStyle_musical(String style_musical) {
		this.style_musical = style_musical;
	}
	
	
}
