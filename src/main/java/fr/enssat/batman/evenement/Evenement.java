package fr.enssat.batman.evenement;

public class Evenement {
	private String dateDebut;
	private String dateFin;
	private String nomDj;
	private String lieu;
	private String ville;
	
	public Evenement() {
		
	}
	
	public Evenement(String dateDebut, String dateFin, String nomDj, String lieu, String ville) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nomDj = nomDj;
		this.lieu = lieu;
		this.ville = ville;
	}

	public String getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDj() {
		return nomDj;
	}

	public void setDj(String dj) {
		this.nomDj = dj;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVilel(String ville) {
		this.ville = ville;
	}
	
	
}
