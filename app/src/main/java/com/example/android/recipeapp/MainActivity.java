package com.example.android.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_activity);

    }
}
