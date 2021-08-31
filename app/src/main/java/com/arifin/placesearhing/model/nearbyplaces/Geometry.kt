package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Geometry(
    val location: Location?,
    val viewport: Viewport?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Location::class.java.classLoader),
        parcel.readParcelable(Viewport::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(location, flags)
        parcel.writeParcelable(viewport, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}