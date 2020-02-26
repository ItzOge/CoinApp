package com.example.android.coinapp;

/**
 * Created by okwuchukwu on 2/26/2020.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrieveData {

//Specify the request type and pass the relative URL//

    @GET("/v1/ticker/?limit=50")

//Wrap the response in a Call object with the type of the expected result//

    Call<List<RetrofitUsers>> getAllUsers();
}
