package fr.utbm.tx.quizz;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.tx.quizz.R;

public class
        LectureActivity extends Activity
{
    private Button btn_select = null;
    private String idLecture = null;
    private Spinner spinTitle = null;
    private TextView text = null;

    private List<String> listTitle = new ArrayList<String>();
    private List<String> listContent = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lecture_activity);
        btn_select = (Button) findViewById(R.id.cours1);
        spinTitle = (Spinner) findViewById(R.id.courseSpinner);
        text = (TextView) findViewById(R.id.information);

        Bundle extras = getIntent().getExtras();
        idLecture = extras.getString("Lecture");
        String http = "http://to52.julienpetit.fr/api/v1/learning/cards/category/" + idLecture;
        new JSONLecture().execute(http);
    }

    public class JSONLecture extends AsyncTask<String,String,List<String> > {
        @Override
        protected List<String> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                buffer.append("{\"JSON\":");
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                buffer.append("}");
                String finaljson = buffer.toString();
                JSONObject parentObject = new JSONObject(finaljson);
                JSONArray parentArray = parentObject.getJSONArray("JSON");
                try {
                    for (int i = 0; i < parentArray.length(); i++) {
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        String title = finalObject.getString("title");
                        String content = finalObject.getString("content");
                        // adding the final object in the list
                        listTitle.add(title);
                        listContent.add(content);
                    }
                } catch (Exception e) {
                    Log.d("CREATION", e.getMessage() + " - " + e.getStackTrace()[0].getClassName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LectureActivity.this, android.R.layout.simple_spinner_item, listTitle);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinTitle.setAdapter(adapter1);
            btn_select = (Button) findViewById(R.id.activation);
                spinTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected (AdapterView < ? > parent, View v, int pos, long id){
                        text.setText(listContent.get(spinTitle.getSelectedItemPosition()));
                    }

                    @Override
                    public void onNothingSelected (AdapterView < ? > arg0){
                    }
                });
            }
    }
}
