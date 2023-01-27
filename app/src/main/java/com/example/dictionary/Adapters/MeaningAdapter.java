package com.example.dictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Models.Meanings;
import com.example.dictionary.R;
import com.example.dictionary.ViewHolders.MeaningsViewHolder;
import com.example.dictionary.ViewHolders.PhoneticViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    protected List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meaning_list_items,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.tvPartsOfSpeech.setText("Parts of Speech: "+meaningsList.get(position).getPartOfSpeech());
        holder.rvDefinitions.setHasFixedSize(true);
        holder.rvDefinitions.setLayoutManager(new GridLayoutManager(context, 1));
        DefinitionAdapter definitionAdapter =new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.rvDefinitions.setAdapter(definitionAdapter);

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
