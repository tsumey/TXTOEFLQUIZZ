package fr.utbm.tx.quizz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import fr.utbm.tx.quizz.dao.QuestionDAO;
import fr.utbm.tx.quizz.dao.ThemeDAO;
import fr.utbm.tx.quizz.javabean.Question;
import fr.utbm.tx.quizz.javabean.Score;
import fr.utbm.tx.quizz.javabean.Theme;

public class ThemeEvolutionActivity extends Activity{

	private TextView titleEvolution = null;
	private Spinner spinnerSelect = null;
	private List<Theme> lstThemes = new ArrayList<Theme>();
	private ThemeDAO tDAO = new ThemeDAO(this);
	private QuestionDAO qDao = new QuestionDAO(this);
	private int currentItem;
	private String tabColor[] = {"#FB8C00", "#BA68C8", "#F44336", "#3F51B5", "#1B5E20", "#FFFF00"
			, "#607D8B"};
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme_score);
		
		// get param
		Bundle b = getIntent().getExtras();
		int theme_id = b.getInt("play_theme");
		
		// titre de l'évolution
		titleEvolution = (TextView) findViewById(R.id.titleEvolution);
		titleEvolution.setText(titleEvolution(theme_id)); //0
		
		//select theme
		spinnerSelect = (Spinner) findViewById(R.id.spinner_lstTheme);
		// add list item theme
		addItemOnSpinner(theme_id-1);
		// set default item
		currentItem = spinnerSelect.getSelectedItemPosition();
		
		//event
		spinnerSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	int element = (int)id + 1;
		    	String theme = parentView.getItemAtPosition(position).toString();
		    	if(currentItem != position){
		    		//Initialisation de la prochaine activité
			    	Intent myIntent = new Intent(getBaseContext(), ThemeEvolutionActivity.class);
		       		myIntent.putExtra("play_theme", element); 
		       		// Start activity
		       		startActivity(myIntent);
		       		finish();
		       		Toast.makeText(parentView.getContext(), 
			    			"Evolution : " + theme,
			    			Toast.LENGTH_SHORT).show();
	       		}	
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // ----- herve
		    }
		});
				
		//build chart
		openChart(theme_id);
	}
	
	private void addItemOnSpinner(int defaultElement){
		// theme
		tDAO.open();
		lstThemes = tDAO.getThemes();
		// new list
		List<String> list = new ArrayList<String>();
		// load theme
		for(Theme t : lstThemes){
			list.add(t.getTheme_lib());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSelect.setAdapter(dataAdapter);
		//default
		spinnerSelect.setSelection(defaultElement);
	}
	
	private XYSeries loadMySYSeries(XYSeries xy,List<Score> lst, int play_theme){
		xy.add(0, 0);
		if(lst != null){
			int j = 1;
			int nbQuestion;
			int val;
			String annotation = " ";
			List <Question> questions = new ArrayList <Question>();
			qDao.open();
			questions=qDao.chooseQuestionFromTheme(play_theme);
			for(Score s : lst){
				xy.add(j, s.getVal());  // add x & y on chart
				nbQuestion = questions.size();   // size of questions
				val = s.getVal()*100/nbQuestion; // value in percent
				annotation = val + "%";  // text annotation
				xy.addAnnotation(annotation, j, s.getVal());
				j++;
			}
		}
		return xy;
	}
	
	private void openChart(int theme_id){
		//title theme
		tDAO.open();
		lstThemes = tDAO.getThemes();
		String themeX = lstThemes.get(theme_id-1).getTheme_lib();
		// Creating an  XYSeries for score classic
    	XYSeries themeSeries = new XYSeries(themeX);
    	//init list score
    	List<Score> lst = ReadScore(theme_id);
    	//size of list score
    	int t = 0;
    	if(lst != null)
    		t = lst.size();
    	// set data from file
    	themeSeries = loadMySYSeries(themeSeries,lst,theme_id);
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Classique Series to the dataset
    	dataset.addSeries(themeSeries); 
    	
    	// Creating XYSeriesRenderer to customize Classique Series
    	XYSeriesRenderer themeRenderer = new XYSeriesRenderer();
    	themeRenderer.setColor(Color.parseColor(tabColor[theme_id-1])); //Color.GREEN
    	themeRenderer.setPointStyle(PointStyle.CIRCLE);
    	themeRenderer.setFillPoints(true);
    	themeRenderer.setLineWidth(2);
    	//themeRenderer.setDisplayChartValues(true);
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
    	multiRenderer.setXLabels(0);
    	multiRenderer.setChartTitle(""); // title chart
    	multiRenderer.setXTitle("N° des parties");
    	multiRenderer.setYTitle("Score");
    	multiRenderer.setZoomButtonsVisible(false); 
    	multiRenderer.setYAxisMax(15);
    	multiRenderer.setYAxisMin(0);
    	multiRenderer.setLabelsTextSize(14); // size of x and y
    	multiRenderer.setLegendTextSize(24);
    	multiRenderer.setMargins(new int[] { 25, 25, 40, 25 });
    	multiRenderer.setAxisTitleTextSize(20); // size title x and y
    	
    	//multiRenderer;
    	
    	// graduation sur les x
    	for(int i=0;i<t;i++){
    		multiRenderer.addXTextLabel(i+1, (i+1)+"e");    		
    	}
    	// graduation sur les y
    	for(int i=0;i<16;i++){
    		multiRenderer.addYTextLabel(i+1, (i+1)+"");    		
    	}
    	
    	// adding element to chart
    	multiRenderer.addSeriesRenderer(themeRenderer);
    	
    	// get layout
    	LinearLayout layout = (LinearLayout) findViewById(R.id.chartTheme);
    	// init view
    	View chartView = ChartFactory.getLineChartView(getBaseContext(), dataset, multiRenderer);
    	// set view chart
    	layout.addView(chartView); 	
    }
	
	public String titleEvolution(int id){
		String startEvolution = "00-00-00";
		String endEvolution = "00-00-00";
		List<Score> lst = ReadScore(id);
		if(lst != null){
			startEvolution = lst.get(0).getDate();
			endEvolution = lst.get(lst.size()-1).getDate();
		}  
		return "Du "+startEvolution+" au "+endEvolution;
	}
	
	//read specific file score 
		public List<Score> ReadScore(int nb){
			int val;
			String date = "";
			List<Score> lst = new ArrayList<Score>();
	        try {
	            InputStream inputStream = openFileInput(nb+".dat"); 
	            if ( inputStream != null ) {
	                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	                String line = "";
	                while ( (line = bufferedReader.readLine()) != null ) {
	                    String[] v = line.split(";");
	                    val = Integer.parseInt(v[0]);
	                    date = v[1];
	                    Score score = new Score(val,date);
	                    lst.add(score);
	                }             
	                inputStream.close();
	            }
	        }
	        catch (FileNotFoundException e) {
	           Log.d("File", "File not found: " + e.toString());
	           lst = null;
	        } catch (IOException e) {
	        	Log.d("File","Can not read file: " + e.toString());
	        	lst = null;
	        }
	        return lst;
		}

	}
