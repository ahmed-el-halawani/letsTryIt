package com.newcore.letstryit.model.interfaces

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

interface IContentProvider {
    fun query(
        p0: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor?

    fun insert(p0: Uri, p1: ContentValues?): Uri?

    fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int

    fun update(p0: Uri,values: ContentValues?, whereClause: String?, whereArgs: Array<out String>?): Int
    fun getType(p0: Uri): String
}