package com.example.newsapp.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.model.NewsModel;
import com.example.newsapp.model.NewsResponse;
import com.example.newsapp.network.NewsClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    MutableLiveData <ArrayList<NewsModel>> homeMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ArrayList<NewsModel>> sportsMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ArrayList<NewsModel>> healthMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ArrayList<NewsModel>> scienceMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ArrayList<NewsModel>> entertainmentMutableLiveData = new MutableLiveData<>();
    MutableLiveData <ArrayList<NewsModel>> technologyMutableLiveData = new MutableLiveData<>();


    //public CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "NewsViewModel";

    public LiveData<ArrayList<NewsModel>> getHomeMutableLiveData() {
        return homeMutableLiveData;
    }

    public LiveData<ArrayList<NewsModel>> getSportMutableLiveData() {
        return sportsMutableLiveData;
    }

    public LiveData<ArrayList<NewsModel>> getHealthMutableLiveData() {
        return healthMutableLiveData;
    }

    public LiveData<ArrayList<NewsModel>> getScienceMutableLiveData() {
        return scienceMutableLiveData;
    }
    public LiveData<ArrayList<NewsModel>> getEntertainmentMutableLiveData() {
        return entertainmentMutableLiveData;
    }
    public LiveData<ArrayList<NewsModel>> getTechnologyMutableLiveData() {
        return technologyMutableLiveData;
    }


    @SuppressLint("CheckResult")
    public void getNews(String country, int pageSize, String apiKey){
     NewsClient.getInstance().getNews(country,pageSize,apiKey)
                .subscribeOn( Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(r->homeMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getNews: "+e.getMessage()));

        //.subscribe(r-> Log.d(TAG, "getNews: "+r.getArticles().get(0).getDescription()),e-> Log.d(TAG, "getNews: "+e.getMessage()));

        //or can i use map for convert NewsResponse to NewsModel


    }

    @SuppressLint("CheckResult")
    public void getSportsNews(String country,String category, int pageSize, String apiKey){
         NewsClient.getInstance().getCategoryNews(country,category,pageSize,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        .subscribe(r->sportsMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getSportsNews: "+e.getMessage()));
      //  compositeDisposable.add(observable.subscribe(r->homeMutableLiveData.setValue(r.getArticles())));

        //or can i use map for convert NewsResponse to NewsModel


    }

    @SuppressLint("CheckResult")
    public void getHealthNews(String country,String category, int pageSize, String apiKey){
        NewsClient.getInstance().getCategoryNews(country,category,pageSize,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(r->healthMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getHealthNews: "+e.getMessage()));
        //  compositeDisposable.add(observable.subscribe(r->homeMutableLiveData.setValue(r.getArticles())));

        //or can i use map for convert NewsResponse to NewsModel


    }

    @SuppressLint("CheckResult")
    public void getScienceNews(String country,String category, int pageSize, String apiKey){
        NewsClient.getInstance().getCategoryNews(country,category,pageSize,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(r->scienceMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getScienceNews: "+e.getMessage()));
        //  compositeDisposable.add(observable.subscribe(r->homeMutableLiveData.setValue(r.getArticles())));

        //or can i use map for convert NewsResponse to NewsModel


    }

    @SuppressLint("CheckResult")
    public void getEntertainmentNews(String country,String category, int pageSize, String apiKey){
        NewsClient.getInstance().getCategoryNews(country,category,pageSize,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(r->entertainmentMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getEntertainmentNews: "+e.getMessage()));
        //  compositeDisposable.add(observable.subscribe(r->homeMutableLiveData.setValue(r.getArticles())));

        //or can i use map for convert NewsResponse to NewsModel


    }

    @SuppressLint("CheckResult")
    public void getTechnologyNews(String country,String category, int pageSize, String apiKey){
        NewsClient.getInstance().getCategoryNews(country,category,pageSize,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(r->technologyMutableLiveData.setValue(r.getArticles()),e-> Log.d(TAG, "getTechnologyNews: "+e.getMessage()));
        //  compositeDisposable.add(observable.subscribe(r->homeMutableLiveData.setValue(r.getArticles())));

        //or can i use map for convert NewsResponse to NewsModel


    }
}
