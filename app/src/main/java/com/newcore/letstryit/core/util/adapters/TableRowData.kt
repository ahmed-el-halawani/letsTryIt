package com.newcore.letstryit.core.util.adapters

import android.os.Parcel
import android.os.Parcelable

data class TableRowData(
    val column1: String = "",
    val column2: String = "",
    val column3: String = "",
    val column4: String = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(column1)
        parcel.writeString(column2)
        parcel.writeString(column3)
        parcel.writeString(column4)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TableRowData> {
        override fun createFromParcel(parcel: Parcel): TableRowData {
            return TableRowData(parcel)
        }

        override fun newArray(size: Int): Array<TableRowData?> {
            return arrayOfNulls(size)
        }
    }
}
