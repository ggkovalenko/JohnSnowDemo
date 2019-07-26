package com.ggkovalenko.johnsnow.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.R;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;

final class CharacterViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final CharacterListAdapter.OnItemClickListener itemClickListener;
    @NonNull
    private final TextView titleView;

    static CharacterViewHolder inflate(@NonNull final LayoutInflater inflater,
                                       @NonNull final ViewGroup parent,
                                       @NonNull final CharacterListAdapter.OnItemClickListener itemClickListener) {
        final View view = inflater.inflate(R.layout.list_item_character, parent, false);
        return new CharacterViewHolder(view, itemClickListener);
    }

    private CharacterViewHolder(@NonNull final View view,
                                @NonNull final CharacterListAdapter.OnItemClickListener itemClickListener) {
        super(view);
        this.titleView = view.findViewById(R.id.title);
        this.itemClickListener = itemClickListener;
    }

    void bind(@NonNull final CharacterShort character) {
        titleView.setText(character.getName());
        itemView.setOnClickListener(view -> itemClickListener.onItemClick(character));
    }

}
