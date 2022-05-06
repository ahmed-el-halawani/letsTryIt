package com.newcore.letstryit.model.entites

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.newcore.letstryit.util.Constants

data class Account(
    val id: Int? = null, val email: String, val password: String,
    val userName:
    String,
) {
    fun toContentValues() = ContentValues().apply {
        put(Constants.USER_NAME, userName)
        put(Constants.EMAIL, email)
        put(Constants.PASSWORD, password)
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor): Account {
            return Account(
                cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                cursor.getString(cursor.getColumnIndex(Constants.EMAIL)),
                cursor.getString(cursor.getColumnIndex(Constants.PASSWORD)),
                cursor.getString(cursor.getColumnIndex(Constants.USER_NAME))
            )
        }
    }
}