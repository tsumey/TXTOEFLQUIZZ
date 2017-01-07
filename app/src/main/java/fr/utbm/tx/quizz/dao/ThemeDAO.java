package fr.utbm.tx.quizz.dao;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.tx.quizz.javabean.Theme;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class ThemeDAO extends BaseDAO {
	
	public ThemeDAO(Context pContext) {
		super(pContext);
	}
	public static final String TABLE_NAME = "theme";
	public static final String KEY = "theme_id";
	public static final String LIB = "theme_lib";

	public List<Theme> getThemes() {
		
		List<Theme> themes = new ArrayList<Theme>();
		
		Cursor cursor = mDb.rawQuery("Select * from "+TABLE_NAME,null);
		while (cursor.moveToNext()) {
			themes.add(new Theme(cursor.getInt(0),cursor.getString(1)));
		}
		cursor.close();
		Log.i("DAO", "themes list done :" + themes);	
		return themes;
	}
	
	public Theme getTheme(int id) {
		
		Cursor cursor = mDb.rawQuery("Select * from "+TABLE_NAME+" When "+KEY+" = "+id,null);
		// cursor.moveToNext();
		Theme theme = new Theme(cursor.getInt(0),cursor.getString(1));
		cursor.close();
		Log.i("DAO", "theme getted:" + theme);
		return theme;
	}
	
}
