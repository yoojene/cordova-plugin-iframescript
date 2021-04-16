package com.rocketlawyer.cordova.iframescript;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.util.Log;

public class IFrameScript extends CordovaPlugin {
    public static final String TAG = "IFrameScript";

    public void initialize(CordovaInterface cordova, CordovaWebViewImpl webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing IFrameScript");
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, action);
        if (action.equals("addScriptCode")) {
            String scriptName = args.getString(0);
            String script = args.getString(1);
            Log.d(TAG, "about to load " + script);
            this.addScript(script, callbackContext);
            return true;
        }
        return false;
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
    }
}