package com.example.appmoviles.models

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Performer (
    val performerId:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    val creationDate:String,
    val type:String,
    val bandId:Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(performerId)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(birthDate)
        parcel.writeString(creationDate)
        parcel.writeString(type)
        parcel.writeInt(bandId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Performer> {
        override fun createFromParcel(parcel: Parcel): Performer {
            return Performer(parcel)
        }

        override fun newArray(size: Int): Array<Performer?> {
            return arrayOfNulls(size)
        }
    }
}