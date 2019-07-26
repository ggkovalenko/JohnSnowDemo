package com.ggkovalenko.johnsnow.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public final class PagedList<T> {

    private final int page;
    private final int pageSize;
    @NonNull
    private final List<T> items;

    public PagedList(final int page,
                     final int pageSize,
                     @NonNull final List<T> items) {
        this.page = page;
        this.pageSize = pageSize;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    @NonNull
    public List<T> getItems() {
        return items;
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;

        } else if (obj == null || getClass() != obj.getClass()) {
            return false;

        } else {
            final PagedList that = (PagedList) obj;
            return page == that.page &&
                    pageSize == that.pageSize &&
                    items.equals(that.items);
        }
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + pageSize;
        result = 31 * result + items.hashCode();
        return result;
    }

}
