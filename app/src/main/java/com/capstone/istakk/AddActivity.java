package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText product_input, quantity_input, low_inventory_input, price_input;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        product_input = findViewById(R.id.product_input);
        quantity_input = findViewById(R.id.quantity_input);
        low_inventory_input = findViewById(R.id.low_inventory_input);
        price_input = findViewById(R.id.price_input);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
            myDB.addBook(
                    product_input.getText() .toString() .trim(),
                    Integer.valueOf(quantity_input.getText() .toString() .trim()),
                    Integer.valueOf(price_input.getText() .toString() .trim()));
        })
    ;}
}