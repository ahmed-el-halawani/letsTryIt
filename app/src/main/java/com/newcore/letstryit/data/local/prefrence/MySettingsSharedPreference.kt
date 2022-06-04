package com.newcore.letstryit.data.local.prefrence

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.newcore.letstryit.data.entites.Settings

class MySettingsSharedPreference(private val sharedPreferences: SharedPreferences) {

    private var settings = Settings()
        set(value) {
            settingsMutableLiveData.value = value
            field = value
        }

    private val settingsMutableLiveData: MutableLiveData<Settings> = MutableLiveData(settings)

    init {
        settings = sharedPreferences.getString(key, null)?.let {
            Gson().fromJson(
                it, Settings::class.java
            )
        } ?: Settings()
    }

    companion object {
        const val key = "Settings"

        var mySettingsInstance: MySettingsSharedPreference? = null
        const val LOCK = ""

        fun getInstance(sharedPreferences: SharedPreferences): MySettingsSharedPreference =
            mySettingsInstance ?: synchronized(LOCK) {
                mySettingsInstance ?: MySettingsSharedPreference(sharedPreferences).also {
                    mySettingsInstance = it
                }
            }

    }


    fun getSettingsLiveData(): LiveData<Settings> = settingsMutableLiveData

    fun getSettings(): Settings = settings

    fun update(update: (Settings) -> Settings) {
        update(settings.copy()).also {
            settings = it
            sharedPreferences.edit().apply {
                putString(key, Gson().toJson(it))
                apply()
            }
        }
    }

}