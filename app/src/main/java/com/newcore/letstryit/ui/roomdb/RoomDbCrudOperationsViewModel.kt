package com.newcore.letstryit.ui.roomdb

import android.app.Application
import androidx.lifecycle.*
import com.newcore.letstryit.model.entites.room.Directory
import com.newcore.letstryit.model.entites.room.School
import com.newcore.letstryit.model.entites.room.releations.SchoolAndDirectory
import com.newcore.letstryit.model.local.roomdb.SchoolDb
import com.newcore.letstryit.util.DomeData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest

class RoomDbCrudOperationsViewModel(val app: Application) : AndroidViewModel(app) {
    private val schoolDb by lazy {
        SchoolDb.getInstance(app).schoolDao()
    }

    val loadingLiveData = MutableLiveData(false)


    fun getSchools() = schoolDb.getAllSchoolsAndDirectoryFromSchool()

    fun getDirectories() = schoolDb.getSchoolAndDirectoriesFromDirectory()

    fun getSchoolWithDirectoryName(): LiveData<List<SchoolAndDirectory>> =
        schoolDb.getAllSchoolsAndDirectoryFromSchool()
            .asFlow()
            .flatMapLatest {
                it.randomOrNull()?.let { schoolAndDirectory ->
                    schoolDb.getSchoolAndDirectoryWithDirectory(schoolAndDirectory.directory.directoryName)
                }?.asFlow() ?: emptyFlow()
            }.asLiveData()

    fun getDirectoryWithSchoolName(): LiveData<List<SchoolAndDirectory>> =
        schoolDb.getAllSchoolsAndDirectoryFromSchool()
            .asFlow()
            .flatMapLatest {
                it.randomOrNull()?.let { schoolAndDirectory ->
                    schoolDb.getSchoolAndDirectoryWithSchool(schoolAndDirectory.school.schoolName)
                }?.asFlow() ?: emptyFlow()
            }.asLiveData()


    fun insertSchool() {
        loadingLiveData.value = true

        val name = generateSchoolName()
        val school = School(
            schoolName = name
        )

        val directory = Directory(
            directoryName = generateHumanName(),
            schoolName = name
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listOf(
                    async { schoolDb.upsertSchool(school) },
                    async { schoolDb.upsertDirectory(directory) }
                ).awaitAll()
            }
            loadingLiveData.value = false
        }
    }

    fun insertDirectory() {
        loadingLiveData.value = true

        val name = generateSchoolName()

        val school = School(
            schoolName = name
        )

        val directory = Directory(
            directoryName = generateHumanName(),
            schoolName = name
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listOf(
                    async { schoolDb.upsertSchool(school) },
                    async { schoolDb.upsertDirectory(directory) }
                ).awaitAll()
            }
            loadingLiveData.value = false
        }

    }


    fun generateSchoolName(): String {
        return DomeData.schoolsName.random()
    }

    fun generateHumanName(): String {
        return DomeData.parentsNames.random()
    }


}