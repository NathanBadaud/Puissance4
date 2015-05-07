package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by matthieu on 06/05/2015.
 */
public class SettingsMenu extends Activity {
    Button ButtonMusique;
    Button ButtonSong;
    Button ButtonLangues;
    TextView TitleSettings;
    Button About;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_display);

        ButtonMusique = (Button) findViewById(R.id.buttonMusique);
        ButtonLangues = (Button) findViewById(R.id.buttonLangue);
        ButtonSong = (Button) findViewById(R.id.buttonSong);
        TitleSettings = (TextView) findViewById(R.id.TitleSettings);

    }
}