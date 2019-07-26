package com.ggkovalenko.johnsnow.ui.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.presentation.detail.Detail;

import java.util.ArrayList;
import java.util.List;

final class DetailsAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    @NonNull
    private final List<Detail> items = new ArrayList<>();

    DetailsAdapter() {
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return DetailViewHolder.inflate(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        final Detail detail = items.get(position);
        holder.bind(detail);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swap(@NonNull final List<Detail> items) {
        this.items.clear();
        this.items.addAll(items);
    }

}
