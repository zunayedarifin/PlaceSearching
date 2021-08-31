package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Northeast(
    val lat: Double,
    val lng: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Northeast> {
        override fun createFromParcel(parcel: Parcel): Northeast {
            return Northeast(parcel)
        }

        override fun newArray(size: Int): Array<Northeast?> {
            return arrayOfNulls(size)
        }
    }
}