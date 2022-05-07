package com.newcore.letstryit.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.letstryit.model.entites.Account
import com.newcore.letstryit.model.repositories.AccountRepo

class RegisterFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val accountRepo: AccountRepo by lazy {
        AccountRepo(application.contentResolver)
    }

    private var registerForm = RegisterForm()
    private val registerFormMutableLiveDouble = MutableLiveData(registerForm)
    val registerFormLiveData = registerFormMutableLiveDouble

    fun withForm(onChange: (RegisterForm) -> Unit) {
        onChange(registerForm)
        notifyChange()
    }

    fun saveAndClear() = registerForm.checkFormValidation().also {
        if (it) {
            save()
            clearForm()
        }
        notifyChange()
    }

    fun isFormValidatorEnable() = registerForm.checkIt

    private fun save() = accountRepo.insert(
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