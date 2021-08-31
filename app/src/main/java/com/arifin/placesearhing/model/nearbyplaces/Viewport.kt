package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Viewport(
    val northeast: Northeast?,
    val southwest: Southwest?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Northeast::class.java.classLoader),
        parcel.readParcelable(Southwest::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(northeast, flags)
        parcel.writeParcelable(southwest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Viewport> {
        override fun createFromParcel(parcel: Parcel): Viewport {
            return Viewport(parcel)
        }

        override fun newArray(size: Int): Array<Viewport?> {
            return arrayOfNulls(size)
        }
    }
}