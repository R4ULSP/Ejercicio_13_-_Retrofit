package es.travelworld.ejercicio13_retrofit.domain;

import com.google.gson.annotations.SerializedName;

public class ThumbUrl {
    @SerializedName("srpDesktop")
    private String srpDesktop;

    public ThumbUrl() {
    }

    public String getSrpDesktop() {
        return srpDesktop;
    }

    public void setSrpDesktop(String srpDesktop) {
        this.srpDesktop = srpDesktop;
    }
}
