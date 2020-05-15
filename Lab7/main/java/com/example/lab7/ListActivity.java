package com.example.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    String[] listNameL;
    String[] listNameR;
    String[] listDeptL;
    String[] listDeptR;
    String[] listYearL;
    String[] listYearR;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        listNameL = intent.getStringArrayExtra("extraNameL");
        listDeptL = intent.getStringArrayExtra("extraDeptL");
        listYearL = intent.getStringArrayExtra("extraYearL");

        listNameR = intent.getStringArrayExtra("extraNameR");
        listDeptR = intent.getStringArrayExtra("extraDeptR");
        listYearR = intent.getStringArrayExtra("extraYearR");

        ListView redList = findViewById(R.id.listRed);
        redList.setAdapter(new CustomListAdapter(this, listNameL));
        redList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listNameL[position] != null){
                    Intent intent = new Intent (ListActivity.this, DetailsActivity.class);
                    intent.putExtra("color", "red");
                    intent.putExtra("name", listNameL[position]);
                    intent.putExtra("dept", listDeptL[position]);
                    intent.putExtra("year", listYearL[position]);
                    startActivity(intent);
                }
            }
        });

        ListView purpList = findViewById(R.id.listPurp);
        purpList.setAdapter(new CustomListAdapter(this, listNameR));
        purpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listNameR[position] != null){
                    Intent intent = new Intent (ListActivity.this, DetailsActivity.class);
                    intent.putExtra("color", "purple");
                    intent.putExtra("name", listNameR[position]);
                    intent.putExtra("dept", listDeptR[position]);
                    intent.putExtra("year", listYearR[position]);
                    startActivity(intent);
                }
            }
        });
    }
}
