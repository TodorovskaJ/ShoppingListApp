package com.example.shoppinglistapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    EditText etName, etSize, etPrice;
    Button btnAddItem;
    /*ArrayList<ShoppingList> shoppingLists = new ArrayList<>();*/
    ShoppingListAdapter adapter;
    AppHolder appHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        etName = findViewById(R.id.etName);
        etSize = findViewById(R.id.etSize);
        etPrice = findViewById(R.id.etPrice);


        btnAddItem = findViewById(R.id.btnAddItem);
        enableButton(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (checkRequirements()) {
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        };

        etPrice.addTextChangedListener(textWatcher);
        etSize.addTextChangedListener(textWatcher);
        etName.addTextChangedListener(textWatcher);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = etName.getText().toString();
                String size = etSize.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                ShoppingList shoppingList = new ShoppingList(itemName,
                        size, price);

                AppHolder.appholder.add(shoppingList);
              /*  adapter.notifyDataSetChanged();*/


                etName.setText("");
                etSize.setText("");
                etPrice.setText("");
                Toast.makeText(AddItemActivity.this, " Item is successfully added" , Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void enableButton(boolean shouldEnable) {
        if (shouldEnable) {
            btnAddItem.setEnabled(true);
            btnAddItem.setClickable(true);
            btnAddItem.setAlpha(1);
        } else {
            btnAddItem.setEnabled(false);
            btnAddItem.setClickable(false);
            btnAddItem.setAlpha(0.5f);
        }
    }

    public boolean checkRequirements() {
        if (etName.getText().toString().length() > 0 &&
                etSize.getText().toString().length() > 0 &&
                etPrice.getText().toString().length() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
