package com.example.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.constants.Constants;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsModel;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.ArrayList;


public class TechnologyFragment extends Fragment {


    String api = "c644e443863a4b748bdec3baa0b730bf";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter adapter;
    private RecyclerView recyclerViewTechnology;

    private String category = "technology";
    NewsViewModel newsViewModel;


    public TechnologyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerViewTechnology = view.findViewById(R.id.recyclerviewtechnology);
        newsModelArrayList = new ArrayList<>();
        recyclerViewTechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(), newsModelArrayList);
        recyclerViewTechnology.setAdapter(adapter);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        newsViewModel.getTechnologyNews(Constants.country,category,100,api);
        newsViewModel.getTechnologyMutableLiveData().observe(getActivity(), new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                adapter.setmList(newsModels);
                adapter.notifyDataSetChanged();
            }
        });

  //      findNews();
        return view;

    }


//    private void findNews() {
//        NewsClient.getAPiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<NewsResponse>() {
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
      //  newsViewModel.compositeDisposable.clear();

//            newsViewModel.compositeDisposable.clear();
    //  newsViewModel.getCategoryMutableLiveData().removeObservers(getViewLifecycleOwner());

    }
}