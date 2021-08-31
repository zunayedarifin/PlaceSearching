package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class OpeningHours(
    val open_now: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (open_now) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<OpeningHours> {
        override fun createFromParcel(parcel: Parcel): OpeningHours {
            return OpeningHours(parcel)
        }

        override fun newArray(size: Int): Array<OpeningHours?> {
            return arrayOfNulls(size)
        }
    }
}