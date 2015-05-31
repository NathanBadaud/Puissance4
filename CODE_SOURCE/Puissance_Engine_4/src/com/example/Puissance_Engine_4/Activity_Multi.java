package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_Multi extends Activity {
    TextView nomJoueur1, nomJoueur2, score, scoreJoueur1, scoreJoueur2;
    ImageView imageJoueur1, imageJoueur2;
    Button BoutonRecommencer, BoutonRevanche;
    MediaPlayer musique;
    private LinearLayout grille;
    public TextView vuePlateau[][];
    public Jeu jeuMulti;
    final int hauteur = 6;
    final int largeur = 7;
    public boolean partieTerminee = false;

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
        BoutonRecommencer = (Button) findViewById(R.id.recommencer);
        BoutonRecommencer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur2.getText().toString());
                    reinitialiserGrilleVide();
                    jeuMulti.demarrerPartie();
                    changerImagesJoueurs();
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
                jeuMulti.nouvellePartie();
                jeuMulti.demarrerPartie();
                BoutonRevanche.setVisibility(View.INVISIBLE);
                changerImagesJoueurs();
                initialiserGrilleListener();
            }
        });

        vuePlateau = new TextView[hauteur][largeur];
        grille = (LinearLayout) findViewById(R.id.grille);

        musique = MediaPlayer.create(Activity_Multi.this, R.raw.ratata);
        musique.start();

        // initialisation de la partie modele
        jeuMulti = new Jeu(hauteur, largeur, nomJoueur1.getText().toString(), nomJoueur1.getText().toString());
        jeuMulti.demarrerPartie();
        changerImagesJoueurs();
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

    public void initialiserGrilleListener() {
        for (int i = hauteur-1; i >= 0; i--) {
            for (int j = 0; j < largeur; j++) {
                final int colonneIndex = j;
                vuePlateau[i][j].setOnClickListener(new View.OnClickListener() {
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
                                musique = MediaPlayer.create(Activity_Multi.this, R.raw.whatwasthat);
                                musique.start();
                                Toast.makeText(getApplicationContext(),
                                        jeuMulti.afficherVainqueur() + " a gagné la partie !", Toast.LENGTH_LONG)
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




