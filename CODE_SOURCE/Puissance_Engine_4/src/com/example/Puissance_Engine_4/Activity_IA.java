package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_IA extends Activity {
	TextView nomJoueur1, nomJoueur2, score, scoreJoueur, scoreIA;
	ImageView imageJoueur1, imageJoueur2;
	Button BoutonRecommencer, BoutonRevanche;
	MediaPlayer musique;
	private LinearLayout grille;
	public TextView vuePlateau[][];
	public Jeu jeu;
	final int hauteur = 6;
	final int largeur = 7;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ia_game_display);

		nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
		nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

		score = (TextView) findViewById(R.id.score);
		scoreJoueur = (TextView) findViewById(R.id.scoreJoueur);
		scoreIA = (TextView) findViewById(R.id.scoreIA);

		imageJoueur1 = (ImageView) findViewById(R.id.imageJoueur1);
		imageJoueur2 = (ImageView) findViewById(R.id.imageJoueur2);

		//Recommencer une partie
		BoutonRecommencer = (Button) findViewById(R.id.recommencer);
		BoutonRecommencer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				jeu = new Jeu(hauteur, largeur, nomJoueur1.getText().toString());
				reinitialiserGrilleVide();
				jeu.demarrerPartie();
				actualiserScores();
				if (BoutonRevanche.getVisibility() == View.VISIBLE) {
					BoutonRevanche.setVisibility(View.INVISIBLE);
					initialiserGrilleListener();
				}
			}
		});

		//Partie revanche
		BoutonRevanche = (Button) findViewById(R.id.revanche);
		BoutonRevanche.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				reinitialiserGrilleVide();
				jeu.nouvellePartie();
				jeu.demarrerPartie();
				BoutonRevanche.setVisibility(View.INVISIBLE);
				if (jeu.joueurActif() == jeu.bot){
					gererTourIA();
				}
				initialiserGrilleListener();
			}
		});

		vuePlateau = new TextView[hauteur][largeur];
		grille = (LinearLayout) findViewById(R.id.grille);

		musique = MediaPlayer.create(Activity_IA.this, R.raw.accepted);
		musique.start();

		// initialisation de la partie modele
		jeu = new Jeu(hauteur, largeur, nomJoueur1.getText().toString());
		jeu.demarrerPartie();
		actualiserScores();

		// initialisation de la grille
		for (int i = hauteur-1; i >= 0; i--) {
			LinearLayout ligne = new LinearLayout(this);
			ligne.setOrientation(LinearLayout.HORIZONTAL);
			for (int j = 0; j < largeur; j++) {
				vuePlateau[i][j] = new TextView(this);
				vuePlateau[i][j]
						.setLayoutParams(new LinearLayout.LayoutParams(
								ViewGroup.LayoutParams.WRAP_CONTENT,
								ViewGroup.LayoutParams.WRAP_CONTENT, 1));
				vuePlateau[i][j]
						.setBackgroundResource(R.drawable.case_vide);
				ligne.addView(vuePlateau[i][j]);
			}
			grille.addView(ligne);
		}
		initialiserGrilleListener();
	}

	public void actualiserScores() {
		scoreJoueur.setText(Integer.toString(jeu.joueurs[0].score));
		scoreIA.setText(Integer.toString(jeu.bot.score));
	}

	public void initialiserGrilleListener() {
		for (int i = hauteur-1; i >= 0; i--) {
			for (int j = 0; j < largeur; j++) {
				final int colonneIndex = j;
				vuePlateau[i][j].setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						int caseDispo = jeu.determinerCaseDisponible(colonneIndex);
						if (caseDispo < hauteur) {
							jeu.placerPion(colonneIndex, caseDispo);
							vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionjaune);
							if (jeu.determinerVictoire() == 0) {
								jeu.joueurSuivant();
								gererTourIA();
							} else {
								Toast.makeText(getApplicationContext(),
										jeu.afficherVainqueur() + " a gagné la partie !", Toast.LENGTH_LONG)
										.show();
																	actualiserScores();
								BoutonRevanche.setVisibility(View.VISIBLE);
								desactiverGrilleListener();
							}
						}
					}
				});
			}
		}
	}

	public void gererTourIA() {
		int[] coordonnees = new int[2];
		coordonnees = jeu.bot.placementPion();
		vuePlateau[coordonnees[1]][coordonnees[0]].setBackgroundResource(R.drawable.pionrouge);
		if (jeu.determinerVictoire() == 0) {
			jeu.joueurSuivant();
		} else {
			musique = MediaPlayer.create(Activity_IA.this, R.raw.baby);
			musique.start();
			Toast.makeText(getApplicationContext(),
					jeu.afficherVainqueur() + " a gagné la partie !", Toast.LENGTH_LONG)
					.show();
			actualiserScores();
			BoutonRevanche.setVisibility(View.VISIBLE);
			desactiverGrilleListener();
		}
	}

	public void desactiverGrilleListener() {
		for (int i = hauteur-1; i >= 0; i--) {
			for (int j = 0; j < largeur; j++) {
				vuePlateau[i][j].setOnClickListener(null);
			}
		}
	}

	public void reinitialiserGrilleVide() {
		for (int i = hauteur-1; i >= 0; i--) {
			for (int j = 0; j < largeur; j++) {
				final int colonneIndex = j;
				vuePlateau[i][j]
						.setBackgroundResource(R.drawable.case_vide);
			}
		}
	}
}
