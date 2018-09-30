package com.burrows.manuelcarvalho.myplaces;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PlaceRepository {

    private PlaceDao placeDao;
    private LiveData<List<Place>> allPlaces;

    public PlaceRepository(Application application) {
        PlaceDataBase database = PlaceDataBase.getInstance(application);
        placeDao = database.placeDao();
        allPlaces = placeDao.getAllPlaces();
    }

    public void insert(Place place) {
        new InsertNoteAsyncTask(placeDao).execute(place);
    }

    public void update(Place place) {
        new UpdateNoteAsyncTask(placeDao).execute(place);
    }

    public void delete(Place place) {
        new DeleteNoteAsyncTask(placeDao).execute(place);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(placeDao).execute();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao placeDao;

        private InsertNoteAsyncTask(PlaceDao placeDao) {
            this.placeDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... places) {
            placeDao.insert(places[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao placeDao;

        private UpdateNoteAsyncTask(PlaceDao placeDao) {
            this.placeDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... places) {
            placeDao.update(places[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao placeDao;

        private DeleteNoteAsyncTask(PlaceDao placeDao) {
            this.placeDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... notes) {
            placeDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlaceDao placeDao;

        private DeleteAllNotesAsyncTask(PlaceDao placeDao) {
            this.placeDao = placeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            placeDao.deleteAllPlaces();
            return null;
        }
    }

}
