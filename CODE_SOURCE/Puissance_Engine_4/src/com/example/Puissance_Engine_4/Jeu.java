package com.example.Puissance_Engine_4;

/**
 * Created by Nathan on 27/05/2015.
 */
public class Jeu {
		public int tour;
		public boolean joueurUnCommence, jeuSolo;
		public int dernierPionX;
		public int dernierPionY;
		public Plateau plateau;
		public Joueur[] joueurs;
		public IA bot;

		//Constructeur
		public Jeu(int hauteur, int largeur, String nomJoueur1, String nomJoueur2) {
			tour = 1;
			plateau = new Plateau(hauteur, largeur);
			joueurs = new Joueur[2];
			joueurs[0] = new Joueur(nomJoueur1, "jaune", hauteur*largeur/2);
			joueurs[1] = new Joueur(nomJoueur2, "rouge", hauteur*largeur/2);
			joueurUnCommence = true;
			jeuSolo = false;
		}

		public Jeu(int hauteur, int largeur, String nomJoueur) {
			this.tour = 1;
			this.plateau = new Plateau(hauteur, largeur);
			joueurs = new Joueur[1];
			joueurs[0] = new Joueur(nomJoueur, "jaune", hauteur*largeur/2);
			joueurUnCommence = true;
			bot = new IA("Ordinateur", "rouge", hauteur*largeur/2);
			jeuSolo = true;
		}

		public void nouvellePartie() {
				//reinitialisation du nombre de pions des joueurs
				joueurs[0].pionsRestants = plateau.hauteur*plateau.largeur/2;
				if (jeuSolo)
					bot.pionsRestants = plateau.hauteur*plateau.largeur/2;
				else
					joueurs[1].pionsRestants = plateau.hauteur*plateau.largeur/2;

				this.joueurUnCommence = !this.joueurUnCommence;

				for (int i = 0; i < plateau.largeur; i++) {
						plateau.colonnes[i].premiereCaseLibre = 0;
						for (int j = 0; j < plateau.hauteur; j++) {
								plateau.colonnes[i].cases[j].reinitialiserCase();
						}
				}
		}

		public void demarrerPartie() {
			joueurs[0].actif = joueurUnCommence;
			if (jeuSolo)
				bot.actif = !joueurUnCommence;
			else
				joueurs[1].actif = !joueurUnCommence;
		}

		public int determinerCaseDisponible(int colonne) {
			return plateau.colonnes[colonne].premiereCaseLibre;
		}

		public String placerPion(int coordonneeX, int coordonneeY){
			plateau.colonnes[coordonneeX].cases[coordonneeY].affecterPion(joueurActif());
			plateau.colonnes[coordonneeX].premiereCaseLibre += 1;

			this.dernierPionX = coordonneeX;
			this.dernierPionY = coordonneeY;
			return joueurActif().couleur;
		}

		public Joueur joueurActif() {
			if (joueurs[0].actif)
				return joueurs[0];
			else
				return joueurs[1];
		}

		public void joueurSuivant() {
			// au depart, les 2 joueurs sont inactifs, joueur 1 devient actif
			if (joueurs[0].actif == joueurs[1].actif) {
				joueurs[0].actif = !joueurs[0].actif;
			} else {
				//on change de joueur actif
				joueurs[0].actif = !joueurs[0].actif;
				joueurs[1].actif = !joueurs[1].actif;
			}
		}

		public void affichageGraphique() {

		}

		public int caseMemeProprietaire(int positionX, int positionY) {
			if (plateau.colonnes[dernierPionX].cases[dernierPionY].proprietaire ==  plateau.colonnes[positionX].cases[positionY].proprietaire)
				return 1;
			else
				return 0;
		}

		public String afficherVainqueur() {
			Joueur vainqueur = plateau.colonnes[dernierPionX].cases[dernierPionY].proprietaire;
			vainqueur.score += 1;
			return vainqueur.nom;
		}

		public int determinerVictoire() {
			// victoire verticale
			if (dernierPionY > 2) {
				if (caseMemeProprietaire(dernierPionX, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX, dernierPionY - 2)
					+caseMemeProprietaire(dernierPionX, dernierPionY - 3)
					== 3)
					return 1;

			}

			// victoires horizontales
			if (dernierPionX > 0 && dernierPionX < plateau.largeur - 2) {
				if (caseMemeProprietaire(dernierPionX - 1, dernierPionY)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY)
					== 3)
					return 2;
			}
			if (dernierPionX > 1 && dernierPionX < plateau.largeur - 1) {
				if (caseMemeProprietaire(dernierPionX - 2, dernierPionY)
					+caseMemeProprietaire(dernierPionX - 1, dernierPionY)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY)
					== 3)
					return 3;
			}
			if (dernierPionX < plateau.largeur - 3) {
				if (caseMemeProprietaire(dernierPionX + 1, dernierPionY)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY)
					+caseMemeProprietaire(dernierPionX + 3, dernierPionY)
					== 3)
					return 4;
			}
			if (dernierPionX > 2) {
				if (caseMemeProprietaire(dernierPionX - 1, dernierPionY)
					+caseMemeProprietaire(dernierPionX - 2, dernierPionY)
					+caseMemeProprietaire(dernierPionX - 3, dernierPionY)
					== 3)
					return 5;
			}

			// victoires diagonale bas-gauche, haut-droit
			if (dernierPionX > 0 && dernierPionX < plateau.largeur - 2 && dernierPionY > 0 && dernierPionY < plateau.hauteur - 2) {
				if (caseMemeProprietaire(dernierPionX - 1, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY + 1)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY + 2)
					== 3)
					return 6;
			}
			if (dernierPionX > 1 && dernierPionX < plateau.largeur - 1 && dernierPionY > 1 && dernierPionY < plateau.hauteur - 1) {
				if (caseMemeProprietaire(dernierPionX - 2, dernierPionY - 2)
					+caseMemeProprietaire(dernierPionX - 1, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY + 1)
					== 3)
					return 7;
			}
			if (dernierPionX < plateau.largeur - 3 && dernierPionY < plateau.hauteur - 3) {
				if (caseMemeProprietaire(dernierPionX + 1, dernierPionY + 1)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY + 2)
					+caseMemeProprietaire(dernierPionX + 3, dernierPionY + 3)
					== 3)
					return 8;
			}
			if (dernierPionX > 2 && dernierPionY > 2) {
				if (caseMemeProprietaire(dernierPionX - 1, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX - 2, dernierPionY - 2)
					+caseMemeProprietaire(dernierPionX - 3, dernierPionY - 3)
					== 3)
					return 9;
			}

			// victoires diagonale haut-gauche, bas-droit
			if (dernierPionX > 0 && dernierPionX < plateau.largeur - 2 && dernierPionY > 2 && dernierPionY < plateau.hauteur - 1) {
				if (caseMemeProprietaire(dernierPionX - 1, dernierPionY + 1)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY - 2)
					== 3)
					return 10;
			}
			if (dernierPionX > 1 && dernierPionX < plateau.largeur - 1 && dernierPionY > 1 && dernierPionY < plateau.hauteur - 2) {
				if (caseMemeProprietaire(dernierPionX - 2, dernierPionY + 2)
					+caseMemeProprietaire(dernierPionX - 1, dernierPionY + 1)
					+caseMemeProprietaire(dernierPionX + 1, dernierPionY - 1)
					== 3)
					return 1;
			}
			if (dernierPionX > 2 && dernierPionY < plateau.hauteur - 3) {
				if (caseMemeProprietaire(dernierPionX - 3, dernierPionY + 3)
					+caseMemeProprietaire(dernierPionX - 2, dernierPionY + 2)
					+caseMemeProprietaire(dernierPionX - 1, dernierPionY + 1)
					== 3)
					return 1;
			}
			if (dernierPionX < plateau.largeur - 3 && dernierPionY > 2) {
				if (caseMemeProprietaire(dernierPionX + 1, dernierPionY - 1)
					+caseMemeProprietaire(dernierPionX + 2, dernierPionY - 2)
					+caseMemeProprietaire(dernierPionX + 3, dernierPionY - 3)
					== 3)
					return 1;
			}

			return 0;
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
