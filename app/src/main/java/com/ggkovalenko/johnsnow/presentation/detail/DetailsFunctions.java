package com.ggkovalenko.johnsnow.presentation.detail;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.data.model.CharacterDetails;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DetailsFunctions {

    @NonNull
    private static final Pattern PATTERN_CHARACTER_URL = Pattern.compile("https://anapioficeandfire\\.com/api/characters/([0-9]+)");

    static int parseCharacterId(@NonNull final String characterUrl) {
        final Matcher matcher = PATTERN_CHARACTER_URL.matcher(characterUrl);
        if (matcher.matches()) {
            final String id = matcher.group(1);
            return Integer.parseInt(id);
        } else {
            throw new IllegalStateException("Wrong character url format");
        }
    }

    @NonNull
    static String resolveTitle(@NonNull final CharacterDetails character) {
        final String name = character.getName();
        if (!name.isEmpty()) {
            return name;
        }
        final List<String> aliases = character.getAliases();
        if (!aliases.isEmpty()) {
            return aliases.get(0);
        }
        return "";
    }

    private DetailsFunctions() {
        // no instances
    }

}
