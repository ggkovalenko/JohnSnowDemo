package com.ggkovalenko.johnsnow.presentation.list;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.arch.AbstractPresenter;
import com.ggkovalenko.johnsnow.arch.Result;
import com.ggkovalenko.johnsnow.data.ExternalDatasource;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;
import com.ggkovalenko.johnsnow.data.model.PagedList;

import java.util.ArrayList;
import java.util.List;

public final class CharacterListPresenter extends AbstractPresenter<CharacterListView> {

    private static final int PAGE_SIZE = 10;

    @NonNull private final ExternalDatasource datasource;
    @NonNull private final List<CharacterShort> characters = new ArrayList<>();
    private int nextPage = 0;

    public CharacterListPresenter(@NonNull final CharacterListView view,
                                  @NonNull final ExternalDatasource datasource) {
        super(view);
        this.datasource = datasource;
    }

    @Override
    protected void create() {
        super.create();
        loadNextPage();
    }

    @Override
    protected void resume() {
        super.resume();
        getView().showCharacters(characters);
    }

    private void onCharactersLoaded(@NonNull final Result<PagedList<CharacterShort>> result) {
        if (result.isSuccessful()) {
            final PagedList<CharacterShort> pagedList = result.getValue();
            if (pagedList.getPage() == 0) {
                characters.clear();
                nextPage = 0;
            }
            characters.addAll(pagedList.getItems());
            nextPage++;

            if (isResumed()) {
                getView().showCharacters(characters);
            }
        } else {
            if (isResumed()) {
                getView().showCharactersError(result.getThrowable());
            }
        }
    }

    private void loadNextPage() {
        invoke(() -> datasource.getCharacters(nextPage, PAGE_SIZE), this::onCharactersLoaded);
    }

    public void onNextPageScrolled() {
        loadNextPage();
    }

    public void onItemClick(@NonNull final CharacterShort character) {
        getView().openCharacter(character.getUrl());
    }

}
