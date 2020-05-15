package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity2 extends AppCompatActivity {
    TextView capital;
    TextView country;
    ImageView imageView;
    TextView alphabet;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view2);

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

        intentData = intent.getStringExtra("alphabet");
        alphabet = findViewById(R.id.alphabetDetail);
        alphabet.setText(intentData);

    }
}
