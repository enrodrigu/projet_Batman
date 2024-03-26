package fr.enssat.batman.evenement;

import java.sql.Date;
import java.sql.Time;

import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.lieu.Lieu;

public class Evenement {
	private String dateDebut;
	private String dateFin;
	private String nomDj;
	private String lieu;
	
	public Evenement() {
		
	}
	
	public Evenement(String dateDebut, String dateFin, String nomDj, String lieu) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nomDj = nomDj;
		this.lieu = lieu;
	}

	public String getDateDebut() {
		return dateDebut;
	}
	
	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
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
	
	
}
