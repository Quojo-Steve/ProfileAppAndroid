package com.example.profileapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Edit extends AppCompatActivity {
    EditText name, age, index, programme, dob;
    Button save;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name1);
        age = findViewById(R.id.age1);
        index = findViewById(R.id.index_num1);
        programme = findViewById(R.id.programme1);
        dob = findViewById(R.id.dob1);
        save = findViewById(R.id.edit);

        db = new Database(this, null, null, 1);

        // Get data from intent
        String passedName = getIntent().getStringExtra("name");
        String passedAge = getIntent().getStringExtra("age");
        String passedIndex = getIntent().getStringExtra("index_number");
        String passedProgramme = getIntent().getStringExtra("programme");
        String passedDob = getIntent().getStringExtra("dob");

        // Populate input fields
        name.setText(passedName);
        age.setText(passedAge);
        index.setText(passedIndex);
        programme.setText(passedProgramme);
        dob.setText(passedDob);

        index.setEnabled(false); // disable editing index number

        save.setOnClickListener(v -> {
            String newName = name.getText().toString();
            String newAge = age.getText().toString();
            String newProgramme = programme.getText().toString();
            String newDob = dob.getText().toString();

            if (!newName.isEmpty() && !newAge.isEmpty() && !newProgramme.isEmpty() && !newDob.isEmpty()) {
                db.updateUser(passedIndex, newName, newAge, newProgramme, newDob);
                Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
