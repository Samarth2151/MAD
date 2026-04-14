package com.example.reminder_alaram;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnPickDate, btnSetAlarm;
    private TextView tvSelectedDate;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timePicker = findViewById(R.id.timePicker);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        selectedDate = Calendar.getInstance();
        updateDateText();

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(Calendar.YEAR, year);
                    selectedDate.set(Calendar.MONTH, month);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateText();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void updateDateText() {
        String myFormat = "dd/MM/yyyy";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(myFormat, Locale.getDefault());
        tvSelectedDate.setText("Selected Date: " + sdf.format(selectedDate.getTime()));
    }

    private void setAlarm() {
        Calendar alarmCalendar = (Calendar) selectedDate.clone();
        alarmCalendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        alarmCalendar.set(Calendar.MINUTE, timePicker.getMinute());
        alarmCalendar.set(Calendar.SECOND, 0);

        long alarmTime = alarmCalendar.getTimeInMillis();

        if (alarmTime <= System.currentTimeMillis()) {
            Toast.makeText(this, "Please select a future time", Toast.LENGTH_SHORT).show();
            return;
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
            Toast.makeText(this, "Alarm set for " + alarmCalendar.getTime().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
