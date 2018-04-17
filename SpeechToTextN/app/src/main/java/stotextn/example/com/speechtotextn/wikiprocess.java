package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */

    import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.StringWriter;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import android.os.AsyncTask;
        import android.util.Log;
        import org.apache.commons.io.IOUtils;
        import org.w3c.dom.Document;

/**
 * Created by ADMIN on 12/8/2017.
 */

public class wikiprocess {
    MainActivity ma;
    wikiprocess(MainActivity m)
    {
        ma=m;
    }
    void processWiki(String cont)
    {
        // String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + cont;
        String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&exsentences=2&explaintext=&titles=" + cont;
        new HttpAsyncTask().execute(url);
    }
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if(inputStream != null)
            {
                result = convertInputStreamToString(inputStream);
                int pos = result.indexOf("extract");
                if (pos!=-1)
                {
                    pos=pos+10;
                    int endpos = result.indexOf("}}",pos);
                    result=result.substring(pos, endpos-2);
                }
                else
                {
                    result="failed";
                }
            }
            else
                result = "failed";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "" ;
        String result = "" ;
        while((line = bufferedReader.readLine()) != null )
            result += line;
        inputStream.close();
        /*StringWriter line = new StringWriter();
        IOUtils.copy(inputStream,line,"UTF-8");
        String result = line.toString() ;*/
        //result=result.substring(0, result.indexOf(".")+1);
        //result= String.valueOf(result.split("'s/\\(^[^.]*\\.[^.]*\\.\\)\\(.*$\\)/\1/g' "));
        // int count=0;


        return result;


    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            ma.txtSpeechInput.setText(result);
            ma.speakOut(result);
        }
    }

}
