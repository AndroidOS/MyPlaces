package com.burrows.manuelcarvalho.myplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class PlaceActivity extends AppCompatActivity {
    private static final String TAG = "PlaceActivity";

    private TextView mTextViewLat;
    private TextView mTextViewLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        mTextViewLat = findViewById(R.id.text_view_lat);
        mTextViewLong = findViewById(R.id.text_view_long);

        double e1, e2;
        Intent intent = this.getIntent();
        e1 = intent.getDoubleExtra("lat", 0);
        e2 = intent.getDoubleExtra("long", 0);

        mTextViewLat.setText(" " + Model.locationResult.getLastLocation().getLatitude());
        mTextViewLong.setText(" " + Model.locationResult.getLastLocation().getLongitude());

        Log.d(TAG, "onCreate: " + Model.locationResult.getLastLocation().getLatitude() + " " + Model.locationResult.getLastLocation().getLongitude());
    }
}
