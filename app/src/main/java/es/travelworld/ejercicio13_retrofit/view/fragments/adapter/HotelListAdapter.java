package es.travelworld.ejercicio13_retrofit.view.fragments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelworld.ejercicio13_retrofit.databinding.ItemlayoutHotelListBinding;

import java.util.List;

import es.travelworld.ejercicio13_retrofit.domain.Hotel;

public class HotelListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Hotel> items;
    private final OnItemHotelClick listener;

    public interface OnItemHotelClick{
        void onItemClick(Hotel hotel);
    }

    public HotelListAdapter(List<Hotel> items, OnItemHotelClick listener){
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemlayoutHotelListBinding binding = ItemlayoutHotelListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HotelListViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HotelListViewHolder){
            ((HotelListViewHolder)holder).bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
