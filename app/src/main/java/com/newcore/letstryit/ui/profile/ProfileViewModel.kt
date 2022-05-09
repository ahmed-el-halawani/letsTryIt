package com.newcore.letstryit.ui.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.newcore.letstryit.R
import com.newcore.letstryit.model.local.prefrence.MySettingsSharedPreference
import com.newcore.letstryit.model.repositories.AccountRepo
import com.newcore.letstryit.model.repositories.AuthenticationFacade
import com.newcore.letstryit.model.repositories.SettingsSharedPreferenceRepo

class ProfileViewModel(val app: Application) : AndroidViewModel(app) {

    private val settingsSharedPreference = SettingsSharedPreferenceRepo(
        MySettingsSharedPreference.getInstance(
            app.getSharedPreferences(
                app.getString(R.string.pref_file_key),
                Context.MODE_PRIVATE
            )
        )
    )

    private val accountRepo = AccountRepo(app.contentResolver)

    private val authenticationFacade by lazy {
        AuthenticationFacade(accountRepo, settingsSharedPreference)
    }

    fun settingsLiveData() = settingsSharedPreference.getSettingsLiveData()

    fun signOut() {
        authenticationFacade.signOut()
    }

}