package com.codepath.jakusoba.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.jakusoba.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static final String YOUTUBE_API_KEY="AIzaSyDWExB5FlSgWyj08PzIZzsnaiqGdCxAkvM";
    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);



        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float)movie.getRating());
    }
}