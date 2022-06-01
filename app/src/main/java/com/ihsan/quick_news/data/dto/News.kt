package com.ihsan.quick_news.data.dto

import android.os.Parcel
import android.os.Parcelable

data class News(
    var cover: String? = "",
    var title: String? = "",
    var content: String? = "",
    var author: String? = "",
    var source: String? = "",
    var tag: String? = "",
    var time: String? = "",
    var date: String? = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cover)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(author)
        parcel.writeString(source)
        parcel.writeString(tag)
        parcel.writeString(time)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}
