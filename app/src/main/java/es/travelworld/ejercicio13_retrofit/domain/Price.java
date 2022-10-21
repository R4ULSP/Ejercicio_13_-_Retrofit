package es.travelworld.ejercicio13_retrofit.domain;

import com.google.gson.annotations.SerializedName;

public class Price {
    @SerializedName("current")
    private String current;
    @SerializedName("exactCurrent")
    private double exactCurrent;

    public Price() {
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public double getExactCurrent() {
        return exactCurrent;
    }

    public void setExactCurrent(double exactCurrent) {
        this.exactCurrent = exactCurrent;
    }
}
