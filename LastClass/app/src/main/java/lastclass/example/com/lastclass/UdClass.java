package lastclass.example.com.lastclass;

/**
 * Created http://androidexample.com/SQLite_Database_Manipulation_Class_-_Android_Example/index.php?view=article_discription&aid=51
 */

public class UdClass {

    //private variables
    int id;
    String name;
    String email;

    // Empty constructor
    public UdClass(){

    }


    // constructor
    public UdClass(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // constructor
    public UdClass(String name, String email){
        this.name = name;
        this.email = email;
    }


    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting email
    public String getEmail(){
        return this.email;
    }

    // setting email
    public void setEmail(String email){
        this.email = email;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserInfo [name=" + name + ", email=" + email + "]";
    }
}
