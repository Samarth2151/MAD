package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etCity, etAge;
    Button btnSave, btnLoad;
    TableLayout tableLayout;
    String fileName = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etCity = findViewById(R.id.etCity);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        tableLayout = findViewById(R.id.tableLayout);

        btnSave.setOnClickListener(v -> {
            try {
                String s = etName.getText() + "," + etEmail.getText() + "," + 
                           etPhone.getText() + "," + etCity.getText() + "," + 
                           etAge.getText() + "\n";
                
                FileOutputStream out = openFileOutput(fileName, MODE_APPEND);
                out.write(s.getBytes());
                out.close();
                
                etName.setText(""); etEmail.setText(""); etPhone.setText(""); 
                etCity.setText(""); etAge.setText("");
                Toast.makeText(this, "Saved to data.txt", Toast.LENGTH_SHORT).show();
            } catch (Exception e) { }
        });

        btnLoad.setOnClickListener(v -> {
            try {
                tableLayout.removeAllViews();
                FileInputStream in = openFileInput(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] d = line.split(",");
                    if (d.length == 5) {
                        addRow("Name: " + d[0]);
                        addRow("Email: " + d[1]);
                        addRow("Phone: " + d[2]);
                        addRow("City: " + d[3]);
                        addRow("Age: " + d[4]);
                        addRow("-----------------");
                    }
                }
                br.close();
            } catch (Exception e) {
                Toast.makeText(this, "No data saved yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addRow(String text) {
        TableRow row = new TableRow(this);
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(10, 5, 10, 5);
        row.addView(tv);
        tableLayout.addView(row);
    }
}
