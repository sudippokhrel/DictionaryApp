package com.example.dictionary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Adapters.MeaningAdapter;
import com.example.dictionary.Adapters.PhoneticsAdapter;
import com.example.dictionary.Models.APIResponse;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView tvWord;
    RecyclerView rvPhonetics,rvMeanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;
    //Adapter phoneticsAdapter,meaningAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view=findViewById(R.id.search_view);
        tvWord=findViewById(R.id.tvWord);
        rvPhonetics=findViewById(R.id.rvPhonetics);
        //  Recyclerview_phonetics.setAdapter(phoneticsAdapter);

        rvMeanings=findViewById(R.id.rvMeanings);
        // rvMeanings.setAdapter(meaningAdapter);
        progressDialog=new ProgressDialog(this);
        //loads this query by default  every time the application is run
        progressDialog.setTitle("Loading data...");
        progressDialog.show();
        RequestManager manager=new RequestManager(MainActivity.this);
        manager.getWordMeaning(listner,"hello");

        //handles the query submitted by the user
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for: "+ query);
                progressDialog.show();

                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getWordMeaning(listner,query);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    private final OnFetchDataListener listner=new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse==null){
                Toast.makeText(MainActivity.this, "Data not found!!", Toast.LENGTH_SHORT).show();
                return;

            }
            showData(apiResponse);

        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();


        }
    };

    private void showData(APIResponse apiResponse) {
        tvWord.setText("Word:" + apiResponse.getWord());
        rvPhonetics.setHasFixedSize(true);

        rvPhonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter=new PhoneticsAdapter(this,apiResponse.getPhonetics());
        rvPhonetics.setAdapter(phoneticsAdapter);



        rvMeanings.setHasFixedSize(true);
        rvMeanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter=new MeaningAdapter(this,apiResponse.getMeanings());
        rvMeanings.setAdapter(meaningAdapter);


    }
}