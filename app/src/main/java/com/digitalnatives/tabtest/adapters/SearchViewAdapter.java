package com.digitalnatives.tabtest.adapters;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.Response;
import com.digitalnatives.tabtest.fragments.RateFragment;
import com.digitalnatives.tabtest.interfaces.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MovieViewHolder>{

        public List<Response.ResultsEntity> movies;
        private Context mContext;

        //TMDb info
        private String baseImgUrl
                = "http://image.tmdb.org/t/p/w185";
        private String key = "?api_key=72508de530eba41fb571b4a5b10dd99b";

        public SearchViewAdapter(List<Response.ResultsEntity> movies, Context context){
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
        protected TextView overview;

        MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            releaseDate = (TextView)itemView.findViewById(R.id.releaseDate);
            poster = (ImageView)itemView.findViewById(R.id.poster);
            overview = (TextView)itemView.findViewById(R.id.overview);
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
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,
                    viewGroup, false);
            MovieViewHolder mvh = new MovieViewHolder(v);
            return mvh;
        }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, int i) {
        final Response.ResultsEntity mi = movies.get(i);
        movieViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("movieName", mi.getOriginal_title());
                RateFragment rateFragment = new RateFragment();
                rateFragment.setArguments(bundle);
                MainActivity.mViewPager.setCurrentItem(1);
            }
        });
        movieViewHolder.movieName.setText(mi.getOriginal_title());
        movieViewHolder.releaseDate.setText(mi.getRelease_date());
        String imgUrl = mi.getPoster_path();
        //if there is no image url then use placeholder
        if(imgUrl == null){
            movieViewHolder.poster.setImageResource(R.drawable.nopicture);
        } else {
            //Get image url and load with picasso
            Picasso.with(mContext).load(baseImgUrl + imgUrl + key).into(movieViewHolder.poster);
        }
        movieViewHolder.overview.setText(mi.getOverview());
    }
}
