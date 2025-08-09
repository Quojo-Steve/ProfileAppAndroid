package com.example.profileapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllData extends AppCompatActivity {
    private ActivityResultLauncher<Intent> editLauncher;

    RecyclerView recyclerView;
    Database db;
    ArrayList<UserModel> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        loadUsers(); // refresh the list
                    }
                }
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new Database(this, null, null, 1);
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, userList, db, editLauncher);
        recyclerView.setAdapter(adapter);
        loadUsers(); // load the data initially
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadUsers() {
        userList.clear();
        Cursor cursor = db.getAllUsers();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String age = cursor.getString(cursor.getColumnIndexOrThrow("age"));
            String index = cursor.getString(cursor.getColumnIndexOrThrow("index_number"));
            String programme = cursor.getString(cursor.getColumnIndexOrThrow("programme"));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"));

            userList.add(new UserModel(name, age, index, programme, dob));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
