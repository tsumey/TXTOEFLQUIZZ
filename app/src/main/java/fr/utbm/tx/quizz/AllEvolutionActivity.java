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
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import fr.utbm.tx.quizz.dao.QuestionDAO;
import fr.utbm.tx.quizz.dao.ThemeDAO;
import fr.utbm.tx.quizz.javabean.Question;
import fr.utbm.tx.quizz.javabean.Score;
import fr.utbm.tx.quizz.javabean.Theme;

public class AllEvolutionActivity extends Activity{
	
	private List<Theme> lstThemes = new ArrayList<Theme>();
	private ThemeDAO tDAO = new ThemeDAO(this);
	private QuestionDAO qDao = new QuestionDAO(this);
	private String tabColor[] = {"#FB8C00", "#BA68C8", "#F44336", "#3F51B5", "#1B5E20", "#FFFF00"
			, "#607D8B"};
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_score);
		
		//build chart
		openChart();
	}
	
	private XYSeries loadMySYSeries(XYSeries xy,List<Score> lst,int play_theme){
		xy.add(0, 0);
		if(lst != null){
			int j = 1;
			int nbQuestion;
			int val;
			String annotation = " ";
			List <Question> questions = new ArrayList <Question>();
			qDao.open();
			if(play_theme == 0){
				nbQuestion = 15;	
			}
			else{
				questions=qDao.chooseQuestionFromTheme(play_theme);
				nbQuestion = questions.size();   // size of questions
			}
			for(Score s : lst){
				xy.add(j, s.getVal());  // add x & y on chart
				val = s.getVal()*100/nbQuestion; // value in percent
				annotation = val + "%";  // text annotation
				xy.addAnnotation(annotation, j, s.getVal());
				j++;
			}
		}
		return xy;
	}
	
	private int maxValue(List<Integer> l){
		int maxVal = 0;
		maxVal = l.get(0);
		for(Integer i: l) {
		    if(i > maxVal) maxVal = i;
		}	
		return maxVal;
	}
	
	private void openChart(){
		// title theme
		tDAO.open();
		lstThemes = tDAO.getThemes();
		
		String theme0 = "Partie Classique";
    	// Creating an  XYSeries for score classic
    	XYSeries classiqueSeries = new XYSeries(theme0);
    	// init list of score
    	List<Score> lst0 = ReadScore(0);
    	//size of list score
    	List<Integer> listTaille = new ArrayList<Integer>();
    	int t0 = 0; 
    	int mSize = 0;
    	if(lst0 != null){
    		listTaille.add(lst0.size());
    	}else{
    		listTaille.add(0);
    	}
    	// set data from file
    	classiqueSeries = loadMySYSeries(classiqueSeries,lst0,0);   	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Series to the dataset (Classique firstly)
    	dataset.addSeries(classiqueSeries);   	
    	// Creating XYSeriesRenderer to customize Classique Series
    	XYSeriesRenderer classiqueRenderer = new XYSeriesRenderer();
    	classiqueRenderer.setColor(Color.parseColor(tabColor[0]));
    	classiqueRenderer.setPointStyle(PointStyle.CIRCLE);
    	classiqueRenderer.setFillPoints(true);
    	classiqueRenderer.setLineWidth(2);
    	//classiqueRenderer.setDisplayChartValues(true);
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
    	
    	// adding element to chart
    	multiRenderer.addSeriesRenderer(classiqueRenderer);
    	
    	/* ----------------------------------------------------
    	 *  DYNAMIQUE BUILDING CHART (theme 1 to theme N)
    	 * ----------------------------------------------------*/
    	// indice
    	int k = 1; 
		for(Theme t : lstThemes){
			// new XYSeries theme
			XYSeries s = new XYSeries(t.getTheme_lib());
			// new score for theme
			List<Score> l = ReadScore(t.getTheme_id());
			// add size score element
			 if(l != null){
				 listTaille.add(l.size()); 
			 }
			 else{
				 listTaille.add(0); 
			 }
			//set data from file
			 s = loadMySYSeries(s,l,t.getTheme_id());
			// Adding Series to the dataset (thème1, ...., thème5)
		    dataset.addSeries(s); 
		    // Creating XYSeriesRenderer to customize Classique Series
	    	XYSeriesRenderer themeRenderer = new XYSeriesRenderer();
	    	themeRenderer.setColor(Color.parseColor(tabColor[k]));
	    	themeRenderer.setPointStyle(PointStyle.CIRCLE);
	    	themeRenderer.setFillPoints(true);
	    	themeRenderer.setLineWidth(2);
	    	//themeRenderer.setDisplayChartValues(true);
	    	// adding element to chart
	    	multiRenderer.addSeriesRenderer(themeRenderer);
	    	k++;
	    	if(tabColor.length < k){
	    		k = 0;
	    	}
		}
		// graduation on x
    	mSize = maxValue(listTaille);
    	for(int i=0;i<mSize;i++){  //maxVal
    		multiRenderer.addXTextLabel(i+1, (i+1)+"e");    		
    	}
    	// graduation on y
    	for(int i=0;i<16;i++){
    		multiRenderer.addYTextLabel(i+1, (i+1)+"");    		
    	}
    	
    	// get layout
    	LinearLayout layout = (LinearLayout) findViewById(R.id.chartAll);
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
		return "Evolution du "+startEvolution+" au "+endEvolution;
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
