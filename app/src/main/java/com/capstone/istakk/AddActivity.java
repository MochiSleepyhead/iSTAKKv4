package com.capstone.istakk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText product_input, quantity_input, price_input;

    MyDatabaseHelper myDB;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDB = new MyDatabaseHelper(this);

        product_input = findViewById(R.id.product_input);
        quantity_input = findViewById(R.id.quantity_input);
        price_input = findViewById(R.id.price_input);
        add_btn = findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = product_input.getText().toString().trim();
                int quantity = Integer.parseInt(quantity_input.getText().toString().trim());
                int price = Integer.parseInt(price_input.getText().toString().trim());

                if (product.isEmpty() || quantity == 0 || price == 0) {
                    Toast.makeText(AddActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addData(product, quantity, price);
                    Toast.makeText(AddActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after adding the data
                }
            }
        });
    }
}
