package databaselink.example.com.databaselink;

/**
 * Created by ADMIN on 9/13/2017.
 */

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public int getId(){
        return id;
    }
    public void setId(int idnum){
        id=idnum;
    }
    public String getName(){
        return name;
    }
    public void setName(String username){
        name=username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String useremail){
        email=useremail;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String userpassword){
        password=userpassword;
    }


}
