package com.example.menu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPopup;
    TextView txtContextTarget, txtMenuStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopup = findViewById(R.id.btnPopup);
        txtContextTarget = findViewById(R.id.txtContextTarget);
        txtMenuStatus = findViewById(R.id.txtMenuStatus);

        btnPopup.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPopup);
            popupMenu.getMenu().add("Edit");
            popupMenu.getMenu().add("Delete");
            popupMenu.getMenu().add("Share");

            popupMenu.setOnMenuItemClickListener(item -> {
                txtMenuStatus.setText("Selected: " + item.getTitle());
                return true;
            });

            popupMenu.show();
        });

        registerForContextMenu(txtContextTarget);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose Option");
        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0, v.getId(), 0, "Paste");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        txtMenuStatus.setText("Selected: " + item.getTitle());
        return true;
    }
}
