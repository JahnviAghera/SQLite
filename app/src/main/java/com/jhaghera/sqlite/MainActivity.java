package com.jhaghera.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Button confirm;
    EditText tv_f_name, tv_l_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirm = findViewById(R.id.btn_confirm);
        tv_f_name = findViewById(R.id.tv_f_name);
        tv_l_name = findViewById(R.id.tv_l_name);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this); // Replace 'this' with your context
                long newRowId = dbHelper.insertRecord(tv_f_name.getText().toString(), tv_l_name.getText().toString());

                if (newRowId != -1) {
                    Toast.makeText(MainActivity.this, "Entry Added at"+ newRowId, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                } else {
                    Toast.makeText(MainActivity.this, "Unsuccessful data not stored", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}