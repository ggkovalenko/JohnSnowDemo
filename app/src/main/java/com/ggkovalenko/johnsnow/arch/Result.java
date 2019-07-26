package com.ggkovalenko.johnsnow.arch;

import androidx.annotation.Nullable;

public final class Result<R> {

    @Nullable
    private final R value;
    @Nullable
    private final Throwable throwable;

    static <R> Result<R> success(R value) {
        return new Result<>(value, null);
    }

    static <R> Result<R> error(Throwable throwable) {
        return new Result<>(null, throwable);
    }

    private Result(@Nullable final R value, @Nullable final Throwable throwable) {
        this.value = value;
        this.throwable = throwable;
    }

    @Nullable
    public R getValue() {
        return value;
    }

    @Nullable
    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isSuccessful() {
        return value != null;
    }

}
