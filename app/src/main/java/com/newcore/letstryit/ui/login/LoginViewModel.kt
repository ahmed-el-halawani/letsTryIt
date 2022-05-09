package com.newcore.letstryit.ui.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.newcore.letstryit.R
import com.newcore.letstryit.model.local.prefrence.MySettingsSharedPreference
import com.newcore.letstryit.model.repositories.AccountRepo
import com.newcore.letstryit.model.repositories.AuthenticationFacade
import com.newcore.letstryit.model.repositories.SettingsSharedPreferenceRepo
import com.newcore.letstryit.util.Either
import com.newcore.letstryit.util.exceptions.InValidEmailOrPassword
import com.newcore.letstryit.util.exceptions.SomethingWrongHappened

class LoginViewModel(val app: Application) : AndroidViewModel(app) {

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

    private var loginForm = LoginFormValidator()
    private val loginFormMutableLiveData = MutableLiveData(loginForm)
    val loginFormLiveData: LiveData<LoginFormValidator> = loginFormMutableLiveData

    private val loginErrorMutableLiveData = MutableLiveData<String?>()
    val loginErrorLiveData = loginErrorMutableLiveData


    fun withForm(onChange: (LoginFormValidator) -> Unit) {
        loginErrorMutableLiveData.value = null
        onChange(loginForm)
        notifyChange()
    }

    fun loginAndClear(): Boolean {
        if (loginForm.checkFormValidation())
            return when (val src = login()) {
                is Either.Failure -> {
                    loginErrorMutableLiveData.value = when (src.failure) {
                        is InValidEmailOrPassword -> app.getString(R.string.invalid_email_or_password)
                        is SomethingWrongHappened -> app.getString(R.string.something_wrong_happened)
                    }
                    false
                }
                is Either.Success -> {
                    clearForm()
                    true
                }
            }
        notifyChange()
        return false
    }

    fun isFormValidatorEnable() = loginForm.checkIt

    fun getSettings() = settingsSharedPreference.getSettings()

    private fun login() = authenticationFacade.login(
        loginForm.emailValidator.value!!,
        loginForm.passwordValidator.value!!
    )

    private fun clearForm() {
        loginForm = LoginFormValidator()
        notifyChange()
    }

    private fun notifyChange() {
        loginFormMutableLiveData.value = loginForm
    }
}