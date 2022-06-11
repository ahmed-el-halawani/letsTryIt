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
        val form = MyForm(this.requireView())
        myForm(form)
        form.start()
        vm.myForm = form
    } else {
        vm.myForm!!.updateView(this.requireView())
    }

    return vm.myForm!!
}