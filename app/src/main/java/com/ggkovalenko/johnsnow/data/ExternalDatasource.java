package com.ggkovalenko.johnsnow.data;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.data.model.CharacterDetails;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;
import com.ggkovalenko.johnsnow.data.model.PagedList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public final class ExternalDatasource {

    private interface ReaderDecoder<T> {
        @NonNull
        T decode(@NonNull Reader reader);
    }

    private static final String ENDPOINT = "https://anapioficeandfire.com/api/";
    private static final String METHOD_GET = "GET";
    private static final int TIMEOUT = 5000;

    private final JsonDecoders decoders = new JsonDecoders();

    public ExternalDatasource() {
    }

    public PagedList<CharacterShort> getCharacters(
            final int page,
            final int pageSize) throws IOException {
        final String uri = "characters?page=" + page + "&pageSize=" + pageSize;
        final List<CharacterShort> characters = performRequest(uri, decoders::characters);
        return new PagedList<>(page, pageSize, characters);
    }

    public CharacterDetails getCharacterDetails(final int characterId) throws IOException {
        final String uri = "characters/" + characterId;
        return performRequest(uri, decoders::characterDetails);
    }

    @NonNull
    private <R> R performRequest(@NonNull final String uri,
                                 @NonNull final ReaderDecoder<R> readerDecoder) throws IOException {
        Reader reader = null;
        HttpsURLConnection connection = null;
        try {
            final URL url = new URL(ENDPOINT + uri);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setRequestMethod(METHOD_GET);

            reader = new InputStreamReader(connection.getInputStream());
            return readerDecoder.decode(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
