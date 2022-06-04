package com.newcore.letstryit.ui.contentprovider

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.letstryit.data.entites.User
import com.newcore.letstryit.data.repositories.UserRepo

class ContentProviderFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo by lazy {
        UserRepo(application.contentResolver)
    }

    private var userForm = CompleteDataForm()
    private val userFormMutableLiveData = MutableLiveData(userForm)
    val userFormLiveData = userFormMutableLiveData

    fun withForm(onChange: (CompleteDataForm) -> Unit) {
        onChange(userForm)
        notifyChange()
    }

    fun saveAndClear(): Boolean {
        return userForm.checkFormValidation().also {
            if (it) {
                saveUser()
                clearForm()
            }
            notifyChange()
        }
    }

    fun isFormValidatorEnable() = userForm.checkIt

    private fun saveUser() {
        userRepo.insert(
            User(
                name = userForm.nameValidator.value!!,
                email = userForm.emailValidator.value!!,
                phoneNumber = userForm.phoneNumberValidator.value!!
            )
        )
    }

    private fun clearForm() {
        userForm = CompleteDataForm()
    }

    private fun notifyChange() {
        userFormMutableLiveData.value = userForm
    }

}