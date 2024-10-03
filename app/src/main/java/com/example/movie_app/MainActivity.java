package com.example.movie_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.movie_app.databinding.ActivityMainBinding;
import com.example.movie_app.model.Movies;
import com.example.movie_app.model.Repo;
import com.example.movie_app.view.MovieAdapter;
import com.example.movie_app.view.OnItemClickListener;
import com.example.movie_app.viewmodel.MainActivityViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Movies> moviesArrayList;
    private MovieAdapter movieAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);

        // Set up RecyclerView
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Set refresh listener for SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Recall data when swiped to refresh
                getPopularMovies();
            }
        });

        // Load movies initially
        getPopularMovies();
    }

    private void getPopularMovies() {
        // Show ProgressBar while loading data
        binding.progressBar.setVisibility(View.VISIBLE);

        mainActivityViewModel.getMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                Log.d("TAG", "onChanged: " + movies.size());

                // Hide ProgressBar after data is loaded
                binding.progressBar.setVisibility(View.GONE);

                // Hide SwipeRefreshLayout loading indicator
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }

                // Update RecyclerView with new data
                moviesArrayList = (ArrayList<Movies>) movies;
                displayMoviesInRecyclerView();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void displayMoviesInRecyclerView() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(this, moviesArrayList, this);
            recyclerView.setAdapter(movieAdapter);
        } else {
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ItemClick(View view, int pos) {
        Movies selectedMovie = moviesArrayList.get(pos);
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", selectedMovie);
        startActivity(intent); // Start the MovieDetailsActivity
    }

}
