package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class PlusCode(
    val compound_code: String?,
    val global_code: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(compound_code)
        parcel.writeString(global_code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<PlusCode> {
        override fun createFromParcel(parcel: Parcel): PlusCode {
            return PlusCode(parcel)
        }

        override fun newArray(size: Int): Array<PlusCode?> {
            return arrayOfNulls(size)
        }
    }
}