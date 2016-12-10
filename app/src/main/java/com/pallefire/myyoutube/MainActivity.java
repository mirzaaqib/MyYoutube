package com.pallefire.myyoutube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayerView yv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize here
        yv = (YouTubePlayerView) findViewById(R.id.yt1);
        //pass the api key here & this for that particular screen
        yv.initialize("AIzaSyAGvvdhbz4GwPH211k0ENzF0ZyowshA1Ko", this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String str1 = bundle.getString("url");

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer Yv, boolean b) {
        final boolean[] loadingFinished = {true};
        final boolean[] redirect = {false};

        //here we pass the particular video key or video number
        Yv.loadVideo("url");
        final WebView webView = null;
        webView.setWebViewClient(new WebViewClient() {
//            Intent intent =getIntent();
//            Bundle b = intent.getExtras();
//            String str1 = b.getString("url");
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                String str1 = b.getString("url");
                Toast.makeText(MainActivity.this, "" + request, Toast.LENGTH_SHORT).show();
                webView.loadUrl(String.valueOf(request));
                if (!loadingFinished[0]) {
                    redirect[0] = true;
                }

                loadingFinished[0] = false;
                webView.loadUrl(request.getUrl().toString());
                return true;


            }
        });
        
    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //here in the failure method you can show the message to the user
        Toast.makeText(this, "check the internet Connection", Toast.LENGTH_SHORT).show();

    }
}
