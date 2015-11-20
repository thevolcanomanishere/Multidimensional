package com.digitalnatives.tabtest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalnatives.tabtest.Movie;
import com.digitalnatives.tabtest.R;

import java.util.List;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MovieViewHolder>{

        public List<Movie> movies;
        private Context mContext;

        public SearchViewAdapter(List<Movie> movies, Context context){
        this.movies = movies;
        this.mContext = context;
        }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        protected CardView cv;
        protected TextView movieName;
        protected TextView releaseDate;
        protected ImageView poster;

        MovieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            releaseDate = (TextView)itemView.findViewById(R.id.releaseDate);
            poster = (ImageView)itemView.findViewById(R.id.poster);
        }

    }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
            MovieViewHolder mvh = new MovieViewHolder(v);
            return mvh;
        }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        Movie mi = movies.get(i);
        movieViewHolder.movieName.setText(mi.getName());
        movieViewHolder.releaseDate.setText(mi.getReleaseDate());
        movieViewHolder.poster.setImageResource(R.drawable.monsters);

    }
}
