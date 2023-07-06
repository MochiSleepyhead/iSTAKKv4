package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity implements CustomAdapter.ItemClickListener {

    MyDatabaseHelper myDB = new MyDatabaseHelper(InventoryActivity.this);
    DrawerLayout drawerlayout;
    ImageView menu;
    LinearLayout dashboard, history, inventory, exit;

    RecyclerView inventoryData;
    FloatingActionButton add_btn;

    ArrayList<String> _id, product_name, product_quantity, product_price;
    CustomAdapter customAdapter;

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

        inventoryData = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        _id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_quantity = new ArrayList<>();
        product_price = new ArrayList<>();

        inventoryData = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(InventoryActivity.this, _id, product_name, product_quantity, product_price);
        customAdapter.setClickListener(this);
        inventoryData.setAdapter(customAdapter);
        inventoryData.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));

        recyclerClickListeners();
        storeDataInArrays();
    }

    private void recyclerClickListeners() {
        inventoryData.setItemAnimator(new DefaultItemAnimator());
        inventoryData.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            int columnCount = cursor.getColumnCount();
            Log.d("Cursor", "Column Count: " + columnCount);
            while (cursor.moveToNext()) {
                _id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_quantity.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
            }
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        // Get the selected item ID
        String selectedItemId = _id.get(position);

        // Display a dialog or options to edit/delete the item
        // For example, you can show a dialog with edit and delete buttons
        showEditDeleteDialog(selectedItemId);
    }

    private void showEditDeleteDialog(final String itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);
        builder.setTitle("Choose an option")
                .setItems(new String[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Edit option selected
                                openEditActivity(itemId);
                                break;
                            case 1:
                                // Delete option selected
                                deleteItem(itemId);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void openEditActivity(String itemId) {
        Intent intent = new Intent(InventoryActivity.this, EditActivity.class);
        intent.putExtra("ITEM_ID", itemId);
        startActivity(intent);
    }

    public boolean deleteItem(String itemId) {
        boolean deletionSuccessful = myDB.deleteData(itemId);
        if (deletionSuccessful) {
            // Item deleted successfully
            // Remove the deleted item from the arrays
            int position = _id.indexOf(itemId);
            if (position != -1) {
                _id.remove(position);
                product_name.remove(position);
                product_quantity.remove(position);
                product_price.remove(position);
                customAdapter.notifyItemRemoved(position);
            }
        } else {
            // Failed to delete item
            Toast.makeText(this, "Failed to delete item", Toast.LENGTH_SHORT).show();
        }
        return deletionSuccessful;
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class<?> secondActivity) {
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
