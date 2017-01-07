package fr.utbm.tx.quizz;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameModeActivity extends Activity {

	private Button classique=null;
	private Button theme=null;
	private Button retour=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_mode);
		
		// Get buttons
		classique=(Button)findViewById(R.id.btn_classique);
		theme=(Button)findViewById(R.id.btn_theme);
		retour=(Button)findViewById(R.id.btn_retour);


        AlertDialog.Builder build;
        build = new AlertDialog.Builder(GameModeActivity.this);
        build.setTitle("ATTENTION");
        build.setMessage("Si vous quittez durant le quizz, votre progression sera perdue !");
        build.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        AlertDialog alert = build.create();
        alert.show();

        // Launch "classique" mode game
		classique.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		 //Initialisation de la prochaine activit�
        		 Intent myIntent = new Intent(getBaseContext(), JouerActivity.class);


        		 // Play classique mode with all theme
        		 int theme_id = 0; // 0 - default value for all themes
        		 myIntent.putExtra("play_theme", theme_id);        		 
        		 
        		 // Start activity
        		 startActivity(myIntent);
 			     finish();


        	}
        });
		
		// Launch "theme" mode game
		theme.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		 //TODO: Initialisation de la prochaine activit�
        		 Intent myIntent = new Intent(getBaseContext(), JouerActivity.class); 
        		 
        		 // Select a random theme for the activity
        		 Random rand = new Random();
        		 int nbTheme = 5;
        		 int nb = rand.nextInt(nbTheme) + 1; // rand.nextInt(nbTheme-1) + 1;
        		 
        		 int theme_id = nb; // Play theme number nb
        		 myIntent.putExtra("play_theme", theme_id); 

        		 // Start activity
 			     startActivity(myIntent);
 			     finish();
        	}
        });
		
		// Back to menu
		retour.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		 //Initialisation de la prochaine activit�
        		 Intent myIntent = new Intent(getBaseContext(), MainActivity.class);    
 			     startActivity(myIntent);
 			     finish();
        	}
        });
	}

}
