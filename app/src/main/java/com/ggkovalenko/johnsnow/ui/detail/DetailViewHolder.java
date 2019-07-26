package com.ggkovalenko.johnsnow.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.R;
import com.ggkovalenko.johnsnow.presentation.detail.Detail;

final class DetailViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final TextView titleView;
    @NonNull
    private final TextView valueView;

    static DetailViewHolder inflate(@NonNull final LayoutInflater inflater,
                                    @NonNull final ViewGroup parent) {
        final View view = inflater.inflate(R.layout.list_item_detail, parent, false);
        return new DetailViewHolder(view);
    }

    private DetailViewHolder(@NonNull final View view) {
        super(view);
        titleView = view.findViewById(R.id.title);
        valueView = view.findViewById(R.id.value);
    }

    void bind(@NonNull final Detail detail) {
        titleView.setText(detail.getTitle());
        valueView.setText(detail.getValue());
    }

}
