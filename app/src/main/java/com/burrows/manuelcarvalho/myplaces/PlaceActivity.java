package com.burrows.manuelcarvalho.myplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceActivity extends AppCompatActivity {
    private static final String TAG = "PlaceActivity";

    private TextView mTextViewLat;
    private TextView mTextViewLong;
    public static final String EXTRA_NAME =
            "com.burrows.manuelcarvalho.myplaces.EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.burrows.manuelcarvalho.myplaces.EXTRA_DESCRIPTION";
    private EditText mTextPlace;
    private EditText mTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        mTextViewLat = findViewById(R.id.text_view_lat);
        mTextViewLong = findViewById(R.id.text_view_long);
        mTextPlace = findViewById(R.id.editText_name);
        mTextDescription = findViewById(R.id.editText_description);
        mTextPlace.requestFocus();


        double e1, e2;
        Intent intent = this.getIntent();
        e1 = intent.getDoubleExtra("lat", 0);
        e2 = intent.getDoubleExtra("long", 0);

        mTextViewLat.setText(" " + Model.latitude);
        mTextViewLong.setText(" " + Model.longitude);

        //Log.d(TAG, "onCreate: " + Model.locationResult.getLastLocation().getLatitude() + " " + Model.locationResult.getLastLocation().getLongitude());
    }

    public void savePlace(View view) {
        String name = mTextPlace.getText().toString();
        String description = mTextDescription.getText().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, description);
        setResult(RESULT_OK, data);
        finish();

    }
}
