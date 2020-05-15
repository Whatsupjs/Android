package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        final Button btn_home = findViewById(R.id.btn_home);

        String username = getIntent().getStringExtra("username");
        TextView user = findViewById(R.id.inputID);
        user.setText(username);

        String password = getIntent().getStringExtra("password");
        TextView pwd = findViewById(R.id.inputPW);
        pwd.setText(password);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
