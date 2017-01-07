package fr.utbm.tx.quizz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;

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

public class CoursActivity extends Activity implements View.OnClickListener {
    private Button cours1=null;
    private Button cours2=null;
    private Button cours3=null;
    private Button cours4=null;
    private List<String> nameCourse = new ArrayList<String>();
    private List<String> listId = new ArrayList<String>();
    private int numBtn =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.course_choice);
        cours1 = (Button) findViewById(R.id.cours1);
        cours2 = (Button) findViewById(R.id.cours2);
        cours3 = (Button) findViewById(R.id.cours3);
        cours4 = (Button) findViewById(R.id.cours4);

        cours1.setOnClickListener(this);
        cours2.setOnClickListener(this);
        cours3.setOnClickListener(this);
        cours4.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.cours1):
                numBtn = 0;
                break;
            case (R.id.cours2):
                numBtn = 1;
                break;
            case (R.id.cours3):
                numBtn = 2;
                break;
            case (R.id.cours4):
                numBtn = 3;
                break;
            }
        }
        new JSONCourse().execute("http://to52.julienpetit.fr/api/v1/learning/categories");
    }

    public class JSONCourse extends AsyncTask<String,String,List<String> > {
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
                String line = "" ;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                buffer.append("}");
                String finaljson = buffer.toString();
                JSONObject parentObject = new JSONObject(finaljson);
                JSONArray parentArray = parentObject.getJSONArray("JSON");
                JSONObject coursename = parentArray.getJSONObject(numBtn);
                try {
                    JSONArray children = coursename.getJSONArray("children");
                    for (int i = 0; i < children.length(); i++) {
                        JSONObject finalObject = children.getJSONObject(i);
                        String name = finalObject.getString("name");
                        String id = finalObject.getString("id");
                        // adding the final object in the list
                        nameCourse.add(name);
                        listId.add(id);
                    }
                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage() + " - " + e.getStackTrace()[0].getClassName());
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

        protected void onPostExecute(List<String> result){
            super.onPostExecute(result);

            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popupcourse, null);
            Button gocours = (Button) popupView.findViewById(R.id.gocours);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CoursActivity.this, android.R.layout.simple_spinner_item, nameCourse);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final Spinner popupSpinner = (Spinner) popupView.findViewById(R.id.Spinner);
            popupSpinner.setAdapter(adapter);
            nameCourse = new ArrayList<String>();

            final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            gocours.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getBaseContext(), LectureActivity.class);
                    myIntent.putExtra("Lecture",listId.get(popupSpinner.getSelectedItemPosition()));
                    startActivity(myIntent);
                }
            });
            popupWindow.showAsDropDown(cours1, 30, 0);
        }
    }
}
