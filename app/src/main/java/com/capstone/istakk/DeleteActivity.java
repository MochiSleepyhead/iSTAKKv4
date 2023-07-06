package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    private Button deleteButton;

    private String itemId;
    private MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        myDB = new MyDatabaseHelper(this);


        itemId = getIntent().getStringExtra("ITEM_ID");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });
    }

    private void deleteItem() {
        boolean isDeleted = myDB.deleteData(itemId);

        if (isDeleted) {
            Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete item", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
