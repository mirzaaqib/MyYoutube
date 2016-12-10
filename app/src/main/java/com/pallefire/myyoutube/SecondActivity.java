package com.pallefire.myyoutube;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mirzaaqibbeg on 21-11-2016.
 */

public class SecondActivity extends AppCompatActivity {
    EditText et;
    Button b1;
    String data;
    WebView wv;
    String item;
    String link = "http://youtube.com/results?search_query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        wv = (WebView) findViewById(R.id.webview1);
        b1 = (Button) findViewById(R.id.bt1);
        et = (EditText) findViewById(R.id.et1);
        wv.getSettings().setJavaScriptEnabled(true);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String data = et.getText().toString();
                String datalink = link + data;
                wv.loadUrl(datalink);

                wv.setWebViewClient(new WebViewClient(){
                    public boolean ShouldOverrideUrlLoading(WebView view, String url){
                        if(view.getId() == R.id.webview1){
                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getApplicationContext().startActivity(intent);
                       intent.putExtra("url",url);
                        startActivity(intent);
                        return false;
                    }
                      else {
                            view.loadUrl(url);
                            return true;
                        }

                    };
        });

            }
        });
    }}