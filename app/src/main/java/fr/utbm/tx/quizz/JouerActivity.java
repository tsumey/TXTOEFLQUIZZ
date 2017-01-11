package fr.utbm.tx.quizz;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.utbm.tx.quizz.dao.QuestionDAO;
import fr.utbm.tx.quizz.dao.ReponseDAO;
import fr.utbm.tx.quizz.dao.ThemeDAO;
import fr.utbm.tx.quizz.javabean.Question;
import fr.utbm.tx.quizz.javabean.Reponse;
import fr.utbm.tx.quizz.javabean.Score;
import fr.utbm.tx.quizz.javabean.Theme;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class JouerActivity extends Activity implements OnClickListener {

	public static final int NB_QUESTION=15;
	private int nbQuestions=0;
	
	private int num_question=1;
	private boolean answered=false;
	private boolean first=true;
	private float result;
	private float noteResult = 0;
	private int button_id;
	
	private View relativeLayout = null;
	private TextView no_question = null;
	private TextView timerText = null;
	private TextView clickText = null;
	private TextView questionView = null;	
	private TextView info = null;
	private RadioGroup radioGroup = null;
	private Button help=null;
	private ProgressBar progressBar = null;
	private Drawable image = null;
	private AlertDialog dialog= null;
	
	//private static final String filename = "score";
	private int[] scores;
	
	/*
	 * CountDownIimer of 30 sec
	 * every second we print the time remaining and set the progressBar with this time 
	 */
	private CountDownTimer timer = new CountDownTimer(30000, 1000) {
	    public void onTick(long millisUntilFinished) {
	    	progressBar.setProgress((int)millisUntilFinished/1000);
	        timerText.setText(String.valueOf(millisUntilFinished / 1000));
	    }

	    /*
	     * when time out you can't answer, and print the correct answer
	     * 
	     */
	    public void onFinish() {
	    	progressBar.setProgress(0);
	        timerText.setText("Temps écoulé! Cliquez pour continuer.");
	        RadioButton radioButton;
	        radioButton=(RadioButton) findViewById(button_id);
	        radioButton.setBackgroundResource(R.drawable.button_good_answer);
	        help.setVisibility(View.VISIBLE);
	        help.setText("+ Informations");
	        answered = true;
	    }
	};
	
	private List <Question> questions = new ArrayList <Question>();
	private Map<Integer, List<Reponse>> map = new Hashtable<Integer, List<Reponse>>();
	private List<Theme> themes = new ArrayList<Theme>();
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jouer);
		
		/**
		 * BDD
		 */
		QuestionDAO qDao = new QuestionDAO(this);
		ReponseDAO rDao = new ReponseDAO(this);
		ThemeDAO tDAO = new ThemeDAO(this); 
		
		// Themes
		tDAO.open();
		themes=tDAO.getThemes();
		tDAO.close();
		
		// Initialize score from number of theme
		scores = new int[themes.size()];
		for(int i=0; i<themes.size(); i++) scores[i] = 0;
		
		Bundle b = getIntent().getExtras();
		int play_theme = b.getInt("play_theme");
		
		/* Questions & Réponses */
		qDao.open();
		if(play_theme != 0)
			questions=qDao.chooseQuestionFromTheme(play_theme);
		else
			questions=qDao.chooseQuestion();
		qDao.close();
		nbQuestions = questions.size();
		
		String id_questions="";
		//list of question numbers separated with ',' to use in where clause in sql
		for(Question q:questions){
			id_questions+=q.getQuestion_id()+",";
		}

		// Delete the last ','
		id_questions=id_questions.substring(0, id_questions.length()-1);
		rDao.open();
		map=rDao.getReponse(id_questions);
		rDao.close();
		
		/** 
		 * Affichage
		 */
		relativeLayout = (View)findViewById(R.id.relative_layout);
		relativeLayout.setOnClickListener(this);
		radioGroup=(RadioGroup)findViewById(R.id.radioGroup1);
		questionView=(TextView)findViewById(R.id.question);
		no_question=(TextView) findViewById(R.id.no_question);
		clickText=(TextView) findViewById(R.id.click);
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
		
		dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Quizz");
		dialog.setCancelable(false);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// Go to home menu because JouerActivity closed, reactive the activity in suspend() 
		    	dialog.cancel();
				finish();
			}
		});
		
		help=(Button) findViewById(R.id.help);
		help.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				if(info.getVisibility()==View.GONE){
					info.setVisibility(View.VISIBLE);
					int w=help.getWidth();
					help.setText("↑ Informations");
					help.setWidth(w);
				}
				else{
					info.setVisibility(View.GONE);
					help.setText("↓ Informations");
				}
			}
		});

		int h = image.getIntrinsicHeight(); 
		int w = image.getIntrinsicWidth();   
		image.setBounds( 0, 0, w, h );
		
		display(questions.get(num_question-1),map.get(questions.get(num_question-1).getQuestion_id()));

		// Timer
		timerText = (TextView)findViewById(R.id.timer);
		timer.start();
	}

	@Override
	public void onClick(View v) {
		//view GONE = invisible and no place taken in the view 
		clickText.setVisibility(View.GONE);
		
		if(answered){
			//user has already answered
			
			num_question++;
			if(num_question<=nbQuestions){
				//print the next question
				display(questions.get(num_question-1),map.get(questions.get(num_question-1).getQuestion_id()));
				answered=false;
			}
			else{
				// End of the Quizz -- pop up score
				if(first) {
					noteResult =  result;
					result=result*100/nbQuestions;
					Score score = new Score(noteResult);
					// get type quizz
					Bundle b = getIntent().getExtras();
					int typ_quizz = b.getInt("play_theme");
					// Write score file
					WriteScore(score.toString(),typ_quizz);
					// Dialog
					dialog.setMessage(Html.fromHtml("Quizz terminé. Score : <b>"+Math.round(result)+"%</b>."));
					dialog.show();
					help.setVisibility(View.GONE);
					info.setVisibility(View.GONE);
					first = false;
				}
			}	
		}
		else {
			//check if the click is on a answer possibility
			if(v.getId()==R.id.radio0 || v.getId()==R.id.radio1 || v.getId()==R.id.radio2 ||v.getId()==R.id.radio3){
				RadioButton radioButton=new RadioButton(this);
				
				if(v.getId()==button_id){
					//good answer
					result++;
					int theme_id = questions.get(num_question-1).getTheme_id();
					scores[theme_id-1]++;
					clickText.setText("Bien joué! Touchez pour continuer.");
					clickText.setVisibility(View.VISIBLE);
				}
				else{
					radioButton=(RadioButton) findViewById(v.getId());
					radioButton.setBackgroundResource(R.drawable.button_bad_answer);
					clickText.setText("Faux! Touchez pour continuer.");
					clickText.setVisibility(View.VISIBLE);
				}

				radioButton=(RadioButton) findViewById(button_id);
				radioButton.setBackgroundResource(R.drawable.button_good_answer);
				
				help.setVisibility(View.VISIBLE);
				help.setText("↓ Informations");
				answered=true;
				//stop the timer
				timer.cancel();
			}
		}
	}
	
	/*
	 * print the question q and her answer possibilities
	 */
	public void display(Question q, List <Reponse> rep){		
		
		// Get corresponding theme for this question
		int theme_id = q.getTheme_id();
		String theme_lib = themes.get(theme_id-1).getTheme_lib();
		
		no_question.setText("N° "+num_question+"/"+nbQuestions+" ["+theme_lib+"]");
		questionView.setText(q.getQuestion_lib());
		help.setVisibility(View.GONE);
		RadioButton radioButton=new RadioButton(this);
		
		//get the number of radioButton -- 4 in our application
		int nb=radioGroup.getChildCount();
		
		for (int i=0;i<nb;i++){
			radioButton=(RadioButton) radioGroup.getChildAt(i);
			radioButton.setOnClickListener(this);
			
			if(i<rep.size()){
				if( rep.get(i).getRep_true()==1){
					radioButton.setChecked(true);
					button_id=radioButton.getId();
					displayAnswerInfo(rep.get(i),radioButton);
				}
				
				radioButton.setText(rep.get(i).getRep_lib());
			}
			else{
				Log.d("display","list size of answer < radioButton numbers ");
			}
			radioButton.setBackgroundResource(R.drawable.button_radio);
		}
		
		timer.start();	
	}
	
	public void displayAnswerInfo(Reponse rep, RadioButton radio){
		info = (TextView) findViewById(R.id.info);
		info.setVisibility(View.GONE);
		info.setText(rep.getRep_info());
		
		//with that, you can click on the url to go to the website
		Linkify.addLinks(info,Linkify.WEB_URLS);
		info.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	//-------------------------------------
	public void WriteScore(String data,int nb){ 
        FileOutputStream fOut = null; 
        OutputStreamWriter osw = null; 
        
        try{ 
            fOut = openFileOutput(nb+".dat",Context.MODE_APPEND);       
            osw = new OutputStreamWriter(fOut); 
            osw.write(data);
            osw.flush(); 
            Log.d("Score","Score write well");
            } 
            catch (Exception e) {       
            	Log.d("Score","Score doesn't write"); 
            } 
            finally { 
               try { 
                      osw.close(); 
                      fOut.close(); 
                      } catch (IOException e) { 
                    	  Log.d("Score","Score doesn't write");
                      } 
            } 
       }
	
	
	//------------------------------------
}

