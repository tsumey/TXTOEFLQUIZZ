package fr.utbm.tx.quizz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.utbm.tx.quizz.javabean.Score;

public class ClassicEvolutionActivity extends Activity{
	
	private Button retourBtn = null;
	private TextView textView = null;
	private TextView titleEvolution = null;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classic_score);
		
		// titre de l'évolution
		titleEvolution = (TextView) findViewById(R.id.titleEvolution);
		titleEvolution.setText(titleEvolution(0));
		
		//build chart
		openChart();
	}
	
	private void openChart(){
    	// Creating an  XYSeries for score classic
    	XYSeries classiqueSeries = new XYSeries("Partie Classique");
    	
    	List<Score> lst = ReadScore(0);
    	int t0;
		if(lst != null){
			t0 = lst.size();
			int nbQuestion = 15;
			int val;
			String annotation = " ";
			int j = 1;
			for(Score s : lst){
				classiqueSeries.add(j, s.getVal());
				val = s.getVal()*100/nbQuestion;
				annotation = val + "%";  // text annotation
				classiqueSeries.addAnnotation(annotation, j, s.getVal());
				j++;
			}
		}
		else{
			t0  = 0;
			classiqueSeries.add(0, 0);
		}
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Classique Series to the dataset
    	dataset.addSeries(classiqueSeries); 
    	
    	// Creating XYSeriesRenderer to customize Classique Series
    	XYSeriesRenderer classiqueRenderer = new XYSeriesRenderer();
    	classiqueRenderer.setColor(Color.GREEN);
    	classiqueRenderer.setPointStyle(PointStyle.CIRCLE);
    	classiqueRenderer.setFillPoints(true);
    	classiqueRenderer.setLineWidth(2);
    	//classiqueRenderer.setDisplayChartValues(true);
    	
    	// duplicate 
    	
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
    	for(int i=0;i<t0;i++){
    		multiRenderer.addXTextLabel(i+1, (i+1)+"e");    		
    	}
    	// graduation sur les y
    	for(int i=0;i<16;i++){
    		multiRenderer.addYTextLabel(i+1, (i+1)+"");    		
    	}
    	
    	// adding element to chart
    	multiRenderer.addSeriesRenderer(classiqueRenderer);
    	
    	// get layout
    	LinearLayout layout = (LinearLayout) findViewById(R.id.chartClassique);
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
