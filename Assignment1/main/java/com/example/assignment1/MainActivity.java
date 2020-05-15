package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Radio Group1 + btn
        final RadioGroup rgroup1 = findViewById(R.id.rgroup1);
        rgroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1a){
                    Toast.makeText(getApplicationContext(), "Alpha", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.rb1b){
                    Toast.makeText(getApplicationContext(), "Bravo", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.rb1c){
                    Toast.makeText(getApplicationContext(), "Charlie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button btn_rg1 = findViewById(R.id.btn1);
        final TextView rg1Text = findViewById(R.id.bonjour);

        btn_rg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int changedRadio = rgroup1.getCheckedRadioButtonId();
                if (changedRadio == R.id.rb1a){
                    rg1Text.setText("Bonjour Alpha");
                }
                if (changedRadio == R.id.rb1b){
                    rg1Text.setText("Bonjour Bravo");
                }
                if (changedRadio == R.id.rb1c){
                    rg1Text.setText("Bonjour Charlie");
                }
            }
        });

        //Radio Group2 + btn
        final RadioGroup rgroup2 = findViewById(R.id.rgroup2);
        rgroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb2a){
                    Toast.makeText(getApplicationContext(), "Delta", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.rb2b){
                    Toast.makeText(getApplicationContext(), "Echo", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.rb2c){
                    Toast.makeText(getApplicationContext(), "Foxtrot", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button btn_rg2 = findViewById(R.id.btn2);
        final TextView rg2Text = findViewById(R.id.hi);

        btn_rg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int changedRadio = rgroup2.getCheckedRadioButtonId();
                if (changedRadio == R.id.rb2a){
                    rg2Text.setText("Hi Delta");
                }
                if (changedRadio == R.id.rb2b){
                    rg2Text.setText("Hi Echo");
                }
                if (changedRadio == R.id.rb2c){
                    rg2Text.setText("Hi Foxtrot");
                }
            }
        });


        //CHECKBOXES
        final CheckBox CheckR = findViewById(R.id.cbox1);
        CheckR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())Toast.makeText(getApplicationContext(),
                        "Red", Toast.LENGTH_SHORT).show();
            }
        });

        final CheckBox CheckG = findViewById(R.id.cbox2);
        CheckG.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())Toast.makeText(getApplicationContext(),
                        "Green", Toast.LENGTH_SHORT).show();
            }
        });

        final CheckBox CheckB = findViewById(R.id.cbox3);
        CheckB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())Toast.makeText(getApplicationContext(),
                        "Blue", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_colour = findViewById(R.id.color_btn);
        btn_colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer colours = new StringBuffer();
                if(CheckR.isChecked()) colours.append(" Red");
                if(CheckG.isChecked()) colours.append(" Green");
                if(CheckB.isChecked()) colours.append(" Blue");

                Toast.makeText(getApplicationContext(), colours, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
