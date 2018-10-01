package com.burrows.manuelcarvalho.myplaces;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    private List<Place> places = new ArrayList<>();

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.place_item, viewGroup, false);
        return new PlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder placeHolder, int i) {
        Place currentPlace = places.get(i);
        placeHolder.textViewTitle.setText(currentPlace.getPlaceName());
        placeHolder.textViewDescription.setText(currentPlace.getPlaceDescription());

    }


    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setNotes(List<Place> notes) {
        this.places = notes;
        notifyDataSetChanged();
    }

    class PlaceHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;


        public PlaceHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_place);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

        }
    }
    

}
