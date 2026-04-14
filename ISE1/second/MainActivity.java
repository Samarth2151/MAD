package com.example.temp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.i10).setOnClickListener(v -> {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.example.temp");
            if (i != null) {
                startActivity(i);
            } else {
                Toast.makeText(this, "App not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
