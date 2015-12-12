package com.digitalnatives.tabtest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.Movie;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.interfaces.ItemClickListener;

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

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ItemClickListener clickListener;
        protected CardView cv;
        protected TextView movieName;
        protected TextView releaseDate;
        protected ImageView poster;

        MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            releaseDate = (TextView)itemView.findViewById(R.id.releaseDate);
            poster = (ImageView)itemView.findViewById(R.id.poster);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }

    }


        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
            MovieViewHolder mvh = new MovieViewHolder(v);
            return mvh;
        }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, int i) {
        Movie mi = movies.get(i);
        movieViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                MainActivity.mViewPager.setCurrentItem(1);
            }
        });
        movieViewHolder.movieName.setText(mi.getName());
        movieViewHolder.releaseDate.setText(mi.getReleaseDate());
        movieViewHolder.poster.setImageResource(R.drawable.monsters);

    }
}
