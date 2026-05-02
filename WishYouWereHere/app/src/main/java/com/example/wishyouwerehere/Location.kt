package com.example.wishyouwerehere

import android.os.Parcel
import android.os.Parcelable

/**
 * Data model for a travel location.
 * Implements Parcelable so it can be passed between Activities via Intent.
 */
data class Location(
    val name: String,           // Name of the location
    val city: String,           // City / state / country
    val lastVisit: String,      // Date of last visit
    val rating: Float,          // Star rating 0.0 – 5.0
    val description: String,    // Longer description shown on detail screen
    val imageResId: Int,        // Drawable resource ID for the photo
    val isVisited: Boolean,     // Has the user been here?
    val visitSeason: String     // Best season to visit
) : Parcelable {

    // Parcelable read constructor – rebuilds the object from a Parcel
    constructor(parcel: Parcel) : this(
        name        = parcel.readString() ?: "",
        city        = parcel.readString() ?: "",
        lastVisit   = parcel.readString() ?: "",
        rating      = parcel.readFloat(),
        description = parcel.readString() ?: "",
        imageResId  = parcel.readInt(),
        isVisited   = parcel.readByte() != 0.toByte(),
        visitSeason = parcel.readString() ?: ""
    )

    // Write every field in the same order as the constructor above
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(lastVisit)
        parcel.writeFloat(rating)
        parcel.writeString(description)
        parcel.writeInt(imageResId)
        parcel.writeByte(if (isVisited) 1 else 0)
        parcel.writeString(visitSeason)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location = Location(parcel)
        override fun newArray(size: Int): Array<Location?> = arrayOfNulls(size)

        // Intent extra key used when passing a Location between activities
        const val EXTRA_KEY = "extra_location"
    }
}
