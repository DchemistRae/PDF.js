package com.example.user.pdfjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity  {

    private static final int REQUEST_OPEN = 1337;
    private static final String STATE_ASSET = "asset";
    private static final String STATE_PICKED = "picked";
    //android.support.v4.app.FragmentManager manager;
   // android.support.v4.app.FragmentTransaction transaction;
   // private WebView wv;
    private String chosenAsset = null;
    public Uri pickedDocument = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // wv = (WebView) findViewById(R.id.webview);
        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        Intent intent = getIntent();
        pickedDocument = intent.getData();


        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        if (pickedDocument == null) {
            FirstFragment fragment1 = new FirstFragment();
            transaction.add(R.id.container, fragment1, "frag1");
            //transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Toast.makeText(this,"FILE LOADING...",Toast.LENGTH_SHORT).show();
            gotoSecondFragment(pickedDocument.toString());
        }
    }
    public void gotoSecondFragment(String s){
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        Bundle bundle=new Bundle();
        bundle.putString("uri",s);
        view fragment2=new view();
        fragment2.setArguments(bundle);
        transaction.replace(R.id.container,fragment2,"frag2");
       // transaction.addToBackStack(null);
        //transaction.commit();
        transaction.commitNowAllowingStateLoss();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.select_file:
                open();
                return true;
            case R.id.close:
                //startActivity(new Intent(this, MainActivity.class));
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void open() {
        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, REQUEST_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            pickedDocument = data.getData();
            // loadPdfUri(pickedDocument.toString());
           // view(pickedDocument.toString());
            Toast.makeText(this,"FILE LOADING...",Toast.LENGTH_SHORT).show();
            gotoSecondFragment(pickedDocument.toString());
        }
        super.onActivityResult(requestCode,resultCode,data);
        }


   /**  private void loadPdfUri(String uri) {
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
     try {
     wv.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" +
     URLEncoder.encode(uri, "UTF-8"));
     } catch (UnsupportedEncodingException e) {
     e.printStackTrace();
     }

     }    **/

}



