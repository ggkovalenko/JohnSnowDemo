package com.ggkovalenko.johnsnow.presentation.detail;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.arch.AbstractPresenter;
import com.ggkovalenko.johnsnow.arch.Result;
import com.ggkovalenko.johnsnow.data.ExternalDatasource;
import com.ggkovalenko.johnsnow.data.model.CharacterDetails;

import java.util.ArrayList;
import java.util.List;

public final class CharacterPresenter extends AbstractPresenter<CharacterView> {

    @NonNull
    private final ExternalDatasource datasource;
    @NonNull
    private final String characterUrl;

    private boolean isLoading;
    @NonNull
    private String title = "";
    @NonNull
    private final List<Detail> details = new ArrayList<>();

    public CharacterPresenter(@NonNull final CharacterView view,
                              @NonNull final ExternalDatasource datasource,
                              @NonNull final String characterUrl) {
        super(view);
        this.datasource = datasource;
        this.characterUrl = characterUrl;
    }

    @Override
    protected void create() {
        super.create();

        final int id = DetailsFunctions.parseCharacterId(characterUrl);
        invoke(() -> datasource.getCharacterDetails(id), this::onCharacterLoaded);
        isLoading = true;
    }

    @Override
    protected void resume() {
        super.resume();
        getView().showLoading(isLoading);
        getView().showTitle(title);
        getView().showDetails(details);
    }

    private void onCharacterLoaded(@NonNull final Result<CharacterDetails> result) {
        if (result.isSuccessful()) {
            final CharacterDetails character = result.getValue();
            title = DetailsFunctions.resolveTitle(character);
            details.clear();
            details.addAll(DetailsFactory.createDetails(character));

            if (isResumed()) {
                getView().showTitle(title);
                getView().showDetails(details);
            }
        } else {
            if (isResumed()) {
                getView().showError(result.getThrowable());
            }
        }

        isLoading = false;
        if (isResumed()) {
            getView().showLoading(isLoading);
        }
    }

}
