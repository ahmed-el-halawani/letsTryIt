package com.newcore.letstryit.model.repositories.irepo

import androidx.lifecycle.LiveData
import com.newcore.letstryit.model.entites.Settings

interface ISettingsSharedPreferenceRepo {
    fun update(update: (Settings) -> Settings)
    fun getSettings():Settings
    fun getSettingsLiveData(): LiveData<Settings>
}