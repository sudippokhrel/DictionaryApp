package com.example.dictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    TextView tvDefinitions;
    TextView tvExamples;
    TextView tvSynonyms;
    TextView tvAntonyms;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDefinitions = itemView.findViewById(R.id.tvDefinitions);
        tvExamples = itemView.findViewById(R.id.tvExamples);
        tvSynonyms = itemView.findViewById(R.id.tvSynonyms);
        tvAntonyms = itemView.findViewById(R.id.tvAntonyms);
    }
}
