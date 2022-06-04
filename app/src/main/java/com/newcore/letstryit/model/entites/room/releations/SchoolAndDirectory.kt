package com.newcore.letstryit.model.entites.room.releations

import androidx.room.Embedded
import androidx.room.Relation
import com.newcore.letstryit.model.entites.room.Directory
import com.newcore.letstryit.model.entites.room.School


class SchoolAndDirectory(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val directory: Directory,
)