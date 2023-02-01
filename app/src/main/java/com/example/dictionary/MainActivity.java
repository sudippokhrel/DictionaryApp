package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Adapters.MeaningAdapter;
import com.example.dictionary.Adapters.PhoneticsAdapter;
import com.example.dictionary.Models.APIResponse;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView tvWord;
    RecyclerView rvPhonetics ,rvMeanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        tvWord = findViewById(R.id.tvWord);
        rvPhonetics = findViewById(R.id.rvPhonetics);
        rvMeanings = findViewById(R.id.rvMeanings);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading");
        progressDialog.show();

//        to display hello world searched by default
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "hello");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                progressDialog.setTitle("Fetching response for" + s);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener,s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse==null){
                Toast.makeText(MainActivity.this, "No Data found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message , Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(APIResponse apiResponse) {
        tvWord.setText("Word: " + apiResponse.getWord());
        rvPhonetics.setHasFixedSize(true);
        rvPhonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        rvPhonetics.setAdapter(phoneticsAdapter);

        rvMeanings.setHasFixedSize(true);
        rvMeanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        rvMeanings.setAdapter(meaningAdapter);
    }
}