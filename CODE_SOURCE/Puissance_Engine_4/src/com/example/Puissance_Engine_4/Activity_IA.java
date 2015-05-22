package com.example.Puissance_Engine_4;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by matthieu on 06/05/2015.
 */
public class Activity_IA extends Activity {
    TextView nomJoueur1;
    TextView nomJoueur2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ia_game_display);

        nomJoueur1 = (TextView) findViewById(R.id.nomJoueur1);
        nomJoueur2 = (TextView) findViewById(R.id.nomJoueur2);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

			/* imageView.setLayoutParams(new GridView.LayoutParams(dpToPixel(150),dpToPixel(150)));

				private float dpToPixel(int dp)	{
						Resources resources = this.getResources();
						DisplayMetrics metrics = resources.getDisplayMetrics();
						float px = dp * (metrics.densityDpi / 160f);
						return px;
				}	*/
    }
}
