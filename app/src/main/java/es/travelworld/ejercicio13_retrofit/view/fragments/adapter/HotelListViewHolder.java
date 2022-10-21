package es.travelworld.ejercicio13_retrofit.view.fragments.adapter;


import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import com.travelworld.ejercicio13_retrofit.R;
import com.travelworld.ejercicio13_retrofit.databinding.ItemlayoutHotelListBinding;

import es.travelworld.ejercicio13_retrofit.domain.Hotel;
import es.travelworld.ejercicio13_retrofit.domain.ThumbUrl;


public class HotelListViewHolder extends RecyclerView.ViewHolder {

    private final ItemlayoutHotelListBinding binding;
    private final HotelListAdapter.OnItemHotelClick listener;

    public HotelListViewHolder(ItemlayoutHotelListBinding binding, HotelListAdapter.OnItemHotelClick listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(Hotel hotel){
        binding.hotelName.setText(hotel.getName());
        binding.hotelAddress.setText(hotel.getHotelAddress().getStreetAddess());
        binding.hotelNeighbourhood.setText(hotel.getNeighbourhood());
        binding.hotelPrice.setText(hotel.getRatePlan().getPrice().getCurrent());
        binding.hotelRatingText.setText(String.valueOf(hotel.getStarRating()));
        binding.hotelPhoto.setImageURI(Uri.parse(hotel.getThumbUrl().getSrpDesktop()));
        //TODO La url que se obtiene se tiene que usar para obtener la imagen mediante retrofit y verbo GET
    }
}
