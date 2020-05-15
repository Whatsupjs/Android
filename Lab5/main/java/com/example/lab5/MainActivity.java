package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] capital1 = { "Buenos Aires", "Nassau", "Yaoude", "Copenhagen", "Cairo", "Helsinki",
            "Tbilisi", "Tegucigalpa", "Jakarta", "Nairob", "Kingston", "Vientiane"};
    String[] country1 = { "Argentina", "Bahamas", "Cameroon", "Denmark", "Egypt", "Finland" , "Georgia",
            "Honduras", "Indonesia","Jamaica", "Kenya", "Laos" };
    Integer[] imgid = { R.drawable.argentina, R.drawable.bahamas, R.drawable.cameroon, R.drawable.denmark, R.drawable.egypt,
                        R.drawable.finland, R.drawable.georgia, R.drawable.honduras, R.drawable.indonesia,
                        R.drawable.jamaica, R.drawable.kenya, R.drawable.laos };

    String[] capital2 = { "Canberra", "Dhaka", "Bogota", "Dijibouti", "Addis Ababa", "Paris", "Accra",
                            "Budapest", "Tehran", "Tokyo","Kuwait City", "Beirut"};
    String[] country2 = { "Australia", "Bangladesh", "Colombia", "Djibouti", "Ethiopia", "France",
                            "Ghana", "Hungary", "Iran", "Japan", "Kuwait", "Lebanon"};
    Integer[] imgid2 = { R.drawable.australia, R.drawable.bangladesh,R.drawable.colombia, R.drawable.djibouti,
                        R.drawable.ethiopia, R.drawable.france,R.drawable.ghana, R.drawable.hungary,
                         R.drawable.iran, R.drawable.japan, R.drawable.kuwait, R.drawable.lebanon};
    String[] alphabet = { "A", "B", "C", "D","E", "F", "G", "H", "I", "J", "K", "L" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListAdapter adapterList = new CustomListAdapter(this, capital1, country1, imgid);
        ListView list1 = findViewById(R.id.listRed);
        list1.setAdapter(adapterList);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("capital", capital1[position]);
                intent.putExtra("country", country1[position]);
                intent.putExtra("imgid", imgid[position].toString());
                startActivity(intent);
            }
        });

        CustomListAdapter2 adapterList2 = new CustomListAdapter2(this, capital2, country2, imgid2, alphabet);
        ListView list2 = findViewById(R.id.listPurp);
        list2.setAdapter(adapterList2);
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity2.class);
                intent.putExtra("capital", capital2[position]);
                intent.putExtra("country", country2[position]);
                intent.putExtra("imgid", imgid2[position].toString());
                intent.putExtra("alphabet", alphabet[position]);
                startActivity(intent);
            }
        });
    }
}
