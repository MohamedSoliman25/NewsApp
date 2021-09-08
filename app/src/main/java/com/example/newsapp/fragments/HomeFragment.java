package com.example.newsapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.constants.Constants;
import com.example.newsapp.model.NewsModel;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    String api = "c644e443863a4b748bdec3baa0b730bf";
    ArrayList<NewsModel> newsModelArrayList;
    NewsAdapter adapter;
    private RecyclerView recyclerViewHome;
    NewsViewModel newsViewModel;
    private static final String TAG = "HomeFragment";
    String test;


    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = view.findViewById(R.id.recyclerviewhome);
        newsModelArrayList = new ArrayList<>();
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(), newsModelArrayList);
        recyclerViewHome.setAdapter(adapter);
        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        newsViewModel.getNews(Constants.country,100,api);
        newsViewModel.getHomeMutableLiveData().observe(getActivity(), new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                adapter.setmList(newsModels);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }



//        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
//        newsViewModel.compositeDisposable.clear();
//        newsViewModel.getHomeMutableLiveData().removeObservers(getViewLifecycleOwner());


//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
//      //  newsViewModel.getHomeMutableLiveData().removeObservers(getViewLifecycleOwner());
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getContext(), "pause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Toast.makeText(getContext(), "destroyview", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getContext(), "destroy", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Toast.makeText(getContext(), "detach", Toast.LENGTH_SHORT).show();
//    }
}