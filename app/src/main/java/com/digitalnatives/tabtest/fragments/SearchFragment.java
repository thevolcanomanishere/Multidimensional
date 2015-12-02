package com.digitalnatives.tabtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.Movie;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.adapters.SearchViewAdapter;
import com.digitalnatives.tabtest.interfaces.TMDbInterface;
import com.digitalnatives.tabtest.services.TMDbService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class SearchFragment extends Fragment {

    EditText searchTextEdit;
    private Button searchButton;
    private List<Movie> movies;
    private Context context;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String testSearch = "Toy Story";
    private String key = "72508de530eba41fb571b4a5b10dd99b";
    private String tag = "SearchFragTag: ";
    private static SearchViewAdapter rva = null;

    //testing
    private int btnCount = 0;

    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        movies = new ArrayList<>();
        movies.add(new Movie("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);


        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

        if(btnCount == 0){
            rv.setVisibility(View.GONE);
        }

        //Set layout manager for RecyclerView adapter
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rva = new SearchViewAdapter(movies, context);
        Log.d(MainActivity.tag, movies.toString());
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);

        searchButton = (Button) rootView.findViewById(R.id.button);
        searchTextEdit = (EditText) rootView.findViewById(R.id.searchText);

        //movieSearch();



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btnCount++;
                rv.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }

    void movieSearch() {
        TMDbInterface client = TMDbService.createService(TMDbInterface.class);


        Call<Movie> call = client.getMovieResponse(testSearch, key);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                Log.d(tag, "*********  reached onResponse  **********");
                movies = response.body().movies;
                rva.notifyDataSetChanged();
                Log.d(tag, movies.toString());

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "DOH !", Toast.LENGTH_LONG).show();
                Log.d(tag, "**** Call failed ****");
            }
        });
    }

}
