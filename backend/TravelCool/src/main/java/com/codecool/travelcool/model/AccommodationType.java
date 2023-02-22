package com.codecool.travelcool.model;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public enum AccommodationType {
    @SerializedName("room")
    ROOM,
    @SerializedName("apartment")
    APARTMENT,
    @SerializedName("house")
    HOUSE
}
