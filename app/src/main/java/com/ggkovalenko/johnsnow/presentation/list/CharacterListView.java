package com.ggkovalenko.johnsnow.presentation.list;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.arch.AbstractView;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;

import java.util.List;

public interface CharacterListView extends AbstractView {

    void showCharacters(@NonNull List<CharacterShort> characters);

    void showCharactersError(@NonNull Throwable throwable);

    void openCharacter(@NonNull String characterUrl);

}
