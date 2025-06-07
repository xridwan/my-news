package com.xridwan.mynewsapp.utils;

import com.xridwan.mynewsapp.network.ApiClient;
import com.xridwan.mynewsapp.network.ApiInterface;

public class Config {
    public static final String API_KEY = "";
    public static final String BASE_URL = "https://newsapi.org/v2/";

    public static ApiInterface apiInterface(){
        return ApiClient.getRetrofit().create(ApiInterface.class);
    }
}
