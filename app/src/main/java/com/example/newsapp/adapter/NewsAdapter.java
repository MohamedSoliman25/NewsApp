package com.example.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.ui.NewsWebView;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsModel;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context mContext;
    private ArrayList<NewsModel> mList = new ArrayList<>();

    public NewsAdapter(Context mContext, ArrayList<NewsModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setmList(ArrayList<NewsModel> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public ArrayList<NewsModel> getmList() {
        return mList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebView.class);
                intent.putExtra("url",mList.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        holder.mTime.setText("Published At : "+mList.get(position).getPublishedAt());
        holder.mAuthor.setText(mList.get(position).getAuthor());
        holder.mHeading.setText(mList.get(position).getTitle());
        holder.mContent.setText(mList.get(position).getDescription());
       Glide.with(mContext).load(mList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeading,mContent,mAuthor,mTime;
         private CardView cardView;
       private ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeading = itemView.findViewById(R.id.mainHeading);
            mContent = itemView.findViewById(R.id.content);
            mAuthor = itemView.findViewById(R.id.author);
            mTime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardview);



        }
    }
}

