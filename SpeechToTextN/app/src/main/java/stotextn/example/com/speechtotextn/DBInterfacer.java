/**
 * Created by ADMIN on 10/09/2017.
 */
package stotextn.example.com.speechtotextn;

import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;


public class DBInterfacer {
    SQLiteDatabase edb;

    MainActivity maa;

    DBInterfacer(MainActivity ma)
    {

        maa = ma;
        edb=ma.openOrCreateDatabase("LeaveDB", Context.MODE_PRIVATE, null);
        edb.execSQL("CREATE TABLE IF NOT EXISTS questions(que VARCHAR,ans VARCHAR);");

        edb.execSQL("CREATE TABLE IF NOT EXISTS expense(expenses VARCHAR,total VARCHAR);");

        edb.execSQL("INSERT INTO questions VALUES('how are you today','I am doing good. Thanks for asking. How are you');");

        edb.execSQL("INSERT INTO questions VALUES('how old are you','I am 2months old');");

        edb.execSQL("INSERT INTO questions VALUES('do you ever get tired','not at all, I can help you at any time');");

        edb.execSQL("INSERT INTO questions VALUES('who was your first crush','of course it’s you');");

        edb.execSQL("INSERT INTO questions VALUES('do you like Siri','of course I do like it');");

        Cursor c=edb.rawQuery("SELECT * FROM expense WHERE expenses='total'", null);
        if(c.moveToFirst())
        {
            //String nl=c.getString(1);
        }
        else
        {
            edb.execSQL("INSERT INTO expense VALUES('total','0');");

        }
    }

    void resetExpense()
    {
        edb.execSQL("UPDATE expense SET total='0'"+
                " WHERE expenses='total'");
    }

    void addExpense(String exp)
    {

        Cursor c=edb.rawQuery("SELECT * FROM expense WHERE expenses='total'", null);
        if(c.moveToFirst())
        {
            String nl=c.getString(1);

            int val = Integer.parseInt(nl);
            int iexp = Integer.parseInt(exp);
            val = val + iexp;

            edb.execSQL("UPDATE expense SET total='"+val+"'"+
                    " WHERE expenses='total'");

            maa.speakOut("Added");


        }

    }
    public String getExpense()
    {
        String res="0";
        Cursor c=edb.rawQuery("SELECT * FROM expense WHERE expenses='total'", null);
        if(c.moveToFirst())
        {
            String nl=c.getString(1);
            return nl;
        }
        return res;

    }

}
