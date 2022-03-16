package com.example.skannaus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSkanneri, btnHistoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
    }

    public void initViews() {
        btnSkanneri = findViewById(R.id.btnSkanneri);
        btnHistoria = findViewById(R.id.btnHistoria);
        btnSkanneri.setOnClickListener(this);
        btnHistoria.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSkanneri:
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                break;
            case R.id.btnHistoria:
                startActivity(new Intent(LaunchActivity.this, HistoriaActivity.class));
                break;
        }

    }
}