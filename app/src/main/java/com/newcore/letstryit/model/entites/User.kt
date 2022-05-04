package com.newcore.letstryit.model.entites

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.newcore.letstryit.model.local.sqlite.MySqlite

data class User(val id: Int? = null, val name: String, val email: String, val phoneNumber: String) {
    fun toContentValues() = ContentValues().apply {
        put(MySqlite.NAME, name)
        put(MySqlite.EMAIL, email)
        put(MySqlite.PHONE_NUMBER, phoneNumber)
    }

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor): User {
            return User(
                cursor.getInt(cursor.getColumnIndex(MySqlite.ID)),
                cursor.getString(cursor.getColumnIndex(MySqlite.NAME)),
                cursor.getString(cursor.getColumnIndex(MySqlite.EMAIL)),
                cursor.getString(cursor.getColumnIndex(MySqlite.PHONE_NUMBER)),
            )
        }
    }

}