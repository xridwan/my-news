package com.xridwan.mynewsapp.network;

import com.xridwan.mynewsapp.models.GetSources;
import com.xridwan.mynewsapp.models.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("sources")
    Call<GetSources> getSources(
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey
    );
}
