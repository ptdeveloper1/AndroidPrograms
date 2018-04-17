package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */

        import android.content.Intent;
        import android.net.Uri;

/**
 * Created by ADMIN on 12/8/2017.
 */

class URLRedirector {

    void openURL(String urlt,MainActivity ma)
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String url = urlt;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        ma.startActivity(i);

    }

}
