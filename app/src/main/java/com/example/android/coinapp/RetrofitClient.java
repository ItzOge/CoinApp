package com.example.android.coinapp;

/**
 * Created by okwuchukwu on 2/26/2020.
 */
import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private Context context;

    // Define the base URL
    private static final String BASE_URL = "https://api.coinmarketcap.com/";

    RetrofitClient(Context context) {
        this.context = context;
    }

    // Create the Retrofit instance
    Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    // Add the converter
                    .addConverterFactory(GsonConverterFactory.create())

                    .client(getHttpClientBuilder().build())

                    // Build the Retrofit instance
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient.Builder getHttpClientBuilder() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Create cache
        long cacheSize = 5 * 1024 * 1024;
        Cache myCache = new Cache(context.getCacheDir(), cacheSize);

        // Add cache to client
        builder.cache(myCache);

        builder.addInterceptor(new CacheInterceptor(context));

        return builder;

    }

}