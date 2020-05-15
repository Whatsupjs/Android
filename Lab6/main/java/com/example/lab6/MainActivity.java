package com.example.lab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqlDatabase;
    EditText id, name, marks;
    Button btn_add, btn_view, btn_find, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sqlDatabase = openOrCreateDatabase("Lab6DB", Context.MODE_PRIVATE, null);
        sqlDatabase.execSQL("CREATE TABLE IF NOT EXISTS studentTable(studnetID VARCHAR, studentName VARCHAR, studentMarks VARCHAR);");

        id = findViewById(R.id.inputID);
        name = findViewById(R.id.inputName);
        marks = findViewById(R.id.inputMark);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( id.getText().length() > 0 && name.getText().length() > 0 && marks.getText().length() > 0){
                    sqlDatabase.execSQL("INSERT INTO studentTable VALUES ('" + id.getText() + "','" +
                            name.getText() + "','" + marks.getText() + "');");

                    StringBuffer msg = new StringBuffer();
                    msg.append("ID:" + id.getText().toString() + ' ');
                    msg.append("Name:" + name.getText().toString() + ' ');
                    msg.append("Marks:" + marks.getText().toString());

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("The following student was added");
                    alert.setMessage(msg);
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();

                    id.setText("");
                    name.setText("");
                    marks.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Incomplete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_view = findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor selectAll = sqlDatabase.rawQuery("SELECT * FROM studentTable", null);

                if(selectAll.getCount() == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("No students were found");
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                    return;
                } else {
                    StringBuffer msg = new StringBuffer();

                    while (selectAll.moveToNext()) {
                        msg.append("ID:"+ selectAll.getString(0)+ ' ');
                        msg.append("Name:"+ selectAll.getString(1)+ ' ');
                        msg.append("Marks:"+ selectAll.getString(2));
                        msg.append('\n');
                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("The following student has been added");
                    alert.setMessage(msg);
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                }
            }
        });

        btn_find = findViewById(R.id.btn_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer msg = new StringBuffer();
                Cursor findQ = sqlDatabase.rawQuery("SELECT * FROM studentTable WHERE studnetID = '" + id.getText().toString() + "'", null);

                Log.d("FIND_TAG", String.valueOf(findQ.getCount()));

                if ( findQ.getCount() == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("No students were found");
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                    return;
                } else {
                    while (findQ.moveToNext()){
                        msg.append("ID:"+ findQ.getString(0)+ ' ');
                        msg.append("Name:"+ findQ.getString(1)+ ' ');
                        msg.append("Marks:"+ findQ.getString(2));
                        msg.append('\n');
                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Student details are as follows");
                    alert.setMessage(msg);
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();

                    id.setText("");
                }
            }
        });

        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer msg = new StringBuffer();
                Cursor findQ = sqlDatabase.rawQuery("SELECT * FROM studentTable WHERE studnetID = '" + id.getText().toString() + "'", null);
                if ( findQ.getCount() == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("No students were found");
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                    return;
                } else {
                    findQ.moveToFirst();
                    msg.append("ID:" + findQ.getString(0) + " ");
                    msg.append("Name:" + findQ.getString(1) + " ");
                    msg.append("Marks:" + findQ.getString(2));

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("The following student has been deleted");
                    alert.setMessage(msg);
                    alert.setCancelable(true);
                    alert.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                    sqlDatabase.execSQL("DELETE FROM studentTable WHERE studnetID = '" + id.getText().toString() + "'");
                    id.setText("");
                }
            }
        });
    }
}
