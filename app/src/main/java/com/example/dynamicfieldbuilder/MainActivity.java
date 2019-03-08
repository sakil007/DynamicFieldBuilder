package com.example.dynamicfieldbuilder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.vijay.jsonwizard.activities.JsonFormActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GET_JSON = 101;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext=this;
        Intent intent = new Intent(mContext, JsonFormActivity.class);
        intent.putExtra("json",loadJSONFromAsset());
        startActivityForResult(intent, REQUEST_CODE_GET_JSON);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("Pattern.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == REQUEST_CODE_GET_JSON && resultCode == RESULT_OK) {
           Log.d("TAG", data.getStringExtra("json"));
       }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
