package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnAddR, btnAddL, btnView;

    EditText nameL, nameR;
    EditText deptL, deptR;
    EditText yearL, yearR;

    SQLiteDatabase sqlDatabase;

    String[] listNameL = new String[50];
    String[] listNameR = new String[50];
    String[] listDeptL = new String[50];
    String[] listDeptR = new String[50];
    String[] listYearL = new String[50];
    String[] listYearR = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDatabase = openOrCreateDatabase("Lab7DB", Context.MODE_PRIVATE, null);
        sqlDatabase.execSQL("CREATE TABLE IF NOT EXISTS redTable(nameL VARCHAR, deptL VARCHAR, yearL VARCHAR);");
        sqlDatabase.execSQL("CREATE TABLE IF NOT EXISTS purpTable(nameR VARCHAR, deptR VARCHAR, yearR VARCHAR);");

        nameL = findViewById(R.id.lName);
        deptL = findViewById(R.id.lDEPT);
        yearL = findViewById(R.id.lYEAR);

        nameR = findViewById(R.id.rName);
        deptR = findViewById(R.id.rDEPT);
        yearR = findViewById(R.id.rYEAR);

        btnAddL = findViewById(R.id.btn1);
        btnAddL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nameL.getText()) || TextUtils.isEmpty(deptL.getText()) || TextUtils.isEmpty(yearL.getText())){
                    Toast.makeText(getApplicationContext(), "Incomplete", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    sqlDatabase.execSQL("INSERT INTO redTable VALUES(?,?,?)", new Object[]{nameL.getText(), deptL.getText(), yearL.getText()});

                    nameL.setText("");
                    deptL.setText("");
                    yearL.setText("");
                }
            }
        });

        btnAddR = findViewById(R.id.btn2);
        btnAddR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nameR.getText()) || TextUtils.isEmpty(deptR.getText()) || TextUtils.isEmpty(yearR.getText())){
                    Toast.makeText(getApplicationContext(), "Incomplete", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    sqlDatabase.execSQL("INSERT INTO purpTable VALUES(?,?,?)", new Object[]{nameR.getText(), deptR.getText(), yearR.getText()});

                    nameR.setText("");
                    deptR.setText("");
                    yearR.setText("");
                }
            }
        });

        btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor redCursor = sqlDatabase.rawQuery("SELECT * FROM redTable", null);
                int index = 0;

                while (redCursor.moveToNext()){
                    listNameL[index] = redCursor.getString(redCursor.getColumnIndex("nameL"));
                    listDeptL[index] = redCursor.getString(redCursor.getColumnIndex("deptL"));
                    listYearL[index] = redCursor.getString(redCursor.getColumnIndex("yearL"));
                    index++;
                }

                Cursor purpCursor = sqlDatabase.rawQuery("SELECT * FROM purpTable", null);
                int index2 = 0;

                while (purpCursor.moveToNext()){
                    listNameR[index2] = purpCursor.getString(purpCursor.getColumnIndex("nameR"));
                    listDeptR[index2] = purpCursor.getString(purpCursor.getColumnIndex("deptR"));
                    listYearR[index2] = purpCursor.getString(purpCursor.getColumnIndex("yearR"));
                    index2++;
                }

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("extraNameL", listNameL);
                intent.putExtra("extraDeptL", listDeptL);
                intent.putExtra("extraYearL", listYearL);

                intent.putExtra("extraNameR", listNameR);
                intent.putExtra("extraDeptR", listDeptR);
                intent.putExtra("extraYearR", listYearR);

                startActivity(intent);
                redCursor.close();
                purpCursor.close();
            }
        });
    }
}
