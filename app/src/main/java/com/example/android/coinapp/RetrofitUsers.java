package com.example.android.coinapp;

/**
 * Created by okwuchukwu on 2/26/2020.
 */

import com.google.gson.annotations.SerializedName;

public class RetrofitUsers {

//Give the field a custom name//

    @SerializedName("name")
    private String name;

    @SerializedName("price_usd")
    private String price_usd;

    @SerializedName("percent_change_1h")
    private String percent_change_1h;

    @SerializedName("symbol")
    private String symbol;


    public RetrofitUsers(String name, String price_usd, String percent_change_2h, String symbol) {
        this.name = name;
        this.price_usd = price_usd;
        this.percent_change_1h = percent_change_2h;
        this.symbol = symbol;


    }

//Retrieve the data using setter/getter methods//

    public String getUser() {
        return name;
    }

    public void setUser(String name) {
        this.name = name;
    }


    public String getPriceUsd() {
        return price_usd;
    }

    public void setPriceUsd(String price_usd) {
        this.name = price_usd;
    }


    public String getPercentChange() {
        return percent_change_1h;
    }

    public void setPercentChange(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



}
