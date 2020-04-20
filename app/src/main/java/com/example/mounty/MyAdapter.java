package com.example.mounty;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mounty.model.TopRatedMovieModel;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context ct;
    List<TopRatedMovieModel> topRatedMovieModelList=new ArrayList<>();

    public MyAdapter(Context ct, List<TopRatedMovieModel> topRatedMovieModelList) {
        this.ct=ct;
        this.topRatedMovieModelList=topRatedMovieModelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(ct);
       View view= inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(topRatedMovieModelList.get(position).getTitle());
        holder.desc.setText(topRatedMovieModelList.get(position).getDescription());
        holder.onImagePoster(topRatedMovieModelList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        if(topRatedMovieModelList!=null) {
            return topRatedMovieModelList.size();
        }else {
            return 0;//progress bar aa ra
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView desc;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTextView);
            desc=itemView.findViewById(R.id.descTextView);
            img=itemView.findViewById(R.id.imageView);
        }
        public void onImagePoster(String imagePath){

            //placeholder image
            Glide.with(ct).load("https://image.tmdb.org/t/p/w500/"+imagePath).into(img);
        }
    }
}
