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
    TextView nomJoueur1, nomJoueur2, score, scoreJoueur1, scoreJoueur2;
		ImageView imageJoueur1IA, imageJoueur2IA;
		Button ButtonRecommencerIA;
		MediaPlayer musique;
		private LinearLayout grille;
		public TextView vuePlateau[][];
		public Jeu jeuSolo;
		final int hauteur = 6;
		final int largeur = 7;
		public int tempsEcoule;
		Timer temps;
		TimerTask timerTask;
		Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ia_game_display);

        nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
        nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

				imageJoueur1IA = (ImageView) findViewById(R.id.imageJoueur1IA);
				imageJoueur2IA = (ImageView) findViewById(R.id.imageJoueur2IA);

				score = (TextView) findViewById(R.id.scoreIA);
				scoreJoueur1 = (TextView) findViewById(R.id.scoreJoueur1IA);
				scoreJoueur2 = (TextView) findViewById(R.id.scoreJoueur2IA);

				ButtonRecommencerIA = (Button) findViewById(R.id.recommencerIA);
				ButtonRecommencerIA.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								jeuSolo = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
								jeuSolo.demarrerPartie();

								for (int i = hauteur-1; i >= 0; i--) {
										for (int j = 0; j < largeur; j++) {
												final int colonneIndex = j;
												vuePlateau[i][j]
																.setBackgroundResource(R.drawable.case_vide);
										}
								}
						}
				});

				vuePlateau = new TextView[hauteur][largeur];
				grille = (LinearLayout) findViewById(R.id.grille);

				musique = MediaPlayer.create(Activity_IA.this, R.raw.accepted);
				musique.start();

				jeuSolo = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur2.getText().toString());
				jeuSolo.demarrerPartie();
				changerImagesJoueurs();

				scoreJoueur1.setText(Integer.toString(jeuSolo.joueurs[0].score));
				scoreJoueur2.setText(Integer.toString(jeuSolo.joueurs[1].score));

				for (int i = hauteur-1; i >= 0; i--) {
						LinearLayout ligne = new LinearLayout(this);
						ligne.setOrientation(LinearLayout.HORIZONTAL);
						final int ligneIndex = i;
						for (int j = 0; j < largeur; j++) {
								final int colonneIndex = j;
								vuePlateau[i][j] = new TextView(this);
								vuePlateau[i][j]
												.setLayoutParams(new LinearLayout.LayoutParams(
																ViewGroup.LayoutParams.WRAP_CONTENT,
																ViewGroup.LayoutParams.WRAP_CONTENT, 1));
								vuePlateau[i][j]
												.setBackgroundResource(R.drawable.case_vide);

								//Ajoute un clickListener sur le plateau de jeu
								vuePlateau[i][j]
												.setOnClickListener(new View.OnClickListener() {
														@Override
														public void onClick(View view) {
																int caseDispo = jeuSolo.determinerCaseDisponible(colonneIndex);
																if (caseDispo < hauteur) {
																		if (jeuSolo.placerPion(colonneIndex, caseDispo) == "jaune") {
																				vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionjaune);
																		} else {
																				vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionrouge);
																		}
																		if (jeuSolo.determinerVictoire() == 0) {
																				jeuSolo.joueurSuivant();
																				changerImagesJoueurs();
																		} else {
																				//messagePopUp();
																				Toast.makeText(getApplicationContext(),
																								jeuSolo.afficherVainqueur() + " a gagné la partie. Une nouvelle partie commencera dans " + String.valueOf(tempsEcoule), Toast.LENGTH_LONG)
																								.show();

																				if (jeuSolo.afficherVainqueur() == jeuSolo.joueurs[1].nom) {
																						musique = MediaPlayer.create(Activity_IA.this, R.raw.baby);
																						musique.start();
																				}

																				actualiserScores();
																				InitialiserNouvelleGrille();
																				jeuSolo.nouvellePartie();
																				jeuSolo.demarrerPartie();
																		}
																}
														}
												});

								ligne.addView(vuePlateau[i][j]);
						}
						grille.addView(ligne);
				}
		}

		public void actualiserScores() {
				scoreJoueur1.setText(Integer.toString(jeuSolo.joueurs[0].score));
				scoreJoueur2.setText(Integer.toString(jeuSolo.joueurs[1].score));
		}

		public void InitialiserNouvelleGrille() {
				for (int i = hauteur-1; i >= 0; i--) {
						for (int j = 0; j < largeur; j++) {
								final int colonneIndex = j;
								vuePlateau[i][j]
												.setBackgroundResource(R.drawable.case_vide);
						}
				}
		}

		public void messagePopUp() {
				temps = new Timer();
				handler = new Handler();
				tempsEcoule = 3;
				final String nomVainqueur = jeuSolo.afficherVainqueur();

				timerTask = new TimerTask() {
						public void run() {
								handler.post(new Runnable() {
										public void run() {
												tempsEcoule--;
												if (tempsEcoule == 0) return;
												Toast.makeText(getApplicationContext(),
																nomVainqueur + " a gagné la partie. Une nouvelle partie commencera dans " + String.valueOf(tempsEcoule), Toast.LENGTH_LONG)
																.show();
										}
								});
						}
				};

				temps.schedule(timerTask, 0, 1000);
		}

		public void changerImagesJoueurs() {
				if (jeuSolo.joueurs[0].actif == true) {
						imageJoueur1IA.setImageResource(R.drawable.player1on);
						imageJoueur2IA.setImageResource((R.drawable.player2));
				} else {
						imageJoueur1IA.setImageResource(R.drawable.player1);
						imageJoueur2IA.setImageResource((R.drawable.player2on));
				}
		}
}
