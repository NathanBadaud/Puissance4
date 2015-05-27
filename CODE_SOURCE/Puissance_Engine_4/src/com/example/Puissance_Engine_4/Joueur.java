package com.example.Puissance_Engine_4;

/**
 * Created by Nathan on 27/05/2015.
 */
public class Joueur {
		public String nom;
		public int score;
		public int pionsRestants;
		public boolean actif;

		//Constructeur
		public Joueur(String nom, int score, int pionsRestants, boolean actif) {
				this.nom = nom;
				this.score = score;
				this.pionsRestants = pionsRestants;
				this.actif = actif;
		}

		//Getters
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
		}

		//Setters
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
		}

		public void afficherNom() {

		}

		public void compterPions() {

		}

		public void compterScore() {

		}

		public void PlacerPion() {

		}

		public void renseignerNom() {

		}
}
