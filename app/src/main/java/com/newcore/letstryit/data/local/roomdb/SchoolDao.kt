package com.newcore.letstryit.data.local.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.newcore.letstryit.data.entites.room.Directory
import com.newcore.letstryit.data.entites.room.School
import com.newcore.letstryit.data.entites.room.releations.SchoolAndDirectory

@Dao
interface SchoolDao {

    @Insert(onConflict = REPLACE)
    suspend fun upsertSchool(school: School)

    @Insert(onConflict = REPLACE)
    suspend fun upsertDirectory(directory: Directory)


    @Transaction
    @Query("select * from School where schoolName = :schoolName")
    fun getSchoolAndDirectoryWithSchool(schoolName: String): LiveData<List<SchoolAndDirectory>>

    @Transaction
    @Query("select * from Directory where directoryName = :directoryName")
    fun getSchoolAndDirectoryWithDirectory(directoryName: String): LiveData<List<SchoolAndDirectory>>

    @Transaction
    @Query("select * from School")
    fun getAllSchoolsAndDirectoryFromSchool(): LiveData<List<SchoolAndDirectory>>

    @Transaction
    @Query("select * from Directory")
    fun getSchoolAndDirectoriesFromDirectory(): LiveData<List<SchoolAndDirectory>>
}