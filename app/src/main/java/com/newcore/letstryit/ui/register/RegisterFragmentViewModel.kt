package com.newcore.letstryit.ui.register

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.letstryit.R
import com.newcore.letstryit.data.entites.Account
import com.newcore.letstryit.data.local.prefrence.MySettingsSharedPreference
import com.newcore.letstryit.data.repositories.AccountRepo
import com.newcore.letstryit.data.repositories.AuthenticationFacade
import com.newcore.letstryit.data.repositories.SettingsSharedPreferenceRepo
import com.newcore.letstryit.core.util.Either
import com.newcore.letstryit.core.util.exceptions.SomethingWrongHappened
import com.newcore.letstryit.core.util.exceptions.UserAlreadyExist

class RegisterFragmentViewModel(val app: Application) : AndroidViewModel(app) {
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

    private var registerForm = RegisterForm()
    private val registerFormMutableLiveDouble = MutableLiveData(registerForm)
    val registerFormLiveData = registerFormMutableLiveDouble

    private val registerErrorMutableLiveData = MutableLiveData<String?>()
    val registerErrorLiveData = registerErrorMutableLiveData

    fun settingsLiveData() = settingsSharedPreference.getSettingsLiveData()

    fun withForm(onChange: (RegisterForm) -> Unit) {
        registerErrorMutableLiveData.value = null
        onChange(registerForm)
        notifyChange()
    }

    fun saveAndClear(): Boolean {
        if (registerForm.checkFormValidation()) {
            return when (val src = save()) {
                is Either.Failure -> {
                    registerErrorMutableLiveData.value = when (src.failure) {
                        SomethingWrongHappened -> app.getString(R.string.something_wrong_happened)
                        UserAlreadyExist -> app.getString(R.string.user_already_exist)
                    }
                    false
                }
                is Either.Success -> {
                    clearForm()
                    true
                }
            }
        }
        notifyChange()
        return false
    }

    fun isFormValidatorEnable() = registerForm.checkIt

    private fun save() = authenticationFacade.register(
        Account(
            email = registerForm.emailValidator.value!!,
            password = registerForm.passwordValidator.value!!,
            userName = registerForm.userNameValidator.value!!
        )
    )

    private fun clearForm() {
        registerForm = RegisterForm()
        notifyChange()
    }

    private fun notifyChange() {
        registerFormMutableLiveDouble.value = registerForm
    }


}