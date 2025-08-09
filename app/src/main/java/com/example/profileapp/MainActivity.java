package com.example.profileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText name, age, index_num, programme, dob;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        index_num = findViewById(R.id.index_num);
        programme = findViewById(R.id.programme);
        dob = findViewById(R.id.dob);
        button = findViewById(R.id.save);

        // Initialize Database
        db = new Database(this, null, null, 1);

        // Button Click Listener
        button.setOnClickListener(v -> {
            String userName = name.getText().toString();
            String userAge = age.getText().toString();
            String userIndex = index_num.getText().toString();
            String userProgramme = programme.getText().toString();
            String userDob = dob.getText().toString();

            if (!userName.isEmpty() && !userAge.isEmpty() && !userIndex.isEmpty()) {
                db.insert_data(userName, userAge, userIndex, userProgramme, userDob);
                Log.d("DATABASE", "Student added.");
                Intent intent = new Intent(MainActivity.this, AllData.class);
                startActivity(intent);
            } else {
                Log.d("DATABASE", "Please fill in all fields.");
            }
        });
    }
}
