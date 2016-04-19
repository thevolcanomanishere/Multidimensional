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
import android.widget.Toast;

import com.digitalnatives.tabtest.LibraryItem;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.activities.LibraryViewActivity;
import com.digitalnatives.tabtest.interfaces.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class LibraryViewAdapter extends RecyclerView.Adapter<LibraryViewAdapter.LibraryViewHolder> {

    public List<LibraryItem> movies;
    private Context mContext;
    private static Intent loadLibraryItem;

    //TMDb info
    private String baseImgUrl
            = "http://image.tmdb.org/t/p/w185";
    private String key = "?api_key=72508de530eba41fb571b4a5b10dd99b";

    public LibraryViewAdapter(List<LibraryItem> movies, Context context){
        this.movies = movies;
        this.mContext = context;
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

    public static class LibraryViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener clickListener;
        protected CardView cv;
        protected TextView movieName;
        protected TextView releaseDate;
        protected ImageView poster;
        protected TextView heartRate;

        LibraryViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            poster = (ImageView)itemView.findViewById(R.id.poster);
            releaseDate = (TextView)itemView.findViewById(R.id.releaseDate);
            heartRate = (TextView)itemView.findViewById(R.id.heartrate);
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
    public LibraryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.library_item, viewGroup, false);
        LibraryViewHolder lvh = new LibraryViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LibraryViewHolder libraryViewHolder, int i){
        final LibraryItem mi = movies.get(i);
        libraryViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("movieName", mi.getName());
                bundle.putInt("id", mi.getId());
                bundle.putString("description", mi.getDescription());
                bundle.putString("releaseDate", mi.getReleaseDate());
                bundle.putString("posterPath", mi.getImage_url());
                bundle.putIntegerArrayList("heartRates", mi.getHeartRates());
                Intent loadLibraryViewActivity = new Intent(mContext, LibraryViewActivity.class);
                loadLibraryViewActivity.putExtras(bundle);
                mContext.startActivity(loadLibraryViewActivity);
            }
        });
        libraryViewHolder.movieName.setText(mi.getName());
        libraryViewHolder.releaseDate.setText(mi.getReleaseDate());

        //get image with picasso
        String imgUrl = mi.getImage_url();
        Picasso.with(mContext).load(baseImgUrl + imgUrl + key).into(libraryViewHolder.poster);
        libraryViewHolder.heartRate.setText(mi.getHeartRateAverage() + " bpm");

    }

}
