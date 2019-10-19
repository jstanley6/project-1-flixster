package com.jasonstanl3y.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jasonstanl3y.flixster.R;
import com.jasonstanl3y.flixster.models.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at the passed in position
        Movie movie = movies.get(position);
        //bind the movie data into the ViewHolder
        viewHolder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {

            tvTitle.setText(movie.getTitle());
            tvOverView.setText(movie.getOverView());
            String imageUrl;
            //if phone == landscape
            //then imageUrl is the backdrop image
            //else image url = poster image
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                imageUrl = movie.getBackDropPath();

            }else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

        }
    }
}
