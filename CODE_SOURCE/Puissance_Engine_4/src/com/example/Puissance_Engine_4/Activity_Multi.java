package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_Multi extends Activity {
    TextView nomJoueur1, nomJoueur2;
    private LinearLayout grille;
    public TextView vuePlateau[][];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mullti_game_display);

        nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
        nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

        vuePlateau = new TextView[6][7];
        grille = (LinearLayout) findViewById(R.id.grille);

        for (int i = 5; i >= 0; i--) {
            LinearLayout ligne = new LinearLayout(this);
            ligne.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j <= 6; j++) {
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

														}
												});

                ligne.addView(vuePlateau[i][j]);
            }
            grille.addView(ligne);
        }
    }
}