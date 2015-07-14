package io.github.gothwski.tarantulafm.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.gothwski.tarantulafm.io.deserializer.MyHypedArtistDeserializer;
import io.github.gothwski.tarantulafm.io.model.HypedArtistsResponse;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by ariana gothwski on 8/07/15.
 */
public class LastFmAdapter {

    private static LastFmApiService API_SERVICE;

    public static LastFmApiService getApiService () {
        if (API_SERVICE == null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(ApiConstants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setConverter(buildLastFmApiConverter())
                    .build();

            API_SERVICE = adapter.create(LastFmApiService.class);
        }

        return API_SERVICE;
    }

    private static GsonConverter buildLastFmApiConverter() {
        Gson gsonConf = new GsonBuilder()
                .registerTypeAdapter(HypedArtistsResponse.class, new MyHypedArtistDeserializer())
                .create();

        return new GsonConverter(gsonConf);
    }
}
