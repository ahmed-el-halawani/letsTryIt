package com.newcore.letstryit.model.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newcore.letstryit.model.entites.room.Directory
import com.newcore.letstryit.model.entites.room.School

@Database(entities = [School::class, Directory::class], version = 4)
abstract class SchoolDb : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao


    companion object {
        fun getInstance(context: Context): SchoolDb = instance ?: synchronized(lock) {
            return buildDatabase(context).also {
                instance = it
            }
        }

        private var instance: SchoolDb? = null
        private const val lock = ""

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            SchoolDb::class.java, "school_db.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}