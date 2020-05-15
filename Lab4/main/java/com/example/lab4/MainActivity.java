package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Character[] array_alpha = new Character[26];
    Number[] array_num = new Number[99];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0 ; i < array_alpha.length; i++){
            array_alpha[i] = (char)(65+i);
        }

        ListView listView = findViewById(R.id.lview);
        ArrayAdapter<Character> adapterList = new ArrayAdapter<>(this, R.layout.my_list_item, array_alpha);
        listView.setAdapter(adapterList);

        for (int j = 0 ; j < array_num.length; j++){
            array_num[j] = ((j+1) * 5);
        }

        GridView gridView = findViewById(R.id.gview);
        ArrayAdapter<Number> adapterGrid = new ArrayAdapter<>(this, R.layout.my_grid_item, array_num);
        gridView.setAdapter(adapterGrid);
    }
}
