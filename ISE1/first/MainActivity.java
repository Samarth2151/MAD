package com.example.temp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText e1 = findViewById(R.id.e1);
        EditText e2 = findViewById(R.id.e2);

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            if (e1.getText().toString().equals("sam") && e2.getText().toString().equals("1234")) {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.example.temp1");
                 startActivity(i);
            }
        });
    }
}
