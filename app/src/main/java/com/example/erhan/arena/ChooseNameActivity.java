package com.example.erhan.arena;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_name);

        final Button button = findViewById(R.id.nameConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText inputTxt = findViewById(R.id.nameEdit);
                MainActivity.fighter.name = inputTxt.getText().toString();
                MainActivity.fighter.setStartingStats();
                Intent intent = new Intent(v.getContext(), IdleActivity.class);
                startActivity(intent);
            }
        });
    }
}
