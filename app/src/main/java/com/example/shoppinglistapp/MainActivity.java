package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTusername, eTpassword;
    CheckBox cBrememberme;
    Button btnlogin;
    boolean isCheckedCheckBox = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTusername = findViewById(R.id.eTusername);
        eTpassword = findViewById(R.id.eTpassword);
        cBrememberme = findViewById(R.id.cBrememberme);
        btnlogin = findViewById(R.id.btnLogin);
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
                if (checkRequirement()) {
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        };

        eTusername.addTextChangedListener(textWatcher);
        eTpassword.addTextChangedListener(textWatcher);
        btnlogin.setOnClickListener( this);
        cBrememberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isCheckedCheckBox = true;
                } else {
                    isCheckedCheckBox = false;
                }
            }
        });

    }

    private Boolean checkRequirement() {
        boolean result = false;
        if (eTusername.getText().toString().length() > 3 &&
                eTpassword.getText().toString().length() > 3) {
            result = true;
        }
        return result;
    }

    private void enableButton(boolean enable) {
        if (enable) {
            btnlogin.setEnabled(true);
            btnlogin.setClickable(true);
            btnlogin.setAlpha(1f);
        } else {
            btnlogin.setEnabled(false);
            btnlogin.setClickable(false);
            btnlogin.setAlpha(0.5f);
        }
    }

    @Override
    public void onClick(View v) {

        String username = eTusername.getText().toString();
        String password = eTpassword.getText().toString();

        Toast.makeText(MainActivity.this, "Successful logged in", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("USERNAME", username);
        bundle.putString("PASSWORD", password);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
