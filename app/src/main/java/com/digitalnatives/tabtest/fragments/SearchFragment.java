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

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.Movie;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.adapters.SearchViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class SearchFragment extends Fragment {

    EditText searchTextEdit;
    Button searchButton;
    private List<Movie> movies;
    private Context context;
    private static final String ARG_SECTION_NUMBER = "section_number";

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
        movies.add(new Movie("Bling number 2", 212, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));
        movies.add(new Movie("Bling Number3", 233, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", 132, "2010-11-23", "released", "This is the tagline"));





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        SearchViewAdapter rva = new SearchViewAdapter(movies, context);
        Log.d(MainActivity.tag, movies.toString());
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);




        searchButton = (Button) rootView.findViewById(R.id.button);
        searchTextEdit = (EditText) rootView.findViewById(R.id.searchText);





        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rootView;
    }

}
