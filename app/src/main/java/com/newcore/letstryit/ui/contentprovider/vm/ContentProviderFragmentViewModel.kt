package com.newcore.letstryit.ui.contentprovider.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newcore.letstryit.model.entites.User
import com.newcore.letstryit.model.repositories.UserRepo
import com.newcore.letstryit.ui.contentprovider.vm.entities.UserEntitiesValidation

class ContentProviderFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo by lazy {
        UserRepo(application.contentResolver)
    }

    val userForm = UserEntitiesValidation()

    private val userFormMutableLiveData = MutableLiveData(userForm)
    val userFormLiveData = userFormMutableLiveData

    fun onUserFormChange(onChange: (UserEntitiesValidation) -> Unit) {
        onChange(userForm)
        notifyChange()
    }

    fun saveUser(): Boolean {
        if (userForm.isValidForm()) {
            userRepo.insert(
                User(
                    name = userForm.nameValidator.value!!,
                    email = userForm.emailValidator.value!!,
                    phoneNumber = userForm.phoneNumberValidator.value!!
                )
            )
            userForm.clearForm()
            return true
        }
        notifyChange()
        return false
    }

    fun notifyChange() {
        userFormMutableLiveData.value = userForm
    }

}