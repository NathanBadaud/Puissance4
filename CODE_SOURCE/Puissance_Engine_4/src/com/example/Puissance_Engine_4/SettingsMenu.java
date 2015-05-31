package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.*;
import android.media.AudioManager;

/**
 * Created by matthieu on 06/05/2015.
 */
public class SettingsMenu extends Activity {
    //Button ButtonMusique;
    //Button ButtonSong;
    Button ButtonLangues;
    Button ButtonAbout;
		TextView TitleSettings;
		Switch SwitchSon;
		AudioManager audio;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_display);

        //ButtonMusique = (Button) findViewById(R.id.buttonMusique);
				//ButtonSong = (Button) findViewById(R.id.buttonSong);

        ButtonLangues = (Button) findViewById(R.id.buttonLangue);
        ButtonAbout = (Button) findViewById(R.id.buttonAbout);
        TitleSettings = (TextView) findViewById(R.id.TitleSettings);
				SwitchSon = (Switch) findViewById(R.id.switch_son);

				//attach a listener to check for changes in state
				SwitchSon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
																				 boolean isChecked) {
								if(isChecked){
										audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
														AudioManager.ADJUST_LOWER, 0);
								} else {
										audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
														AudioManager.ADJUST_RAISE, 0);
								}
						}
				});
    }
}