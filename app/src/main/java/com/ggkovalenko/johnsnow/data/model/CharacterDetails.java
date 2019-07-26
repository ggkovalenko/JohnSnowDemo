package com.ggkovalenko.johnsnow.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class CharacterDetails {

    @NonNull
    @SerializedName("name")
    private final String name;

    @NonNull
    @SerializedName("gender")
    private final String gender;

    @NonNull
    @SerializedName("culture")
    private final String culture;

    @NonNull
    @SerializedName("died")
    private final String died;

    @NonNull
    @SerializedName("titles")
    private final List<String> titles;

    @NonNull
    @SerializedName("aliases")
    private final List<String> aliases;

    @NonNull
    @SerializedName("tvSeries")
    private final List<String> tvSeries;

    @NonNull
    @SerializedName("playedBy")
    private final List<String> playedBy;

    public CharacterDetails(@NonNull final String name,
                            @NonNull final String gender,
                            @NonNull final String culture,
                            @NonNull final String died,
                            @NonNull final List<String> titles,
                            @NonNull final List<String> aliases,
                            @NonNull final String father,
                            @NonNull final String mother,
                            @NonNull final List<String> tvSeries,
                            @NonNull final List<String> playedBy) {
        this.name = name;
        this.gender = gender;
        this.culture = culture;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.tvSeries = tvSeries;
        this.playedBy = playedBy;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    @NonNull
    public String getCulture() {
        return culture;
    }

    @NonNull
    public String getDied() {
        return died;
    }

    @NonNull
    public List<String> getTitles() {
        return titles;
    }

    @NonNull
    public List<String> getAliases() {
        return aliases;
    }

    @NonNull
    public List<String> getTvSeries() {
        return tvSeries;
    }

    @NonNull
    public List<String> getPlayedBy() {
        return playedBy;
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;

        } else if (obj == null || getClass() != obj.getClass()) {
            return false;

        } else {
            final CharacterDetails that = (CharacterDetails) obj;
            return name.equals(that.name) &&
                    gender.equals(that.gender) &&
                    culture.equals(that.culture) &&
                    died.equals(that.died) &&
                    titles.equals(that.titles) &&
                    aliases.equals(that.aliases) &&
                    tvSeries.equals(that.tvSeries) &&
                    playedBy.equals(that.playedBy);
        }
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + culture.hashCode();
        result = 31 * result + died.hashCode();
        result = 31 * result + titles.hashCode();
        result = 31 * result + aliases.hashCode();
        result = 31 * result + tvSeries.hashCode();
        result = 31 * result + playedBy.hashCode();
        return result;
    }

}

/*

   "gender":"Male",
   "culture":"Westeros",
   "born":"In 260 AC or before, at King's Landing",
   "died":"",
   "titles":[
      "Ser",
      "Lord of the Rainwood",
      "Admiral of the Narrow Sea",
      "Hand of the King"
   ],
   "aliases":[
      "Onion Knight",
      "Davos Shorthand",
      "Ser Onions",
      "Onion Lord",
      "Smuggler"
   ],
   "father":"",
   "mother":"",
   "spouse":"https://www.anapioficeandfire.com/api/characters/1676",
   "allegiances":[
      "https://www.anapioficeandfire.com/api/houses/15",
      "https://www.anapioficeandfire.com/api/houses/340"
   ],
   "books":[
      "https://www.anapioficeandfire.com/api/books/5"
   ],
   "povBooks":[
      "https://www.anapioficeandfire.com/api/books/2",
      "https://www.anapioficeandfire.com/api/books/3",
      "https://www.anapioficeandfire.com/api/books/8"
   ],
   "tvSeries":[
      "Season 2",
      "Season 3",
      "Season 4",
      "Season 5",
      "Season 6"
   ],
   "playedBy":[
      "Liam Cunningham"
   ]


 */
