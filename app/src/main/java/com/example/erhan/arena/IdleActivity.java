package com.example.erhan.arena;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IdleActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        final TextView nt =  findViewById(R.id.nameTitle);
        nt.setText(Fighter.name.toString());
    }
}


