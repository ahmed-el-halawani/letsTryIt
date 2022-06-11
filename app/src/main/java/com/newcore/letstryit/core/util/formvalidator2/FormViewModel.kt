package com.newcore.letstryit.core.util.formvalidator2

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {
    var myForm: MyForm? = null
}

fun Fragment.vmForm(myForm: MyForm.() -> Unit): MyForm {
    val vm: FormViewModel by viewModels()
    if (vm.myForm == null) {
        MyForm().apply {
            myForm(this)
            start(requireView())
            vm.myForm = this
        }
    } else {
        vm.myForm!!.start(this.requireView())
    }

    return vm.myForm!!
}