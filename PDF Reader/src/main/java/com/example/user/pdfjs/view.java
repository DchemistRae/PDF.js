package com.example.user.pdfjs;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class view extends Fragment {

    public static final String chosenAsset = "uri";
    WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        //
        // Bundle bundle = this.getArguments();
        //String uri =bundle.getString("uri");
        //loadPdfUri(uri);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String link = bundle.getString("uri");
            loadPdfUri(link);
        }
    }

    private void loadPdfUri(String uri) {
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wv = (WebView) getView().findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setAllowUniversalAccessFromFileURLs(true);

        try {
            wv.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" +
                    URLEncoder.encode(uri, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}