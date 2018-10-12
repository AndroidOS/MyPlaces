package com.burrows.manuelcarvalho.myplaces;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";
    private PlaceViewModel placeViewModel;
    private RecyclerView recyclerView;
    private Location location;
    public static final int ADD_PLACE_REQUEST = 1;


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
        location = new com.burrows.manuelcarvalho.myplaces.Location(getActivity());
        location.locationStart();

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
//                Toast.makeText(getActivity(), "Listening" + places, Toast.LENGTH_SHORT).show();
//
//                for (Place place : places) {
//                    Log.d(TAG, "onChanged: " + place.getPlaceName());
//                }
                Model.places = places;

                adapter.setPlaces(places);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                placeViewModel.delete(adapter.getPlaceAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Place deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }

    public void fabClicker() {


        location = new com.burrows.manuelcarvalho.myplaces.Location(getActivity());
        location.locationStart();

        Intent intent = new Intent(getActivity(), PlaceActivity.class);


        startActivityForResult(intent, ADD_PLACE_REQUEST);

        //startActivity(intent);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PLACE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(PlaceActivity.EXTRA_NAME);
            String description = data.getStringExtra(PlaceActivity.EXTRA_DESCRIPTION);
            Place newPlace = new Place(name, description, Model.latitude, Model.longitude);
            placeViewModel.insert(newPlace);
            Toast.makeText(getActivity(), " " + name + " " + description, Toast.LENGTH_SHORT).show();

        }
    }
}
