package com.example.mylistadapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class MyDiffUtil extends DiffUtil.ItemCallback<News> {
    @Override
    public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return oldItem.getIdModel() == newItem.getIdModel();
    }

    @Override
    public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return oldItem.getTitle().equals(newItem.getTitle()) &&
                oldItem.getDescription().equals(newItem.getDescription()) &&
                oldItem.getUrl().equals(newItem.getUrl());
    }
}
