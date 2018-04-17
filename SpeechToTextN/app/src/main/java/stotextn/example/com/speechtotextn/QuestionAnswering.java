package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */


/**
 * Created by ADMIN on 12/8/2017.
 */

public class QuestionAnswering {

    void handleText(String text,MainActivity ma)
    {
        String searchQuery = text;
        searchQuery = searchQuery.replace("question","");
        searchQuery=searchQuery.replace(" ", "+");

        URLRedirector urlr = new URLRedirector();
        String ur="http://start.csail.mit.edu/answer.php?query="+searchQuery;
        //http://start.csail.mit.edu/answer.php?query=
        urlr.openURL(ur,ma);

    }

}
