package com.example.movie_app.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.movie_app.R;
import com.example.movie_app.serviceapi.MovieApiService;
import com.example.movie_app.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repo {

    ArrayList<Movies> moviesArrayList = new ArrayList<>();
    MutableLiveData<List<Movies>> mutableLiveData = new MutableLiveData<>();
    Application application;

    public Repo(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movies>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getService();
        Log.d("TAG", "getMutableLiveData: Loading");
        Call<Result> call = movieApiService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {

                Result result = response.body();

                if (result != null && result.getData() != null) {
                    moviesArrayList = (ArrayList<Movies>) result.getData();
                    mutableLiveData.setValue(moviesArrayList);

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                Log.d("TAG", "onFailure: " + throwable.getMessage());

            }
        });



        return mutableLiveData;

    }


}

