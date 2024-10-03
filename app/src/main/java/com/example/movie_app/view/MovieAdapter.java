package com.example.movie_app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_app.databinding.MovieListItemBinding;
import com.example.movie_app.model.Movies;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Movies> moviesArrayList;
    OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context, ArrayList<Movies> moviesArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.moviesArrayList = moviesArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = MovieListItemBinding.inflate(
                LayoutInflater.from(context), parent, false
        );
        return new MyViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movies movies = moviesArrayList.get(position);
        holder.movieListItemBinding.setMovies(movies);
        holder.movieListItemBinding.executePendingBindings(); // Ensure bindings are executed immediately
    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MovieListItemBinding movieListItemBinding;

        public MyViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
            movieListItemBinding.getRoot().setOnClickListener(this);
//            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Handle click event
//                    Toast.makeText(context, moviesArrayList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.ItemClick(v, getAdapterPosition());

        }
    }
}
