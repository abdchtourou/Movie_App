package com.example.movie_app.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movie_app.model.Movies;
import com.example.movie_app.model.Repo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private final Repo repo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repo = new Repo(application);
    }

    public LiveData<List<Movies>> getMovies() {
        Log.d("TAG", "getMovies: Loading ");
        LiveData<List<Movies>> response=this.repo.getMutableLiveData();
        Log.d("TAG", "getMovies: fetch "+response.getValue());
        return response;

    }
}
