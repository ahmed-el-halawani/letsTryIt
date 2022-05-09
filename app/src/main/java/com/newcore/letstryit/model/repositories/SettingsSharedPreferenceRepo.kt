package com.newcore.letstryit.model.repositories

import com.newcore.letstryit.model.entites.Settings
import com.newcore.letstryit.model.local.prefrence.MySettingsSharedPreference
import com.newcore.letstryit.model.repositories.irepo.ISettingsSharedPreferenceRepo

class SettingsSharedPreferenceRepo(private val mySettingsSharedPreference: MySettingsSharedPreference) :
    ISettingsSharedPreferenceRepo {
    override fun update(update: (Settings) -> Settings) = mySettingsSharedPreference.update(update)

    override fun getSettings(): Settings = mySettingsSharedPreference.getSettings()

    override fun getSettingsLiveData() = mySettingsSharedPreference.getSettingsLiveData()
}