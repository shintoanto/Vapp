package com.emmj.vapp;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    public WebView prayerWeb;
    public ProgressBar progressBar;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pbLoad);

        //Starting Webview code
        prayerWeb = this.findViewById(R.id.prayerWeb);
        prayerWeb.getSettings().setJavaScriptEnabled(true);
        prayerWeb.loadUrl("https://shintoanto.github.io/PRAYER-ROOM-static-website/");

        // adding proggress bar.
        prayerWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

        });

    }
    // set back button.
    @Override
    public void onBackPressed() {
        if (prayerWeb.canGoBack()) {
            prayerWeb.goBack();
        } else {
            alertBox();
        }
    }

    //set closing alert box.
    private void alertBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("PRAYER ROOM");
        builder.setIcon(R.drawable.progress_color);
        builder.setMessage("Are you sure want to close prayer room")
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("PRAYER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        prayerWeb.goBack();
                    }
                });

        builder.create();
        builder.show();
    }
}
