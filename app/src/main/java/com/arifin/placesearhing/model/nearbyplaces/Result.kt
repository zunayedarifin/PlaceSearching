package com.arifin.placesearhing.model.nearbyplaces

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Result(
    val business_status: String?,
    val geometry: Geometry?,
    val icon: String?,
    val icon_background_color: String?,
    val icon_mask_base_uri: String?,
    val name: String?,
    val opening_hours: OpeningHours?,
    val permanently_closed: Boolean,
    val photos: List<Photo>?,
    val place_id: String?,
    val plus_code: PlusCode?,
    val price_level: Int,
    val rating: Double,
    val reference: String?,
    val scope: String?,
    val types: List<String>?,
    val user_ratings_total: Int,
    val vicinity: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Geometry::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(OpeningHours::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.createTypedArrayList(Photo),
        parcel.readString(),
        parcel.readParcelable(PlusCode::class.java.classLoader),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(business_status)
        parcel.writeParcelable(geometry, flags)
        parcel.writeString(icon)
        parcel.writeString(icon_background_color)
        parcel.writeString(icon_mask_base_uri)
        parcel.writeString(name)
        parcel.writeParcelable(opening_hours, flags)
        parcel.writeByte(if (permanently_closed) 1 else 0)
        parcel.writeTypedList(photos)
        parcel.writeString(place_id)
        parcel.writeParcelable(plus_code, flags)
        parcel.writeInt(price_level)
        parcel.writeDouble(rating)
        parcel.writeString(reference)
        parcel.writeString(scope)
        parcel.writeStringList(types)
        parcel.writeInt(user_ratings_total)
        parcel.writeString(vicinity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}