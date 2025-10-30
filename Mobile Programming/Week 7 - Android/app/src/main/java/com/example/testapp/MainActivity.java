package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends Activity {
    private static final String TAG = "SEOULTECH";
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "+++ ON CREATE +++");
    }
    @Override protected void onStart() {
        super.onStart(); Log.v(TAG, "++ ON START ++");
    }
    @Override protected void onPostResume() {
        super.onPostResume(); Log.v(TAG, "+ ON RESUME +");
    }
    @Override protected void onPause() {
        super.onPause(); Log.v(TAG, "- ON PAUSE -");
    }
    @Override protected void onStop() {
        super.onStop(); Log.v(TAG, "–- ON STOP --");
    }
    @Override protected void onDestroy() {
        super.onDestroy(); Log.v(TAG, "--- ON DESTROY ---");
    }
}