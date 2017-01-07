package fr.utbm.tx.quizz.dao;


import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper{
	
	private Context Context;
	//name of file which contains the SQL script
	private String Filename;
	
	public DataBaseHandler(Context context, String name, String filename,CursorFactory factory,int version) {
		super(context, name, factory, version);
		Log.i("BDD","onConstrutor");
		Context=context;
		Filename=filename;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("BDD","onCreate");
		
		// open transaction
		db.beginTransaction();
		 
       try {
             InputStream is = Context.getResources().getAssets().open(Filename); // open the script file
             Log.i("BDD", "Récupération de fichier OK");  
             
             String[] statements = FileHelper.parseSqlFile(is); //split the sql query
             Log.i("BDD", "Parse done");  
             for (String statement : statements) {
            	 db.execSQL(statement);   //execute query
             }
             db.setTransactionSuccessful();
             Log.i("BDD", "Cr�ation r�ussie");
              
       } catch (Exception ex) {
    	   Log.i("BDD", "Création ou connexion fichier échouée");
       }
       finally {
    	   //close transaction
    	   db.endTransaction();
	   }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("BDD","onUpdate");
		
	}
	
}
