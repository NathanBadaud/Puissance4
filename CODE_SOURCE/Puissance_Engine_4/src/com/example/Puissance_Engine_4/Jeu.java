package com.example.Puissance_Engine_4;

/**
 * Created by Nathan on 27/05/2015.
 */
public class Jeu {
		public int tour;
		public int dernierPionX;
		public int dernierPionY;

		//Constructeur
		public Jeu(int tour, int dernierPionX, int dernierPionY) {
				this.tour = tour;
				this.dernierPionX = dernierPionX;
				this.dernierPionY = dernierPionY;
		}

		//Getters
		public int getTour() {
				return tour;
		}
		public int getDernierPionX() {
				return dernierPionX;
		}
		public int getDernierPionY() {
				return dernierPionY;
		}
		
		//Setters
		public void setTour(int tour) {
				this.tour = tour;
		}
		public void getDernierPionX(int dernierPionX) {
				this.dernierPionX = dernierPionX;
		}
		public void getDernierPionY(int dernierPionY) {
				this.dernierPionY = dernierPionY;
		}

		public void initialiser() {

		}

		public void affichageGraphique() {

		}

		public static int determinerVictoire() {
				return 0;
		}

		public void afficherVainqueur() {

		}
}
