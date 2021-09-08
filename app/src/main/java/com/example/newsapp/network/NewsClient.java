package com.example.newsapp.network;

import com.example.newsapp.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClient {
   private static final String BASE_URL = "https://newsapi.org/v2/";
   private ApiInterface apiInterface;
   private static NewsClient Instance;


    public NewsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static NewsClient getInstance() {
        if(Instance==null){
            Instance = new NewsClient();
        }
        return Instance;
    }
    public Single<NewsResponse> getNews(String country,int pageSize,String apiKey){
        return apiInterface.getNews(country,pageSize,apiKey);
    }

    public Single<NewsResponse> getCategoryNews(String country,String category,int pageSize,String apiKey){
        return apiInterface.getCategoryNews(country,category,pageSize,apiKey);
    }

}
