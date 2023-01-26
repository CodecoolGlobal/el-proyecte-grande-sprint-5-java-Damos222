package com.codecool.travelcool.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.SerializedName;

public enum AccommodationType {
    @SerializedName("room")
    ROOM,
    @SerializedName("apartment")
    APARTMENT,
    @SerializedName("house")
    HOUSE
}
