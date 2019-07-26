package com.ggkovalenko.johnsnow.data.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class CharacterShort {

    @SerializedName("url")
    private final String url;

    @SerializedName("name")
    private final String name;

    @SerializedName("aliases")
    private final List<String> aliases;

    public CharacterShort(final String url, final String name, final List<String> aliases) {
        this.url = url;
        this.name = name;
        this.aliases = aliases;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getName() {
        if (TextUtils.isEmpty(name)) {
            if (aliases.isEmpty()) {
                return "";
            } else {
                return aliases.get(0);
            }
        } else {
            return name;
        }
    }

}
