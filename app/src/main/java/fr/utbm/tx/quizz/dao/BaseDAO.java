package fr.utbm.tx.quizz.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDAO {
	
	  // to update the database, you need to change this version number
	  protected final static int VERSION = 1;
	  // name of the file which represents the database
	  protected final static String NOM = "quizzDB.db";
	    
	  protected SQLiteDatabase mDb = null;
	  protected DataBaseHandler mHandler = null;
	    
	  public BaseDAO(Context pContext) {
	    this.mHandler = new DataBaseHandler(pContext, NOM,"db.sql", null, VERSION);
	  }
	  
	  /*
	   * open the database and return it
	   */
	  public SQLiteDatabase open() {
	    mDb = mHandler.getWritableDatabase();
	    return mDb;
	  }
	    
	  public void close() {
	    mDb.close();
	  }
	    
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
		
}
