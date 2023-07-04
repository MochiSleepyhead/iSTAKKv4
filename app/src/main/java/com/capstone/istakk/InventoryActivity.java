package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InventoryActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
    ImageView menu;
    LinearLayout dashboard, history, inventory, exit;

    RecyclerView recyclerView;
    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        drawerlayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        dashboard = findViewById(R.id.dashboard);
        history = findViewById(R.id.history);
        inventory = findViewById(R.id.inventory);
        exit = findViewById(R.id.exit);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerlayout);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(InventoryActivity.this, MainActivity.class);

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(InventoryActivity.this, HistoryActivity.class);
            }
        });
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InventoryActivity.this, "Exit", Toast.LENGTH_SHORT).show();
            }
        });
        {
            recyclerView = findViewById(R.id.recyclerView);
            add_btn = findViewById(R.id.add_btn);
            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(InventoryActivity.this, AddActivity.class);
                    startActivity(intent);
                }

            });
        }

    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerlayout);
    }
}