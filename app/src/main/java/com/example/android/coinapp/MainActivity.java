package com.example.android.coinapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoinAdapter CoinAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a handler for the RetrofitInstance interface
        RetrieveData service = new RetrofitClient(this).getRetrofitInstance().create(RetrieveData.class);

        Call<List<RetrofitUsers>> call = service.getAllUsers();

        // Execute the request asynchronously
        call.enqueue(new Callback<List<RetrofitUsers>>() {

            @Override
            public void onResponse(@NonNull Call<List<RetrofitUsers>> call, @NonNull Response<List<RetrofitUsers>> response) {
                loadDataList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<RetrofitUsers>> call, @NonNull Throwable throwable) {
                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Display the retrieved data as a list
    private void loadDataList(List<RetrofitUsers> usersList) {

        // Get a reference to the RecyclerView
        myRecyclerView = findViewById(R.id.myRecyclerView);
        CoinAdapter = new CoinAdapter(usersList);

        // Use a LinearLayoutManager with default vertical orientation
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

        // Set the Adapter to the RecyclerView
        myRecyclerView.setAdapter(CoinAdapter);
    }

}
