package com.example.android.coinapp;

import android.content.Context;

import com.example.android.coinapp.util.NetworkUtils;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

class CacheInterceptor implements Interceptor {

    private Context context;

    CacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (NetworkUtils.isConnectedToNetwork(context))
            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
        else
            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

        return chain.proceed(request);
    }
}
