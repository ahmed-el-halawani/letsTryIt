package com.newcore.letstryit.data.local.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.newcore.letstryit.core.util.Constants.ACCOUNT_TABLE
import com.newcore.letstryit.core.util.Constants.EMAIL
import com.newcore.letstryit.core.util.Constants.ID
import com.newcore.letstryit.core.util.Constants.NAME
import com.newcore.letstryit.core.util.Constants.PASSWORD
import com.newcore.letstryit.core.util.Constants.PHONE_NUMBER
import com.newcore.letstryit.core.util.Constants.USERS_TABLE
import com.newcore.letstryit.core.util.Constants.USER_NAME

/*
table user
    id
    name
    email
    phoneNumber
*/

class MySqlite constructor(
    context: Context,
) : SQLiteOpenHelper(context, "MyDatabase", null, 2) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("""
            CREATE TABLE $USERS_TABLE(
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT NOT NULL,
                $EMAIL TEXT NOT NULL,
                $PHONE_NUMBER TEXT NOT NULL
            )
        """.trimIndent())

        p0.execSQL("""
            CREATE TABLE $ACCOUNT_TABLE(
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $USER_NAME TEXT NOT NULL,
                $EMAIL TEXT NOT NULL,
                $PASSWORD TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        onCreate(p0)
    }


}