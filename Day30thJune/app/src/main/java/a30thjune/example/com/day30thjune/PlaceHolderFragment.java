package a30thjune.example.com.day30thjune;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by ADMIN on 6/30/2017.
 */

public class PlaceHolderFragment extends Fragment {
    public PlaceHolderFragment(){

    }
   @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
       View rootview=inflater.inflate(R.layout.fragement_main,container,false);
       WebView webview=(WebView)rootview.findViewById(R.id.webview);
       webview.getSettings().setJavaScriptEnabled(true);
       webview.loadUrl("");
       webview.getSettings().setBuiltInZoomControls(true);
       webview.setInitialScale(58);
       return rootview;

   }
}
public static class SplashScreenFragment extends Fragment{
    public SplashScreenFragment(){

    }
}
