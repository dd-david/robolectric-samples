package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;


public class SampleIntentService extends IntentService {
    public SampleIntentService() {
        super("SampleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*
        * "example" 이라는 SharedPreference(xml) 파일을 만들어서
        *     Key   : "SAMPLE_DATA"
        *     Value : "sample data"
        * 를 저장합니다
        *
        * */
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(
                "example", Context.MODE_PRIVATE).edit();
        editor.putString("SAMPLE_DATA", "sample data");
        editor.apply();
    }
}
