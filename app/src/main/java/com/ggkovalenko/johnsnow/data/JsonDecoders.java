package com.ggkovalenko.johnsnow.data;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.data.model.CharacterDetails;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

final class JsonDecoders {

    @NonNull
    private final Gson gson = new Gson();

    JsonDecoders() {
    }

    @NonNull
    List<CharacterShort> characters(@NonNull final Reader reader) {
        final Type characterListType = new TypeToken<List<CharacterShort>>() {
        }.getType();
        return gson.fromJson(reader, characterListType);
    }

    @NonNull
    CharacterDetails characterDetails(@NonNull final Reader reader) {
        return gson.fromJson(reader, CharacterDetails.class);
    }

}
