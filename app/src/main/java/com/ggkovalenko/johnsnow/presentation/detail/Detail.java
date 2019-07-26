package com.ggkovalenko.johnsnow.presentation.detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

public final class Detail {

    @StringRes
    private final int title;
    @NonNull
    private final String value;

    public Detail(final int title,
                  @NonNull final String value) {
        this.title = title;
        this.value = value;
    }

    @StringRes
    public int getTitle() {
        return title;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;

        } else if (obj == null || getClass() != obj.getClass()) {
            return false;

        } else {
            final Detail that = (Detail) obj;
            return title == that.title &&
                    value.equals(that.value);
        }
    }

    @Override
    public int hashCode() {
        int result = title;
        result = 31 * result + value.hashCode();
        return result;
    }

}
