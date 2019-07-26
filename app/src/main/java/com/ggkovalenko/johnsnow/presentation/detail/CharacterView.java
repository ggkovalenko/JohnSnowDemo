package com.ggkovalenko.johnsnow.presentation.detail;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.arch.AbstractView;

import java.util.List;

public interface CharacterView extends AbstractView {

    void showLoading(boolean isLoading);

    void showTitle(@NonNull String title);

    void showDetails(@NonNull List<Detail> details);

    void showError(@NonNull Throwable throwable);

}
