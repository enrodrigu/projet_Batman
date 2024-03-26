package fr.enssat.batman.evenement;

import java.sql.Date;
import java.sql.Time;

import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.lieu.Lieu;

public class Evenement {
	private Date date;
	private Time hDebut;
	private Time hFin;
	private String nomDj;
	private String lieu;
	
	public Evenement() {
		
	}
	
	public Evenement(Date date, Time hDebut, Time hFin, String nomDj, String lieu) {
		this.date = date;
		this.hDebut = hDebut;
		this.hFin = hFin;
		this.nomDj = nomDj;
		this.lieu = lieu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getH_debut() {
		return hDebut;
	}

	public void setH_debut(Time h_debut) {
		this.hDebut = h_debut;
	}

	public Time getH_fin() {
		return hFin;
	}

	public void setH_fin(Time h_fin) {
		this.hFin = h_fin;
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
