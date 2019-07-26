package com.ggkovalenko.johnsnow.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public final class PageScrollListener extends RecyclerView.OnScrollListener {

    public interface Callbacks {
        void onNextPageScrolled();
    }

    private final int offset;
    @NonNull
    private final Callbacks callbacks;

    public PageScrollListener(final int offset,
                              @NonNull final Callbacks callbacks) {
        this.offset = offset;
        this.callbacks = callbacks;
    }

    @Override
    public void onScrolled(@NonNull final RecyclerView recyclerView,
                           final int dx,
                           final int dy) {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        final int lastVisibleItemPosition = getLastVisibleItemPosition(layoutManager);
        final int updatePosition = adapter.getItemCount() - 1 - offset;
        if (lastVisibleItemPosition >= updatePosition) {
            callbacks.onNextPageScrolled();
            recyclerView.removeOnScrollListener(this);
        }
    }

    private int getLastVisibleItemPosition(@NonNull final RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        throw new IllegalStateException("Unexpected layout manager type");
    }

}
