package com.burrows.manuelcarvalho.myplaces;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Place.class}, version = 1)
public abstract class PlaceDataBase extends RoomDatabase {
    private static final String TAG = "PlaceDataBase";

    private static PlaceDataBase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Log.d(TAG, "onCreate: DB");
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    public static synchronized PlaceDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlaceDataBase.class, "place_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    public abstract PlaceDao placeDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlaceDao placeDao;

        private PopulateDBAsyncTask(PlaceDataBase db) {
            placeDao = db.placeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            placeDao.insert(new Place("Park", "Green Park", 1.0, 1.0));
            placeDao.insert(new Place("House", "Green Park", 1.0, 1.0));
            placeDao.insert(new Place("CarPark", "Green Park", 1.0, 1.0));
            placeDao.insert(new Place("Beach", "Green Park", 1.0, 1.0));

            return null;
        }
    }
}
