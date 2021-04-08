package com.rocketlawyer.cordova.iframescript;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.rocketlawyer.mobile.android.R;

import android.util.Log;

public class IFrameScript extends CordovaPlugin {
    public static final String TAG = "IFrameScript";

    private CordovaWebView mWebView;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
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

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, action);

        if (action.equals("echo")) {
            String phrase = args.getString(0);
            this.echo(phrase, callbackContext);
            return true;
        }

        if (action.equals("addScriptCode")) {
            String script = args.getString(0);

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

        Log.d(TAG, "about to load " + script + " into mWebView");
        mWebView.loadUrl(script);

    }


}