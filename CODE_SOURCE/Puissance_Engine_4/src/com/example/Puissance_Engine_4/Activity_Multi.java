package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_Multi extends Activity {
		TextView nomJoueur1;
		TextView nomJoueur2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mullti_game_display);

				nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
				nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

    }
}