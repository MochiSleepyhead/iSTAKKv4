package com.capstone.istakk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerlayout;
    ImageView menu;
    LinearLayout dashboard, history, inventory, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerlayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        dashboard = findViewById(R.id.dashboard);
        history = findViewById(R.id.history);
        inventory = findViewById(R.id.inventory);
        exit = findViewById(R.id.exit);

        menu.setOnClickListener(view -> openDrawer(drawerlayout));
        dashboard.setOnClickListener(view -> recreate());
        history.setOnClickListener(view -> redirectActivity(MainActivity.this, HistoryActivity.class));
        inventory.setOnClickListener(view -> redirectActivity(MainActivity.this, InventoryActivity.class));
        exit.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show());
        {

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