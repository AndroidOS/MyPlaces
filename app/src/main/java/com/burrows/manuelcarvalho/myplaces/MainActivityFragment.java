package com.burrows.manuelcarvalho.myplaces;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private PlaceViewModel placeViewModel;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        //noteViewModel.insert(new Note("Title6", "description 6", 6));
        placeViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable List<Place> places) {
//                Toast.makeText(MainActivity.this,"Listening" + notes.size(),Toast.LENGTH_SHORT).show();
//                lister(notes);
                //tester();
                //adapter.setNotes(notes);
            }
        });

    }

}
