package com.codepath.jakusoba.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.jakusoba.flixster.adapters.MovieAdapter;
import com.codepath.jakusoba.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
   /* We made the API URL a constant (NOWPLAYINGURL) so that we dont have to keep typing*/
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    /*This statement is necessary so we can log statement.*/
    public static final String TAG = "MainActivity";
    /*This will make a ourlives easier when creating a recycler view showing a list of movies.*/
    List<Movie> movies;
           
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        RecyclerView rvMovies = findViewById( R.id.rvMovies );
        movies = new ArrayList<>(  );


        /*To Bind the adapter to the data source to populate the RecyclerView*/
        // Create the adapter
         final MovieAdapter movieAdapter = new MovieAdapter( this, movies );

        //set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter );

        //Set a layout manager on the recycler view.
        rvMovies.setLayoutManager( new LinearLayoutManager( this ) );

        AsyncHttpClient client = new AsyncHttpClient();
        /*we make a call to the nowplaying url and asign the jsonresponsehandler to handle the call back.*/
        client.get( NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d( TAG, "onSuccess: " );
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject. getJSONArray( "results" );
                    /*log if successful and change results to string*/
                    Log.i( TAG, "Results: " + results.toString() );
                    /*call defined static method from  main activity, this will return a list of movie objects and its best we make a number variable i.e see begining of mainActivity.java*/
                    //When ever the data changes behind the adapter, we need let the adapter know so that it re-renders the data
                    movies.addAll(Movie.fromJSONArray( results ));
                    movieAdapter.notifyDataSetChanged();
                    Log.i( TAG, "Movies: " + movies.size() );
                } catch (JSONException e) {
                    Log.e( TAG, "Hit json exception ", e  );

                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d( TAG, "onFailure: " );
            }
        } );
    }
}