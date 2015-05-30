package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_Multi extends Activity {
    TextView nomJoueur1, nomJoueur2, score, scoreJoueur1, scoreJoueur2;
    ImageView imageJoueur1, imageJoueur2;
    Button ButtonRecommencerMulti;
    private LinearLayout grille;
    public TextView vuePlateau[][];
    public Jeu jeuMulti;
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
        setContentView(R.layout.mullti_game_display);

        nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
        nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

        score = (TextView) findViewById(R.id.score);
        scoreJoueur1 = (TextView) findViewById(R.id.scoreJoueur1);
        scoreJoueur2 = (TextView) findViewById(R.id.scoreJoueur2);

        imageJoueur1 = (ImageView) findViewById(R.id.imageJoueur1);
        imageJoueur2 = (ImageView) findViewById(R.id.imageJoueur2);


        //Recommencer une partie
        ButtonRecommencerMulti = (Button) findViewById(R.id.recommencerMulti);
        ButtonRecommencerMulti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
                    InitialiserNouvelleGrille();
                    jeuMulti.demarrerPartie();
                }
        });

        vuePlateau = new TextView[hauteur][largeur];
        grille = (LinearLayout) findViewById(R.id.grille);

        // initialisation de la partie modele
        jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur2.getText().toString());
        jeuMulti.demarrerPartie();
        changerImagesJoueurs();
        actualiserScores();

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
                                                                //messagePopUp();
                                                                Toast.makeText(getApplicationContext(),
                                                                        jeuMulti.afficherVainqueur() + " a gagné la partie. Une nouvelle partie commencera dans " + String.valueOf(tempsEcoule), Toast.LENGTH_LONG)
                                                                        .show();

                                                            actualiserScores();
                                                            InitialiserNouvelleGrille();
                                                            jeuMulti.nouvellePartie();
                                                            jeuMulti.demarrerPartie();
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
        scoreJoueur1.setText(Integer.toString(jeuMulti.joueurs[0].score));
        scoreJoueur2.setText(Integer.toString(jeuMulti.joueurs[1].score));
    }

    public void changerImagesJoueurs() {
        if (jeuMulti.joueurs[0].actif == true) {
            imageJoueur1.setImageResource(R.drawable.player1on);
            imageJoueur2.setImageResource((R.drawable.player2));
        } else {
            imageJoueur1.setImageResource(R.drawable.player1);
            imageJoueur2.setImageResource((R.drawable.player2on));
        }
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
        final String nomVainqueur = jeuMulti.afficherVainqueur();

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
}




