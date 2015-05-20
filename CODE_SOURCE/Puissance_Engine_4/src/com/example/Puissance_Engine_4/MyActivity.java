package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button ButtonTitle;
    Button ButtonMulti;
    Button ButtonIA;
    Button ButtonSettings;
    //Activity SettingsMenuActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButtonTitle = (Button) findViewById(R.id.Title);
        ButtonMulti = (Button) findViewById(R.id.MultiButton);
        ButtonIA = (Button) findViewById(R.id.IAButton);
        ButtonSettings = (Button) findViewById(R.id.settings);

				//Démarrage des SETTINGS
        ButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, SettingsMenu.class);
                startActivity(intent);
            };


        });

				//Démarrage du jeu MULTIJOUEURS
        ButtonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, Activity_Multi.class);
                startActivity(intent);
            }

        });

				//Démarrage du jeu SOLO
        ButtonIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, Activity_IA.class);
                startActivity(intent);
            }

        });

    }
}
