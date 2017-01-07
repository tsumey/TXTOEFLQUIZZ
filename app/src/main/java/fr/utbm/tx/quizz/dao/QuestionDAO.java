package fr.utbm.tx.quizz.dao;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.tx.quizz.JouerActivity;
import fr.utbm.tx.quizz.javabean.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class QuestionDAO extends BaseDAO{
	
	public static final String TABLE_NAME = "question";
	public static final String KEY = "question_id";
	public static final String THEME = "theme_id";
	public static final String LIBELLE = "question_lib";
	
	public QuestionDAO(Context pContext) {
		super(pContext);
	}
	
	public List<Question> chooseQuestion(){
		// Liste n questions aleatoires
		List<Question> questions = new ArrayList<Question>();
		
		Cursor cursor = mDb.rawQuery("Select * from "+TABLE_NAME+" order by RANDOM() LIMIT "+JouerActivity.NB_QUESTION,null);
		while (cursor.moveToNext()) {
			questions.add(new Question(cursor.getInt(0),cursor.getString(2),cursor.getInt(1)));
		}
		cursor.close();
		Log.i("DAO", "chooseQuestions done :" + questions);	
		return questions;
	}
	
	public List<Question> chooseQuestionFromTheme(int theme_id) {
		List<Question> questions = new ArrayList<Question>();
		
		Cursor cursor = mDb.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+THEME+"="+theme_id+" order by RANDOM() LIMIT "+JouerActivity.NB_QUESTION,null);
		while (cursor.moveToNext()) {
			questions.add(new Question(cursor.getInt(0),cursor.getString(2),cursor.getInt(1)));
		}
		cursor.close();
		Log.i("DAO", "chooseQuestionsByTheme done :" + questions);	
		return questions;
	}
	
	public void insert(Question q){
		Log.i("DAO", "insert done");	
		ContentValues values=new ContentValues();
		values.put(LIBELLE, q.getQuestion_lib());
		mDb.insert(TABLE_NAME, null, values);
	}
	
	public void delete(long id) {
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}
	
	public void update(Question q){
		ContentValues value = new ContentValues();
		value.put(LIBELLE, q.getQuestion_lib());
		value.put(LIBELLE, q.getTheme_id());
		mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(q.getQuestion_id())});
	}
}
