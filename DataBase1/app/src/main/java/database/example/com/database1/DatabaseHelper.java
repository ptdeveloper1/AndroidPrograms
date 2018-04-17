package database.example.com.database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ADMIN on 6/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name="First_table.db";
    private static final String Table_Name="First_table";
    public  static final String Col1="ID";
    public  static final String Col2="Name";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
       // SQLiteDatabase db=this.getWritableDatabase();//create a db and table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable="CREATE TABLE " + Table_Name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + Col2 +" TEXT";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
    public boolean insertData(String name){
        SQLiteDatabase db=this.getWritableDatabase();//create a db and table
        ContentValues cv=new ContentValues();
        cv.put(Col2,name);
        long result =db.insert(Table_Name,null,cv);
        if(result==-1)
            return false;
        else
            return true;



    }
}
