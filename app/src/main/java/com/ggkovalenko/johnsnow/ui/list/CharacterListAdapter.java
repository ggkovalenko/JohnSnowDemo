package com.ggkovalenko.johnsnow.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.data.model.CharacterShort;

import java.util.ArrayList;
import java.util.List;

final class CharacterListAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(@NonNull CharacterShort character);
    }

    @NonNull private final OnItemClickListener itemClickListener;
    @NonNull private final List<CharacterShort> items = new ArrayList<>();

    CharacterListAdapter(@NonNull final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return CharacterViewHolder.inflate(inflater, parent, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        final CharacterShort character = items.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swap(@NonNull final List<CharacterShort> items) {
        this.items.clear();
        this.items.addAll(items);
    }

}
