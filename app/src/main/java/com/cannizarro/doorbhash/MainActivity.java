package com.cannizarro.doorbhash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button add, join;
    EditText roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.create);
        join = findViewById(R.id.join);
        roomName = findViewById(R.id.roomName);

    }

    public void createRoom(){

    }

    public void joinRoom(){

    }
}
