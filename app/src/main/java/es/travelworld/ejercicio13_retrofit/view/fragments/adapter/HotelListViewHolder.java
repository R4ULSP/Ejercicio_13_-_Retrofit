package es.travelworld.ejercicio13_retrofit.view.fragments.adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import androidx.recyclerview.widget.RecyclerView;

import com.travelworld.ejercicio13_retrofit.R;
import com.travelworld.ejercicio13_retrofit.databinding.ItemlayoutHotelListBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import es.travelworld.ejercicio13_retrofit.domain.Hotel;


public class HotelListViewHolder extends RecyclerView.ViewHolder {

    private final ItemlayoutHotelListBinding binding;
    private final HotelListAdapter.OnItemHotelClick listener;

    public HotelListViewHolder(ItemlayoutHotelListBinding binding, HotelListAdapter.OnItemHotelClick listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(Hotel hotel) {
        binding.hotelName.setText(hotel.getName());
        binding.hotelAddress.setText(hotel.getHotelAddress().getStreetAddess());
        binding.hotelNeighbourhood.setText(hotel.getNeighbourhood());
        binding.hotelPrice.setText(hotel.getRatePlan().getPrice().getCurrent());
        binding.hotelRatingText.setText(String.valueOf(hotel.getStarRating()));
        initHotelThumb(hotel);
    }

    private void initHotelThumb(Hotel hotel){
        Handler handler = new Handler();
        Runnable runnable = () -> {
            Bitmap bitmap = getHotelThumb(hotel.getThumbUrl().getSrpDesktop());
            handler.post(() -> {
                if (bitmap != null) {
                    binding.hotelPhoto.setImageBitmap(bitmap);

                } else {
                    binding.hotelPhoto.setImageResource(R.drawable.image_not_found);
                }
            });
        };
        new Thread(runnable).start();
    }

    private Bitmap getHotelThumb(String thumbUrl) {
        try {
            URL url = new URL(thumbUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
