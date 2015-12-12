package com.digitalnatives.tabtest.adapters;

import android.content.Context;
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
import com.digitalnatives.tabtest.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by alexmcgonagle on 19/11/2015.
 */
public class LibraryViewAdapter extends RecyclerView.Adapter<LibraryViewAdapter.LibraryViewHolder> {

    public List<LibraryItem> movies;
    private Context mContext;

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
        protected TextView rateDate;
        protected ImageView poster;

        LibraryViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            poster = (ImageView)itemView.findViewById(R.id.poster);
            rateDate = (TextView)itemView.findViewById(R.id.rateDate);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
           // clickListener.onClick(view, getAdapterPosition());
            Toast.makeText(view.getContext(), "Test", Toast.LENGTH_SHORT).show();
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
        LibraryItem mi = movies.get(i);
        libraryViewHolder.movieName.setText(mi.getName());
        libraryViewHolder.rateDate.setText(mi.getRateDate());
        libraryViewHolder.poster.setImageResource(R.drawable.monsters);

    }

}
