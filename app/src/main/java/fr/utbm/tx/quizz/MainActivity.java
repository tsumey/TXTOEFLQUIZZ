package fr.utbm.tx.quizz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.DialogInterface;

public class MainActivity extends Activity {
    private Button jouer = null;
    //private Button settings=null;
    private Button quitter = null;
    private Button score = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        jouer = (Button) findViewById(R.id.btn_play);
        //settings=(Button)findViewById(R.id.btn_settings);
        quitter = (Button) findViewById(R.id.btn_quit);

        score = (Button) findViewById(R.id.btn_score);

        jouer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Initialisation de la prochaine activit�
                Intent myIntent = new Intent(getBaseContext(), GameModeActivity.class);
                startActivity(myIntent);
            }
        });

        //ChoiceScoreActivity
        score.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ChoiceScoreActivity.class);
                startActivity(myIntent);
            }
        });
        
       /* settings.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		 //Initialisation de la prochaine activit�
        		 Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);    
 			     startActivity(myIntent);
        	}
        });*/

        quitter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Au revoir.", Toast.LENGTH_SHORT).show();
                // Fermeture de l'activit� courante
                finish();
            }
        });


    }

    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Voulez-vous vraiment quitter le Quizz ?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Non", null)
                .show();
    }

}