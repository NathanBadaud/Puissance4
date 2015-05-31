package com.example.Puissance_Engine_4;

/**
 * Created by Nathan on 27/05/2015.
 */
public class Joueur {
	public Jeu jeu;
	public String nom;
	public int score;
	public int pionsRestants;
	public String couleur;
	public boolean actif;
	public boolean estHumain;

	//Constructeur
	public Joueur(Jeu jeu, String nom, String couleur, int pions) {
		this.jeu = jeu;
		this.nom = nom;
		this.couleur = couleur;
		this.score = 0;
		this.pionsRestants = pions;
		this.actif = false;
		this.estHumain = true;
	}

			/* Getters
	public String getNom() {
			return nom;
	}
	public int getScore() {
			return score;
	}
	public int getPionsRestants() {
			return pionsRestants;
	}
	public boolean getActif() {
			return actif;
	}	*/

	/* Setters
	public void setNom(String nom) {
			this.nom = nom;
	}
	public void setScore(int score) {
			this.score = score;
	}
	public void setPionsRestants(int pionsRestants) {
			this.pionsRestants= pionsRestants;
	}
	public void setActif(boolean actif) {
			this.actif= actif;
	} */
}
