package com.newcore.letstryit.model.local.contentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.newcore.letstryit.model.local.sqlite.MySqlite
import com.newcore.letstryit.util.Constants.ACCOUNT_TABLE
import com.newcore.letstryit.util.Constants.USERS_TABLE

class MyContentProvider : ContentProvider() {

    private lateinit var db: MySqlite
    private lateinit var writable: SQLiteDatabase

    override fun onCreate(): Boolean {
        return context?.let {
            db = MySqlite(it)
            db.writableDatabase?.apply { writable = this } != null
        } ?: false
    }

    private fun table(uri: Uri) = when (Routes.fromCode(Routes.matcher.match(uri))) {
        Routes.Users, Routes.UserId -> USERS_TABLE
        Routes.Accounts, Routes.AccountsId -> ACCOUNT_TABLE
    }

    override fun query(
        p0: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {

        val c = writable.query(
            table(p0),
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        c.setNotificationUri(context?.contentResolver, p0)
        return c
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        writable.insert(table(p0), "", p1).also {
            val uri = ContentUris.withAppendedId(Routes.routeFactory(Routes.Users), it)
            return if (it > 0) {
                context!!.contentResolver.notifyChange(uri, null)
                uri
            } else
                null
        }
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return writable.delete(
            table(p0),
            p1,
            p2
        ).also {
            val uri = ContentUris.withAppendedId(Routes.routeFactory(Routes.Users),
                (p2?.get(0) ?: "-1").toLong())
            context!!.contentResolver.notifyChange(uri, null)
        }
    }

    override fun update(
        p0: Uri,
        values: ContentValues?,
        whereClause: String?,
        whereArgs: Array<out String>?,
    ): Int {
        return writable.update(
            table(p0),
            values, whereClause, whereArgs
        ).also {
            val uri = ContentUris.withAppendedId(Routes.routeFactory(Routes.Users),
                (whereArgs?.get(0) ?: "-1").toLong())

            context!!.contentResolver.notifyChange(uri, null)
        }
    }

    override fun getType(p0: Uri): String {
        val route = Routes.fromCode(Routes.matcher.match(p0))
        return when (route) {
            Routes.Users, Routes.Accounts -> "vnd.android.cursor.dir/vnd.com.newcore.letstryit.provider."
            Routes.UserId, Routes.AccountsId -> "vnd.android.cursor.item/vnd.com.newcore.letstryit.provider."
        } + route.route
    }
}