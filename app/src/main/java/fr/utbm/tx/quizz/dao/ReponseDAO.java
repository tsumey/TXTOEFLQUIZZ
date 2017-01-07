package fr.utbm.tx.quizz.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.utbm.tx.quizz.javabean.Question;
import fr.utbm.tx.quizz.javabean.Reponse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ReponseDAO extends BaseDAO{
	
	public static final String TABLE_NAME = "reponse";
	public static final String Q_ID = "question_id";
	public static final String REP_ID = "rep_id";
	public static final String LIBELLE = "rep_lib";
	public static final String REP_TRUE = "rep_true";
	public static final int NB_REP = 4;
	
	public ReponseDAO(Context pContext) {
		super(pContext);
	}

	public List<Reponse> getReponse(Question q_id){
		// Récupère les réponses pour une question
		List<Reponse> reponses = new ArrayList<Reponse>();
		
		Cursor cursor = mDb.rawQuery("Select * from "+TABLE_NAME+" where "+Q_ID+"="+q_id.getQuestion_id()+" order by RANDOM()",null);
		while (cursor.moveToNext()) {
			  reponses.add(new Reponse(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
		}
		cursor.close();
		return reponses;
	}
	
	public Map<Integer, List<Reponse>> getReponse(String cond){
		// List answers per questions
		Map<Integer, List<Reponse>> map = new Hashtable<Integer, List<Reponse>>();
		// List of 4 answers
		List<Reponse> reponseTmp=new ArrayList<Reponse>();
		List<Reponse> reponse ;
		String sql="Select * from "+TABLE_NAME+" where "+Q_ID+" IN("+cond+") ORDER BY "+Q_ID+",RANDOM() ";
		int i=0;
		int previousId = 0;
		Cursor cursor = mDb.rawQuery(sql,null);
		
		// For each answer
		while (cursor.moveToNext()) {
			if(i%NB_REP != 0 || i==0){
				// Add the answer
				previousId = cursor.getInt(0);
				reponseTmp.add(new Reponse(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
			}
			else{
				// Copy of the list by value to avoid an empty map
				reponse = new ArrayList<Reponse>(reponseTmp);
				// Add the answer to the related question
				map.put(previousId, reponse);
				// Remove answers because there is an other question
				reponseTmp.clear();
				// Get the first answer
				reponseTmp.add(new Reponse(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
			}
			
			if(cursor.isLast()){
				// Copy of the list by value to avoid an empty map
				reponse = new ArrayList<Reponse>(reponseTmp);
				// Add the answer to the related question
				map.put(Integer.valueOf(cursor.getInt(0)), reponse);  
			}
			i++;
		}
		
		cursor.close();
		return map;
	}
	
	public void insert(Reponse r){
		ContentValues values=new ContentValues();
		values.put(Q_ID, r.getQuestion_id());
		values.put(REP_ID, r.getRep_id());
		values.put(LIBELLE, r.getRep_lib());
		values.put(REP_TRUE, r.getRep_true());
		mDb.insert(TABLE_NAME, null, values);
	}
}
