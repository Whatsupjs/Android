package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        final Button btn_login = findViewById(R.id.btn_login);

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = MainActivity.this.findViewById(R.id.inputID);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("username", user.getText().toString());
                startActivity(intent);
            }
        });

        btn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pw = MainActivity.this.findViewById(R.id.inputPW);
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("password", pw.getText().toString());
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = MainActivity.this.findViewById(R.id.inputID);
                EditText pw = MainActivity.this.findViewById(R.id.inputPW);
                Intent intent = new Intent(MainActivity.this, FourthActivity.class);
                intent.putExtra("username", user.getText().toString());
                intent.putExtra("password", pw.getText().toString());
                startActivity(intent);
            }
        });
    }
}
