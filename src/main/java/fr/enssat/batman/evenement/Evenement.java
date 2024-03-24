package fr.enssat.batman.evenement;

import java.sql.Date;

import fr.enssat.batman.dj.Dj;
import fr.enssat.batman.lieu.Lieu;

public class Evenement {
	private Date date;
	private int h_debut;
	private int h_fin;
	private Dj dj;
	private Lieu lieu;
	
	public Evenement() {
		
	}
	
	public Evenement(Date date, int h_debut, int h_fin, Dj dj, Lieu lieu) {
		this.date = date;
		this.h_debut = h_debut;
		this.h_fin = h_fin;
		this.dj = dj;
		this.lieu = lieu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getH_debut() {
		return h_debut;
	}

	public void setH_debut(int h_debut) {
		this.h_debut = h_debut;
	}

	public int getH_fin() {
		return h_fin;
	}

	public void setH_fin(int h_fin) {
		this.h_fin = h_fin;
	}

	public Dj getDj() {
		return dj;
	}

	public void setDj(Dj dj) {
		this.dj = dj;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	
}
