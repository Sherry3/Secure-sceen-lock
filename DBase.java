package com.hrds.magentalockscreen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBase {

	Context c;
    SQLiteDatabase mydb;
    private   static String DBNAME = "LockSherry.db";
    private static String TABLE = "Credentials";
	
	public DBase(Context a) {
		c=a;
		createTable();
		// TODO Auto-generated constructor stub
	}
	
	// CREATE TABLE IF NOT EXISTS
	public void createTable() {
        try {
            mydb = c.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE
                    + " (ID INTEGER PRIMARY KEY, str TEXT);");
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(c, "Error in creating table", Toast.LENGTH_LONG).show();
        }
    }
 
    // THIS FUNCTION INSERTS DATA TO THE DATABASE
    public void insertIntoTable(String str) {
        try {
            mydb = c.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("INSERT INTO " + TABLE
                    + "(str) VALUES('"+str+"')");
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(c, "Error in inserting into table", Toast.LENGTH_LONG).show();
        }
    }
 
    // THIS FUNCTION SHOWS DATA FROM THE DATABASE
    public String readFile() {
 
    	try {
            mydb = c.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            Cursor allrows = mydb.rawQuery("SELECT str FROM " + TABLE, null);
            System.out.println("COUNT : " + allrows.getCount());
 
            if (allrows.moveToFirst()) {
                do {
 
                    String name = allrows.getString(0);
                    Toast.makeText(c, name, Toast.LENGTH_LONG).show();
                    return name;
                } while (allrows.moveToNext());
            }
            allrows.close();
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(c, "Error encountered in reading file.", Toast.LENGTH_LONG).show();
        }
    	
    	return "no";
    	
    }
    
    public void setYes()
    {
    	delete();
    	insertIntoTable("yes");
    }

    public void setNo()
    {
    	delete();
    	insertIntoTable("no");
    }
    
	public void delete()
	{
		try {
            mydb = c.openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mydb.execSQL("DELETE FROM "+TABLE);
            mydb.close();
        } catch (Exception e) {
            Toast.makeText(c, "Error in inserting into table", Toast.LENGTH_LONG).show();
        }
	}
	
	
	
}
