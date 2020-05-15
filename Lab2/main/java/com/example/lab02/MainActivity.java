package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_user = findViewById(R.id.btn_user);
        final Button btn_pw = findViewById(R.id.btn_pw);
        final Button btn_usepw = findViewById(R.id.btn_usepw);

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = MainActivity.this.findViewById(R.id.inputID);
                btn_user.setText(user.getText().toString());
            }
        });

        btn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pw = MainActivity.this.findViewById(R.id.inputPW);
                btn_pw.setText(pw.getText().toString());
            }
        });

        btn_usepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = MainActivity.this.findViewById(R.id.inputID);
                EditText pw = MainActivity.this.findViewById(R.id.inputPW);
                btn_usepw.setText(user.getText().toString() + " AND " + pw.getText().toString());
            }
        });

    }
}
