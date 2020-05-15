package com.example.lab5;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView capital;
    TextView country;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        Intent intent = getIntent();
        String intentData = intent.getStringExtra("capital");
        capital = findViewById(R.id.capitalDetail);
        capital.setText(intentData);

        intentData = intent.getStringExtra("country");
        country = findViewById(R.id.countryDetail);
        country.setText(intentData);

        intentData = intent.getStringExtra("imgid");
        Integer imageValue = new Integer(intentData);
        imageView = findViewById(R.id.iconIDDetail);
        imageView.setImageResource(imageValue);

    }
}
