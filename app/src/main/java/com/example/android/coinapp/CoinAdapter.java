package com.example.android.coinapp;

/**
 * Created by okwuchukwu on 2/26/2020.
 */

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.support.v7.widget.RecyclerView;
        import android.widget.TextView;

        import java.util.List;

//Extend the RecyclerView.Adapter class//

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CustomViewHolder> {

    private List<RetrofitUsers> dataList;

    public CoinAdapter(List<RetrofitUsers> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

//Get a reference to the Views in our layout//

        public final View myView;

        TextView textUser, textPrice, textPercentage, textSymbol;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textUser = myView.findViewById(R.id.user);
            textPrice = myView.findViewById(R.id.price);
            textPercentage = myView.findViewById(R.id.percentage);
            textSymbol = myView.findViewById(R.id.symbol);

        }
    }

    @Override

//Construct a RecyclerView.ViewHolder//

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

//Set the data//

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(" Name: " + dataList.get(position).getUser());
        holder.textPrice.setText("Price: "+ dataList.get(position).getPriceUsd());
        holder.textPercentage.setText("Percentage change: "+ dataList.get(position).getPercentChange());
        holder.textSymbol.setText("Symbol: "+ dataList.get(position).getSymbol());


    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
