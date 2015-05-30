package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_IA extends Activity {
    TextView nomJoueur1, nomJoueur2, score, scoreJoueur1, scoreJoueur2;
		ImageView imageJoueur1IA, imageJoueur2IA;
		Button ButtonRecommencerIA;
		private LinearLayout grille;
		public TextView vuePlateau[][];
		public Jeu jeuMulti;
		final int hauteur = 6;
		final int largeur = 7;

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
								jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
								jeuMulti.demarrerPartie();

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

				jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
				jeuMulti.demarrerPartie();
				changerImagesJoueurs();

				scoreJoueur1.setText(Integer.toString(jeuMulti.joueurs[0].score));
				scoreJoueur2.setText(Integer.toString(jeuMulti.joueurs[1].score));

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
																int caseDispo = jeuMulti.determinerCaseDisponible(colonneIndex);
																if (caseDispo < hauteur) {
																		if (jeuMulti.placerPion(colonneIndex, caseDispo) == "jaune") {
																				vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionjaune);
																		} else {
																				vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionrouge);
																		}
																		if (jeuMulti.determinerVictoire() == 0) {
																				jeuMulti.joueurSuivant();
																				changerImagesJoueurs();
																		} else {
																				Toast.makeText(getApplicationContext(),
																								"Quelqu'un a gagné", Toast.LENGTH_LONG)
																								.show();
																		}
																}
														}
												});

								ligne.addView(vuePlateau[i][j]);
						}
						grille.addView(ligne);
				}
		}

		public void changerImagesJoueurs() {
				if (jeuMulti.joueurs[0].actif == true) {
						imageJoueur1IA.setImageResource(R.drawable.player1on);
						imageJoueur2IA.setImageResource((R.drawable.player2));
				} else {
						imageJoueur1IA.setImageResource(R.drawable.player1);
						imageJoueur2IA.setImageResource((R.drawable.player2on));
				}
		}
}
