package com.example.movie_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movie_app.model.Movies;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Retrieve the movie object from the intent
        Movies movie = (Movies) getIntent().getSerializableExtra("movie");

        // Initialize UI elements
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView overviewTextView = findViewById(R.id.overviewTextView);
        ImageView posterImageView = findViewById(R.id.posterImageView);

        // Set data to UI elements
        titleTextView.setText(movie.getTitle());
        overviewTextView.setText(movie.getOverview());

        // Load the poster image
        if (movie.getPosterPath() != null) {
            loadImage(posterImageView, movie.getPosterPath());
        }
    }

    // Method to load images using Glide
    private void loadImage(ImageView view, String imageUrl) {
        String url = "https://image.tmdb.org/t/p/w500/" + imageUrl;
        Glide.with(this)
                .load(url)
                .into(view);
    }
}
