package com.burrows.manuelcarvalho.myplaces;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "place_table")
public class Place {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String placeName;
    private String placeDescription;
    private double latitude;
    private double longitude;

    public Place(String placeName, String placeDescription, double latitude, double longitude) {
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
