package com.example.newsapp.network;

import com.example.newsapp.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    //for all news
    @GET("top-headlines")
    Single<NewsResponse> getNews(@Query("country") String country,
                                 @Query("pageSize") int pageSize,
                                 @Query("apiKey") String apiKey);

//for category news
    @GET("top-headlines")
    Single<NewsResponse> getCategoryNews(@Query("country") String country,
                                       @Query("category") String category,
                                       @Query("pageSize") int pageSize,
                                       @Query("apiKey") String apiKey);
}
