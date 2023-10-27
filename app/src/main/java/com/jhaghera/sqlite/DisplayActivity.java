package com.jhaghera.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private static final int EDIT_OPTION_ID = R.id.edit_option;
    private static final int DELETE_OPTION_ID = R.id.delete_option;
    FloatingActionButton fab;
    ListView listView;
    RecordAdapter adapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        // Retrieve records from the database
        List<DataModel> records = dbHelper.getAllRecords();

        // Create and set the adapter for the ListView
        adapter = new RecordAdapter(this, records);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity to add a new record
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        // Handle item click for viewing details
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DataModel selectedRecord = adapter.getItem(position);
//                // Start an activity to view record details
//                Intent intent = new Intent(DisplayActivity.this, ViewDetailsActivity.class);
//                intent.putExtra("record_id", selectedRecord.getId());
//                startActivity(intent);
//            }
//        });

        // Register a long click listener for the ListView items to show PopupMenu
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
                return true;
            }
        });
    }

    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());

        DataModel selectedRecord = adapter.getItem(position);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                DataModel selectedRecord = adapter.getItem(position);

                if (item.getItemId() == R.id.edit_option) {
                    // Start an activity to edit the selected record
                    Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                    intent.putExtra("record_id", selectedRecord.getId());
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.delete_option) {
                    // Delete the selected record from the database
                    dbHelper.deleteRecord(selectedRecord.getId());
                    // Remove the record from the adapter and notify the change
                    adapter.remove(selectedRecord);
                    adapter.notifyDataSetChanged();
                    return true;
                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }


}
