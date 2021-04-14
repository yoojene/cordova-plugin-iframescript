package com.rocketlawyer.cordova.iframescript;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.rocketlawyer.mobile.android.R;

import android.util.Log;

public class IFrameScript extends CordovaPlugin {
    public static final String TAG = "IFrameScript";

    public void initialize(CordovaInterface cordova, CordovaWebViewImpl webView) {
        super.initialize(cordova, webView);

        Log.d(TAG, "Initializing IFrameScript");
//        mWebView.setWeb
//
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebChromeClient(new WebChromeClient());
//        mWebView.setWebViewClient(new WebViewClient());

//        {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                String user = ((EditText) findViewById(R.id.edit_text)).getText().toString();
//                if (user.isEmpty()) {
//                    user = "World";
//                }
//                String javascript="javascript: document.getElementById('msg').innerHTML='Hello "+user+"!';";
//
//
//                view.loadUrl(url);
//            }
//        });

    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, action);

        if (action.equals("echo")) {
            String phrase = args.getString(0);
            this.echo(phrase, callbackContext);
            return true;
        }

        if (action.equals("addScriptCode")) {
            String scriptName = args.getString(0);
            String script = args.getString(1);
            String script2 = args.getString(2);

//            JSONObject scriptObj = args.getJSONObject(1);
//            JSONObject scriptObj2 = args.getJSONObject(2);

            Log.d(TAG, "grabbed arg 0 " + scriptName);
            Log.d(TAG, "grabbed arg 1 " + script);
            Log.d(TAG, "grabbed arg 2 " + script2);


            Log.d(TAG, "about to load " + script);


            //.webView.getClass().getMethod("getView").invoke(webView)
            this.addScript(script, callbackContext);

            
        }

        return false;
    }

    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            Log.d(TAG, message);
        } else {
            callbackContext.error("Expected one non-empty string argument");
        }

    }

    private void addScript(String script, CallbackContext callbackContext) {

        CordovaWebView view = this.webView;
        Activity activity = this.cordova.getActivity();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "about to load on UI thread " + script + " into webview");
                view.loadUrlIntoView(script, false);
            }
        });




//        Log.d(TAG, "about to load " + script + " into mWebView");
//        Activity activity = this.cordova.getActivity();
//        CordovaWebView view;
//
//        view = (CordovaWebView).webView.getClass().getMethod("getView").invoke(webView);


        //        mWebView.loadUrl(script);
//        mWebView.init(cordova);
//        mWebView.loadUrlIntoView(script, false);

    }


}