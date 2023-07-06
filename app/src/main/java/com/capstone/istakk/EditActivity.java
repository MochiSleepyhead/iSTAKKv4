package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private Button updateButton;

    private String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextName = findViewById(R.id.name_input);
        editTextQuantity = findViewById(R.id.quantity_input);
        editTextPrice = findViewById(R.id.price_input);
        updateButton = findViewById(R.id.update_btn);

        itemId = getIntent().getStringExtra("_id");

        // Fetch existing data for the given itemId and pre-fill the EditText fields
        fetchDataForItem(itemId);

        updateButton.setOnClickListener(v -> updateItem());
    }

    private void fetchDataForItem(String itemId) {
        // TODO: Fetch existing data for the given itemId from the database
        // and pre-fill the EditText fields with the fetched data
    }

    private void updateItem() {
        String updatedName = editTextName.getText().toString();
        String updatedQuantity = editTextQuantity.getText().toString();
        String updatedPrice = editTextPrice.getText().toString();

        Toast.makeText(this, "Item updated successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}
