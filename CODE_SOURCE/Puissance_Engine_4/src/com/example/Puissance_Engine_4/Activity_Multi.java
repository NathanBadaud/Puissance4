package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_Multi extends Activity {
    TextView nomJoueur1, nomJoueur2;
		Button ButtonRecommencerMulti;
    private LinearLayout grille;
    public TextView vuePlateau[][];
    public Jeu jeuMulti;
    final int hauteur = 6;
    final int largeur = 7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mullti_game_display);

        nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
        nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

				//Recommencer une partie
				ButtonRecommencerMulti = (Button) findViewById(R.id.recommencerMulti);
				ButtonRecommencerMulti.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
								jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
								jeuMulti.demarrerPartie();
						}
				});

        vuePlateau = new TextView[hauteur][largeur];
        grille = (LinearLayout) findViewById(R.id.grille);

        // initialisation de la partie modele
        jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
        jeuMulti.demarrerPartie();

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
                                                            if (caseDispo < hauteur){
                                                                if (jeuMulti.placerPion(colonneIndex, caseDispo) == "jaune") {
                                                                    vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionjaune);
                                                                } else {
                                                                    vuePlateau[caseDispo][colonneIndex].setBackgroundResource(R.drawable.pionrouge);
                                                                }
                                                                jeuMulti.joueurSuivant();
                                                            }
														}
												});

                ligne.addView(vuePlateau[i][j]);
            }
            grille.addView(ligne);
        }
    }
}