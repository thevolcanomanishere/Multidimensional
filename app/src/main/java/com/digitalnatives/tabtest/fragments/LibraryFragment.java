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

import com.digitalnatives.tabtest.LibraryItem;
import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.adapters.LibraryViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexmcgonagle on 18/11/2015.
 */
public class LibraryFragment extends Fragment{

    private List<LibraryItem> libraryList;
    private Context context;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static LibraryFragment newInstance(int sectionNumber) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public LibraryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        libraryList = new ArrayList<>();
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));
        libraryList.add(new LibraryItem("Bling blang bloom whats up", 234, "/7k9db7pJyTaVbz3G4eshGltivR1.jpg", "Monsters. Inc", "Releasedate", 148, "released", "2010-20-12"));


        


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_fragment, container, false);


        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.libRv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        LibraryViewAdapter rva = new LibraryViewAdapter(libraryList, context);
        Log.d(MainActivity.tag, libraryList.toString());
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);

        return rootView;
    }



}
