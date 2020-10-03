package com.codepath.jakusoba.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.jakusoba.flixster.DetailActivity;
import com.codepath.jakusoba.flixster.R;
import com.codepath.jakusoba.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;
import java.util.Objects;

/*RV adapter is a abstract class so its method must be implemented where movieadapter is extending the RV adapter*/

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

  /*  To fill out the overide below, we need a coulple of things.class
    WE need a context for expample to inflate a view*/
    Context context;
    /*WE need a list of movies the adapter needs to hold on to*/
    List<Movie> movies;
    /*Both of the above variable will be passed in through the construtor.*/

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    /*OnCreateViewHolder is method which will be inflating a layout from XML and returning the holder*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*WE are inflating the item_movie layout and it is returning a view which we assigned to View movieView*/
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }
    /*On bindview holder populates data into the item through the holder*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    /*Get the movie at the passed at the position with Movie movie = movies.get(position); i.e we have a list of movies and we are indexing into it at this position
        bind the movie data into the viewHolder*/
       /* Then take the data in the movie and populate the elements in the view holder which are tvTitle,
        ivPoster, tvOverview. We bind the data by inventing a method called bind method. */
       Log.d("MovieAdapter", "onBindViewHolder");
       Movie movie = movies.get(position);
       holder.bind(movie);
    }
    /*getItemCount returns the total count of items in the list.*/
    @Override
    public int getItemCount() {
        return movies.size();
    }

    /*This adapter extend the recyclerview adapter and this parametrized by a view holder*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        /*The view holder is a representaion of row the recyclerview.*/
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            /*define where the textview and imageview is coming from, i.e its been searched via their Id*/
            tvTitle = itemView.findViewById( R.id.tvTitle );
            tvOverview = itemView.findViewById( R.id.tvOverview );
            ivPoster = itemView.findViewById( R.id.ivPoster );
            container = itemView.findViewById(R.id.container);

        }


        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle() );
            tvOverview.setText(movie.getOverview());
            /*instead of always loadng posterPath or backDroppath, we load the imageURl which will changes depending on the position of the phone.*/
            String imageUrl;
            //if phone is in landscape mode,
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // imageURl = backDropPath
                imageUrl = movie.getBackdropPath();
            }   else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);
            //register a clickListener so we know when we want to move to another activity(screen)
            //register that event on the movie title.

            //register the click listener on the entire of movie row. To do this we need to get a refrence to the container element which contains the image and type text views that we see.
            //when we want to tap we want to navigaete to a new activity.
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Navigate to a new activity on tap.
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);



                }
            });

        }


    }

}