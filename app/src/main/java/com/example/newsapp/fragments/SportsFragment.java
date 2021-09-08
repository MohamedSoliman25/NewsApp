package com.example.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.constants.Constants;
import com.example.newsapp.model.NewsModel;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SportsFragment extends Fragment {

    String api = "c644e443863a4b748bdec3baa0b730bf";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter adapter;
    private RecyclerView recyclerViewSports;

    private String category = "sports";
    NewsViewModel newsViewModel;
    ProgressBar progressBar;
    int count=9000;
    Timer timer;


    public SportsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        recyclerViewSports = view.findViewById(R.id.recyclerviewsports);
        progressBar=view.findViewById(R.id.progress_bar);
        newsModelArrayList = new ArrayList<>();
        recyclerViewSports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(), newsModelArrayList);

      //  recyclerViewSports.setVisibility(View.GONE);
    //    getProgressTimer();


           recyclerViewSports.setAdapter(adapter);





        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        newsViewModel.getSportsNews(Constants.country,category,100,api);
        newsViewModel.getSportMutableLiveData().observe(getActivity(), new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {

                      adapter.setmList(newsModels);
                    adapter.notifyDataSetChanged();


            }
        });

       // findNews();
        return view;
    }

//    private void findNews() {
//        NewsClient.getAPiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                if (response.isSuccessful()){
////                    newsModelArrayList.addAll(response.body().getArticles());
//                    adapter.setmList(response.body().getArticles());
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsResponse> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
@Override
public void onStop() {
    super.onStop();
   // newsViewModel.compositeDisposable.clear();

    //newsViewModel.getSportMutableLiveData().removeObservers(getViewLifecycleOwner());
}

//public void getProgressTimer(){
//        timer = new Timer();
//    TimerTask timerTask = new TimerTask() {
//        @Override
//        public void run() {
//            count++;
//            progressBar.setProgress(count);
//            if(count==15){
//                timer.cancel();
//            }
//        }
//    };
//    timer.schedule(timerTask,0,15);
//}

    public void getProgressTimer(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(count==4000){
                    progressBar.setVisibility(View.GONE);
                    recyclerViewSports.setAdapter(adapter);
                }

            }
        },count);
    }
}