package com.newcore.letstryit.data.entites.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Directory(
    @PrimaryKey(autoGenerate = false)
    val directoryName: String,
    val schoolName: String,
)