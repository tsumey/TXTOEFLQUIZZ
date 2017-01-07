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

public class ChoiceScoreActivity extends Activity{
   private Button tousBtn = null;
   private Button classiqueBtn = null;
   private Button themeBtn = null;
   private Button retourBtn = null;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_choice_score);

       // fix their element
       tousBtn = (Button) findViewById(R.id.btn_tous);
       classiqueBtn = (Button) findViewById(R.id.btn_classique_m);
       themeBtn = (Button) findViewById(R.id.btn_partheme);
       retourBtn = (Button) findViewById(R.id.btn_retour);

       // go to all score
       tousBtn.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               //Initialisation de la prochaine activité  //ExempleChartActivity
               Intent myIntent = new Intent(getBaseContext(), AllEvolutionActivity.class);
               startActivity(myIntent);
           }
       });

       // go to classique score
       classiqueBtn.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               //Initialisation de la prochaine activité
               Intent myIntent = new Intent(getBaseContext(), ClassicEvolutionActivity.class);
               startActivity(myIntent);
               //finish();
           }
       });

       // go to score by theme
       themeBtn.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               //Initialisation de la prochaine activité
               Intent myIntent = new Intent(getBaseContext(), ThemeEvolutionActivity.class);

               // Select a random theme for the activity
               Random rand = new Random();
               int nbTheme = 5;
               int nb = rand.nextInt(nbTheme) + 1;

               int theme_id = nb; // Play theme number nb
               myIntent.putExtra("play_theme", theme_id);

               // Start activity
               startActivity(myIntent);
           }
       });

       //Back to menu
       retourBtn.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               //Initialisation de la prochaine activité
               Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
               startActivity(myIntent);
               ;
               finish();
           }
       });

   }
}
