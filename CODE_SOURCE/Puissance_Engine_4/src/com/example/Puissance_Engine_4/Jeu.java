package com.example.Puissance_Engine_4;

/**
 * Created by Nathan on 27/05/2015.
 */
public class Jeu {
		public int tour;
		public int dernierPionX;
		public int dernierPionY;
		public Plateau plateau;
		public Joueur joueurs[];

		//Constructeur
		public Jeu(int tour, int dernierPionX, int dernierPionY) {
				this.tour = tour;
				this.dernierPionX = dernierPionX;
				this.dernierPionY = dernierPionY;
		}

		public void initialiser(int hauteur, int largeur, String nomJoueur1, String nomJoueur2) {
				this.plateau = new Plateau(hauteur, largeur);
				joueurs[0] = new Joueur(nomJoueur1, "jaune", hauteur*largeur/2);
				joueurs[1] = new Joueur(nomJoueur2, "rouge", hauteur*largeur/2);
		}

		public void nouvellePartie(int hauteur, int largeur) {
				//réinitialisation du nombre de pions des joueurs
				joueurs[0].pionsRestants = hauteur*largeur/2;
				joueurs[1].pionsRestants = hauteur*largeur/2;

				for (int i = 0; i < plateau.largeur; i++) {
						for (int j = 0; j < plateau.hauteur; j++) {
								plateau.colonnes[largeur].cases[hauteur].reinitialiserCase();
						}
				}
		}

		public void affichageGraphique() {

		}

		public static int determinerVictoire() {
				return 0;
		}

		public void afficherVainqueur() {

		}

				/*		Getters
		public int getTour() {
				return tour;
		}
		public int getDernierPionX() {
				return dernierPionX;
		}
		public int getDernierPionY() {
				return dernierPionY;
		}		*/

		/*		Setters
		public void setTour(int tour) {
				this.tour = tour;
		}
		public void getDernierPionX(int dernierPionX) {
				this.dernierPionX = dernierPionX;
		}
		public void getDernierPionY(int dernierPionY) {
				this.dernierPionY = dernierPionY;
		}		*/
}
