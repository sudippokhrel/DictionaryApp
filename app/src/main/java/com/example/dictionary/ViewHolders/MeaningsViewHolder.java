package com.example.dictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    TextView tvPartsOfSpeech,rvDefinitions;
    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvPartsOfSpeech =itemView.findViewById(R.id.tvPartsOfSpeech);
        rvDefinitions =itemView.findViewById(R.id.rvDefinitions);


    }
}
