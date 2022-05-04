package com.newcore.letstryit.model.entites

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.newcore.letstryit.util.Constants.EMAIL
import com.newcore.letstryit.util.Constants.ID
import com.newcore.letstryit.util.Constants.NAME
import com.newcore.letstryit.util.Constants.PHONE_NUMBER

data class User(val id: Int? = null, val name: String, val email: String, val phoneNumber: String) {
    fun toContentValues() = ContentValues().apply {
        put(NAME, name)
        put(EMAIL, email)
        put(PHONE_NUMBER, phoneNumber)
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor): User {
            return User(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(NAME)),
                cursor.getString(cursor.getColumnIndex(EMAIL)),
                cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)),
            )
        }
    }

}