package com.example.todo;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;


public class AddTaskActivity extends AppCompatActivity {
    EditText input;
    TaskDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dbHelper = new TaskDBHelper(this);

        input = findViewById(R.id.taskInput);
        findViewById(R.id.saveBtn).setOnClickListener(v -> {
            String text = input.getText().toString();
            if (!text.isEmpty()) {
                dbHelper.addTask(text);
                finish();
            }
        });
    }
}