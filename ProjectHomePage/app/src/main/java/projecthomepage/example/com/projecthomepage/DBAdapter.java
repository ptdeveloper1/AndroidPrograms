package projecthomepage.example.com.projecthomepage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 7/5/2017.
 */

class DBAdapter {

    /******************** if debug is set true then it will show all Logcat message ************/
    public static final boolean DEBUG = true;

    /******************** Logcat TAG ************/
    public static final String LOG_TAG = "DBAdapter";

    /******************** Table Fields ************/
    public static final String KEY_ID = "_id";

    public static final String KEY_USER_NAME = "user_name";

    public static final String KEY_USER_EMAIL = "user_email";


    /******************** Database Name ************/
    public static final String DATABASE_NAME = "DB_sqllite";

    /******************** Database Version (Increase one if want to also upgrade your database) ************/
    public static final int DATABASE_VERSION = 1;// started at 1

    /** Table names */
    public static final String USER_TABLE = "tbl_user1";

    /**New Table*/
    public static final String HABIT_TABLE = "habit";

    /**Fields in the new table*/
    public static final String KEY_TITLE = "title";

    public static final String KEY_DATE = "date";

    public static final String KEY_TIME = "time";

    public static final String KEY_STATUS = "status";

    /** Create table syntax */
    private static final String HABIT_CREATE =
            "create table "+HABIT_TABLE+"("+
                    KEY_ID+ " integer primary key autoincrement, "+
                    KEY_TITLE+ " text not null, "+
                    KEY_DATE+" text not null, "+
                    KEY_TIME+" text not null, "+
                    KEY_STATUS+" text not null);";
    /******************** Set all table with comma seperated like USER_TABLE,ABC_TABLE ************/
    private static final String[] ALL_TABLES = { USER_TABLE, HABIT_TABLE};

    /** Create table syntax */
    private static final String USER_CREATE =
            "create table "+USER_TABLE+"("+
                    KEY_ID+ " integer primary key autoincrement, "+
                    KEY_USER_NAME+" text not null, "+
                    KEY_USER_EMAIL+" text not null);";

    /******************** Used to open database in syncronized way ************/
    private static DataBaseHelper DBHelper = null;

    protected DBAdapter() {
    }
    /******************* Initialize database *************/
    public static void init(Context context) {
        if (DBHelper == null) {
            if (DEBUG)
                Log.d(LOG_TAG, context.toString());
            DBHelper = new DataBaseHelper(context);
        }
    }

    /********************** Main Database creation INNER class ********************/
    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DEBUG)
                Log.d(LOG_TAG, "new create");
            try {
                db.execSQL(USER_CREATE);
                db.execSQL(HABIT_CREATE);


            } catch (Exception exception) {
                if (DEBUG)
                    Log.d(LOG_TAG, "Exception onCreate() exception");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (DEBUG)
                Log.d(LOG_TAG, "Upgrading database from version" + oldVersion
                        + "to" + newVersion + "...");

            for (String table : ALL_TABLES) {
                db.execSQL("DROP TABLE IF EXISTS " + table);
            }
            onCreate(db);
        }

    } // Inner class closed


    /********************** Open database for insert,update,delete in syncronized manner ********************/
    private static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }


    /************************ General functions**************************/


    /*********************** Escape string for single quotes (Insert,Update)************/
    private static String sqlEscapeString(String aString) {
        String aReturn = "";

        if (null != aString) {
            //aReturn = aString.replace("'", "''");
            aReturn = DatabaseUtils.sqlEscapeString(aString);
            // Remove the enclosing single quotes ...
            aReturn = aReturn.substring(1, aReturn.length() - 1);
        }

        return aReturn;
    }
    /*********************** UnEscape string for single quotes (show data)************/
    private static String sqlUnEscapeString(String aString) {

        String aReturn = "";

        if (null != aString) {
            aReturn = aString.replace("''", "'");
        }

        return aReturn;
    }


    /********************************************************************/


    /**
     * All Operations (Create, Read, Update, Delete)
     */
    // Adding new contact

    public static void addUserData(UserData uData) {
        final SQLiteDatabase db = open();

        String name = sqlEscapeString(uData.getName());
        String email = sqlEscapeString(uData.getEmail());
        ContentValues cVal = new ContentValues();
        cVal.put(KEY_USER_NAME, name);
        cVal.put(KEY_USER_EMAIL, email);
        db.insert(USER_TABLE, null, cVal);
        db.close(); // Closing database connection
    }

    public static void addHabitData(Habit data) {
        final SQLiteDatabase db = open();

        String title = sqlEscapeString(data.getTitle());
        String date = sqlEscapeString(data.getDate());
        String time =sqlEscapeString(data.getTime());
        String status=sqlEscapeString(data.getStatus());

        ContentValues cVal = new ContentValues();
        cVal.put(KEY_TITLE, title);
        cVal.put(KEY_DATE, date);
        cVal.put(KEY_TIME, time);
        cVal.put(KEY_STATUS, status);
        db.insert(HABIT_TABLE, null, cVal);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public static UserData getUserData(int id) {
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_ID,
                        KEY_USER_NAME, KEY_USER_EMAIL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserData data = new UserData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return data;
    }

    // Getting All Contacts
    public static List<UserData> getAllUserData() {
        List<UserData> contactList = new ArrayList<UserData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE;

        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData data = new UserData();
                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setEmail(cursor.getString(2));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    // Getting All Contacts
    public static List<Habit> getAllHabits() {
        List<Habit> contactList = new ArrayList<Habit>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + HABIT_TABLE;

        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Habit data = new Habit();
                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setTitle(cursor.getString(1));
                data.setDate(cursor.getString(2));
                data.setTime(cursor.getString(3));
                data.setStatus(cursor.getString(4));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public static int updateUserData(UserData data) {
        final SQLiteDatabase db = open();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, data.getName());
        values.put(KEY_USER_EMAIL, data.getEmail());

        // updating row
        return db.update(USER_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
    }

    // Updating single contact
    public static int updateHabitData(Habit data) {
        final SQLiteDatabase db = open();

        ContentValues cVal = new ContentValues();
        String title = sqlEscapeString(data.getTitle());
        String date = sqlEscapeString(data.getDate());
        String time =sqlEscapeString(data.getTime());
        String status=sqlEscapeString(data.getStatus());

        cVal.put(KEY_TITLE, title);
        cVal.put(KEY_DATE, date);
        cVal.put(KEY_TIME, time);
        cVal.put(KEY_STATUS, status);


        // updating row
        return db.update(HABIT_TABLE, cVal, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
    }
    // Deleting single contact
    public static void deleteUserData(UserData data) {
        final SQLiteDatabase db = open();
        db.delete(USER_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
        db.close();
    }

    // Deleting single habit
    public static void deleteHabitData(Habit data) {
        final SQLiteDatabase db = open();
        db.delete(HABIT_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
        db.close();
    }
    // Getting contacts Count
    public static int getUserDataCount() {
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting contacts Count
    public static int getHabitDataCount() {
        String countQuery = "SELECT  * FROM " + HABIT_TABLE;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}