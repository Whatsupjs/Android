package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addBtn, exitBtn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

        final List<String> cityList = databaseHandler.getAllItems("city");
        final String[] cityArray = cityList.toArray(new String[0]);

        final List<String> nameList = databaseHandler.getAllItems("name");
        final String[] nameArray = nameList.toArray(new String[0]);

        final List<String> sportList = databaseHandler.getAllItems("sport");
        final String[] sportArray = sportList.toArray(new String[0]);

        final List<String> MVPList = databaseHandler.getAllItems("MVP");
        final String[] MVPArray = MVPList.toArray(new String[0]);

        final List<String> stadiumList = databaseHandler.getAllItems("stadium");
        final String[] stadiumArray = stadiumList.toArray(new String[0]);



        CustomListAdapter adapter = new CustomListAdapter(this, cityArray, nameArray);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("city", cityArray[position]);
                intent.putExtra("name", nameArray[position]);
                intent.putExtra("sport", sportArray[position]);
                intent.putExtra("MVP", MVPArray[position]);
                intent.putExtra("stadium", stadiumArray[position]);
                intent.putExtra("command", "ud");
                startActivity(intent);
            }
        });

        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("city", "");
                intent.putExtra("name", "");
                intent.putExtra("sport", "");
                intent.putExtra("MVP", "");
                intent.putExtra("stadium", "");
                intent.putExtra("command", "add");
                startActivity(intent);
            }
        });
    }
}
