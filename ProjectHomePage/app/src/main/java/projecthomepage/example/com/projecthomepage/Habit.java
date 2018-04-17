package projecthomepage.example.com.projecthomepage;

/**
 * Created by ADMIN on 7/5/2017.
 */

public class Habit {

    static final int SIZE=21;

    private int id;
    private String title;
    private String date;
    private String time;
    private char[] achievement;

    public Habit()
    {
        title="";
        date="";
        time="";
        achievement= new char[SIZE]; // T means the task has been done on (i+1)th day; F means the task is failed.
    }
    public Habit(String mtitle,String mdate, String mtime)
    {
        title=mtitle;
        date=mdate;
        time= mtime;
        achievement= new char[SIZE];
        for(int i =0; i<SIZE;i++)
        {
            achievement[i]='N';
        }
    }
    public void setTaskStatus(int id, boolean flag)
    {
        if ( id<=21 && id >=1){
            if(flag == true)
                achievement[id-1]='T';
            else
                achievement[id-1]='F';
        }

    }
    //set and get methods
    public String getTitle()
    {
        return title;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    public String getStatus()
    {
        return String.valueOf(achievement);
    }
    public int getID()
    {
        return id;
    }

    public void setID(int mid)
    {
        id=mid;
    }
    public void setTitle(String t)
    {
        title=t;
    }
    public void setDate(String dat)
    {
        date=dat;
    }
    public void setTime(String t)
    {
        time=t;
    }
    public void setStatus(String stat)
    {
        achievement=stat.toCharArray();
    }
}

