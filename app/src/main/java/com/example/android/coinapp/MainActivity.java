package com.example.android.coinapp;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.coinapp.util.NetworkUtils.isConnectedToNetwork;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private CoinAdapter coinAdapter;
    private RecyclerView myRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List list = new ArrayList<RetrofitUsers>();

    RetrieveData service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        loadData();
    }

    private void setup() {

        myRecyclerView = findViewById(R.id.myRecyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshlayout);

        swipeRefreshLayout.setOnRefreshListener(this);

        coinAdapter = new CoinAdapter(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(coinAdapter);

        service = new RetrofitClient(this).getRetrofitInstance().create(RetrieveData.class);
    }

    private void loadData() {

        list.clear();
        coinAdapter.notifyDataSetChanged();

        setRefreshing(true);

        // Execute the request asynchronously
        service.getAllUsers().enqueue(new Callback<List<RetrofitUsers>>() {

            @Override
            public void onResponse(@NonNull Call<List<RetrofitUsers>> call, @NonNull Response<List<RetrofitUsers>> response) {
                list.addAll(response.body());
                coinAdapter.notifyDataSetChanged();
                setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<RetrofitUsers>> call, @NonNull Throwable throwable) {
                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
                setRefreshing(false);
            }
        });

        if (!isConnectedToNetwork(this)) {
            Toast.makeText(MainActivity.this, "Network unavailable!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRefreshing(final Boolean refreshing) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        }, 100);
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
