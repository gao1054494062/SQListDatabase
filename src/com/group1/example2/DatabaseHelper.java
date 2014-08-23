package com.group1.example2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
 
    private static final String DB_NAME = "mydata.db3";
    private static final int version = 1;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }
    
    public DatabaseHelper(Context context,String dataName) {
        super(context, dataName, null, version);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
    	System.out.println("create a database");
        String sql = "create table if not exists studentInfo(" +
        		"name varchar(50), sex varchar(50)," +
        		"number varchar(50), sign varchar(50))";
        db.execSQL(sql);
    }
    public Cursor getAll(SQLiteDatabase db)
    {
    	return db.rawQuery("select * from studentInfo", null);
    }
    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	db.execSQL("DROP TABLE IF EXISTS studentInfo");
		onCreate(db);
	}
 
}