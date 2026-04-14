package com.example.ratingprogress;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        ProgressBar progressBar = findViewById(R.id.progressBar);
        ProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView ratingText = findViewById(R.id.ratingText);

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            int progress = (int) (rating * 20);
            progressBar.setProgress(progress);
            circularProgressBar.setProgress(progress);
            ratingText.setText("Rating: " + rating);
        });
    }
}
