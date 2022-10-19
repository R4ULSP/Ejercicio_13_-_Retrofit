package es.travelworld.ejercicio13_retrofit.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelsList {
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("results")
    private List<Hotel> hotels;

    public HotelsList() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
