package com.codepath.jakusoba.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    int movieId;
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double rating;

    //empty constructor is needed by the Parceler library
    public Movie() {

    }

    /*create a constructor  to construct the information that will need displayed and that will be out there.*/
    /*If any of the information fails then the construtor will thrrow an exception and whoever is calling this method must handle the exception*/
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
    }

    /*Create a static method returning list of movie from JSONArray, movieJSONArray is the data we got back.*/
    public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException {
        /*This method iterates through jsonarray and constructs a movie from each element in this json array.*/
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            /*add a movie in each position of the array*/
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    /*we can construct movie objects which is posterpath, title and overview but we want to be able to generate getters i.e data from this objects.*/
    public String getPosterPath() {
        /*make the postpath useable by including full url and not just the relative path from the api response.*/
        /*full url , image size, and %s which just says posterpath is the relative url that i want to see. */
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);

    }

    public String getBackdropPath() {
        //*in order to render the image in a landscape mode, we have to pass "the back dropPath"*/
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }


    public String getOverview() {
        return overview;
    }


    public double getRating() {
        return rating;
    }
    public int getMovieId() {
        return movieId;
    }

}