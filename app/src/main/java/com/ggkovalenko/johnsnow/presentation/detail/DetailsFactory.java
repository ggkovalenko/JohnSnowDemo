package com.ggkovalenko.johnsnow.presentation.detail;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.ggkovalenko.johnsnow.R;
import com.ggkovalenko.johnsnow.data.model.CharacterDetails;

import java.util.ArrayList;
import java.util.List;

final class DetailsFactory {

    @NonNull
    private static final String DELIMITER = ", ";

    @NonNull
    static List<Detail> createDetails(@NonNull final CharacterDetails character) {
        final List<Detail> details = new ArrayList<>();
        addDetail(details, R.string.character_name, character.getName());
        addDetail(details, R.string.character_gender, character.getGender());
        addDetail(details, R.string.character_culture, character.getCulture());
        addDetail(details, R.string.character_died, character.getDied());
        addDetail(details, R.string.character_titles, character.getTitles());
        addDetail(details, R.string.character_aliases, character.getAliases());
        addDetail(details, R.string.character_tv_series, character.getTvSeries());
        addDetail(details, R.string.character_played_by, character.getPlayedBy());
        return details;
    }

    private static void addDetail(@NonNull final List<Detail> details,
                                  @StringRes final int title,
                                  @NonNull final String value) {
        if (!value.isEmpty()) {
            details.add(new Detail(title, value));
        }
    }

    private static void addDetail(@NonNull final List<Detail> details,
                                  @StringRes final int title,
                                  @NonNull final List<String> value) {
        if (!value.isEmpty() && !TextUtils.isEmpty(value.get(0))) {
            final String valueText = TextUtils.join(DELIMITER, value);
            details.add(new Detail(title, valueText));
        }
    }

    private DetailsFactory() {
        // no instances
    }

}
