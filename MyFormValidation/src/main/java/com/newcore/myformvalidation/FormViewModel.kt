package com.newcore.myformvalidation

import androidx.fragment.app.Fragment

class FormViewModel {

    companion object {
        private var formViewModel2 = HashMap<Class<*>, FormViewModel>()

        fun getInstance(refactorClass: Class<*>): FormViewModel {
            return if (formViewModel2.containsKey(refactorClass))
                formViewModel2[refactorClass]!!
            else
                FormViewModel().also {
                    formViewModel2[refactorClass] = it
                }
        }
    }


    var myForm: MyForm? = null

}

fun Fragment.vmForm(myForm: MyForm.() -> Unit): MyForm {
    val vm = FormViewModel.getInstance(this.javaClass)

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