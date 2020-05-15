package com.example.lab7;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    TextView name, dept, year;
    LinearLayout detailLayout;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String intentData = intent.getStringExtra("name");
        name = findViewById(R.id.detailName);
        name.setText(intentData);

        intentData = intent.getStringExtra("dept");
        dept = findViewById(R.id.detailDept);
        dept.setText(intentData);

        intentData = intent.getStringExtra("year");
        year = findViewById(R.id.detailYear);
        year.setText(intentData);

        intentData = intent.getStringExtra("color");
        detailLayout = findViewById(R.id.detailLayout);
        detailLayout.setBackgroundColor(Color.parseColor("#ff3d00"));
        if (intentData.equalsIgnoreCase("purple")){
            detailLayout.setBackgroundColor(Color.parseColor("#ba68c8"));
        }

    }
}
