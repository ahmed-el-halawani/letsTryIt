package com.newcore.letstryit.data.repositories.irepo

import androidx.lifecycle.LiveData
import com.newcore.letstryit.data.entites.Settings

interface ISettingsSharedPreferenceRepo {
    fun update(update: (Settings) -> Settings)
    fun getSettings():Settings
    fun getSettingsLiveData(): LiveData<Settings>
}