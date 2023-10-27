package com.jhaghera.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editAge;
    private Button saveButton;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = findViewById(R.id.tv_f_name);
        editAge = findViewById(R.id.tv_f_name);
        saveButton = findViewById(R.id.btn_confirm);

        // Retrieve the record's ID that you want to edit from the intent
        int recordId = getIntent().getIntExtra("record_id", -1);

        // Fetch the record details based on the ID and populate the EditText fields

        // Assuming dbHelper is an instance of your DatabaseHelper
        DataModel record = dbHelper.getRecordById(recordId);
        if (record != null) {
            editName.setText(record.getF_name());
            editAge.setText(String.valueOf(record.getL_name()));
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated name and age from the EditText fields
                String updatedName = editName.getText().toString();
                String updatedL_Name = editAge.getText().toString();

                // Update the record in the database
                // Assuming dbHelper is an instance of your DatabaseHelper
                dbHelper.updateRecord(recordId, updatedName, updatedL_Name);

                // Redirect back to the DisplayActivity or any other activity
                Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
    }
}
