package com.burrows.manuelcarvalho.myplaces;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";
    private PlaceViewModel placeViewModel;
    private RecyclerView recyclerView;
//    private LocationCallback mLocationCallback;
//    private FusedLocationProviderClient mFusedLocationClient;
//    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
//    private long FASTEST_INTERVAL = 2000; /* 2 sec */
//
//    private LocationRequest mLocationRequest;


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

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final PlaceAdapter adapter = new PlaceAdapter();
        recyclerView.setAdapter(adapter);

        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        //noteViewModel.insert(new Note("Title6", "description 6", 6));
        placeViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable List<Place> places) {
                Toast.makeText(getActivity(), "Listening" + places, Toast.LENGTH_SHORT).show();

                for (Place place : places) {
                    Log.d(TAG, "onChanged: " + place.getPlaceName());
                }

                adapter.setPlaces(places);
            }
        });


    }

    public void fabClicker() {


        com.burrows.manuelcarvalho.myplaces.Location location = new com.burrows.manuelcarvalho.myplaces.Location(getActivity());
        location.fabClicker();


    }

}
