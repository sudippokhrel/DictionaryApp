package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView tvWord;
    RecyclerView rvPhonetics ,rvMeanings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        tvWord = findViewById(R.id.tvWord);
        rvPhonetics = findViewById(R.id.rvPhonetics);
        rvMeanings = findViewById(R.id.rvMeanings);

    }
}