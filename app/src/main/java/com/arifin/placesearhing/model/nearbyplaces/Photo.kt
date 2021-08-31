package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.util.ArrayList

data class Photo(
    val height: Int,
    val html_attributions: ArrayList<String>?,
    val photo_reference: String?,
    val width: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(height)
        parcel.writeStringList(html_attributions)
        parcel.writeString(photo_reference)
        parcel.writeInt(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}