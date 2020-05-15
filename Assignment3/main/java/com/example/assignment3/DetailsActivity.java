package com.example.assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    EditText editCity, editName, editSport, editMVP, editStadium;
    Button btnSubmit, btnUpdate, btnDelete, btnExit;
    LinearLayout topLayout, bottomLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editCity = findViewById(R.id.city);
        editName = findViewById(R.id.name);
        editSport = findViewById(R.id.sport);
        editMVP = findViewById(R.id.MVP);
        editStadium = findViewById(R.id.stadium);
        topLayout = findViewById(R.id.topLayout);
        bottomLayout = findViewById(R.id.bottomLayout);

        btnSubmit = findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
                String city = editCity.getText().toString();
                String name = editName.getText().toString();
                String sport = editSport.getText().toString();
                String MVP = editMVP.getText().toString();
                String stadium = editStadium.getText().toString();

                if( city.length() == 0 || name.length() == 0){
                    Toast.makeText(DetailsActivity.this, "City and Name are required",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dbHandler.insertItem(city,name,sport,MVP,stadium);
                    editCity.setText("");
                    editName.setText("");
                    editSport.setText("");
                    editMVP.setText("");
                    editStadium.setText("");
                }
            }
        });
        btnUpdate = findViewById(R.id.updateBtn);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDelete = findViewById(R.id.deleteBtn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnExit = findViewById(R.id.exitBtn);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                //finishAffinity();
            }
        });

        Intent intent = getIntent();

        String stringData = intent.getStringExtra("city");
        editCity.setText(stringData);

        stringData = intent.getStringExtra("name");
        editName.setText(stringData);

        stringData = intent.getStringExtra("sport");
        editSport.setText(stringData);

        stringData = intent.getStringExtra("MVP");
        editMVP.setText(stringData);

        stringData = intent.getStringExtra("stadium");
        editStadium.setText(stringData);

        stringData = intent.getStringExtra("command");
        if(stringData.equals("add")){
            btnDelete.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            topLayout.setBackgroundColor(Color.parseColor("#ffc107"));
        }
        if(stringData.equals("ud")){
            btnSubmit.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnUpdate.setEnabled(false);
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setEnabled(false);
            topLayout.setBackgroundColor(Color.parseColor("#ff1744"));
        }
    }
}
