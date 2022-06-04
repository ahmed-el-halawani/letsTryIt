package com.newcore.letstryit.data.repositories

import com.newcore.letstryit.data.entites.Settings
import com.newcore.letstryit.data.local.prefrence.MySettingsSharedPreference
import com.newcore.letstryit.data.repositories.irepo.ISettingsSharedPreferenceRepo

class SettingsSharedPreferenceRepo(private val mySettingsSharedPreference: MySettingsSharedPreference) :
    ISettingsSharedPreferenceRepo {
    override fun update(update: (Settings) -> Settings) = mySettingsSharedPreference.update(update)

    override fun getSettings(): Settings = mySettingsSharedPreference.getSettings()

    override fun getSettingsLiveData() = mySettingsSharedPreference.getSettingsLiveData()
}