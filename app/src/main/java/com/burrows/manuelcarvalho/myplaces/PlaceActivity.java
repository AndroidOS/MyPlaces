package com.burrows.manuelcarvalho.myplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PlaceActivity extends AppCompatActivity {
    private static final String TAG = "PlaceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        double e1, e2;
        Intent intent = this.getIntent();
        e1 = intent.getDoubleExtra("lat", 0);
        e2 = intent.getDoubleExtra("long", 0);

        Log.d(TAG, "onCreate: " + e1 + " " + e2);
    }
}
