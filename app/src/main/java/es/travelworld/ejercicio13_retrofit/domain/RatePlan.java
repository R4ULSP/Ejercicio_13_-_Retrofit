package es.travelworld.ejercicio13_retrofit.domain;

import com.google.gson.annotations.SerializedName;

public class RatePlan {
    @SerializedName("price")
    private Price price;

    public RatePlan() {
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
