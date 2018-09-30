package com.burrows.manuelcarvalho.myplaces;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository repository;
    private LiveData<List<Place>> allPlaces;


    public PlaceViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository(application);
        allPlaces = repository.getAllPlaces();
    }

    public void insert(Place place) {
        repository.insert(place);
    }

    public void update(Place place) {
        repository.update(place);
    }

    public void delete(Place place) {
        repository.delete(place);
    }

    public void deleteAllPlaces() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

}
