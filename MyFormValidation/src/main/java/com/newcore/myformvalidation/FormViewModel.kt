package com.newcore.myformvalidation

import android.app.Activity
import android.view.View
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

fun Fragment.vmForm(viewContainer: ViewContainer? = null, myForm: MyForm.() -> Unit): MyForm {
    val vm = FormViewModel.getInstance(this.javaClass)

    if (vm.myForm == null) {
        MyForm().apply {
            myForm(this)
            start(viewContainer ?: object : ViewContainer {
                override fun <T : View> findViewById(id: Int): T =
                    requireView().findViewById(id)

            })
            vm.myForm = this
        }
    } else {
        vm.myForm!!.start(viewContainer ?: object : ViewContainer {
            override fun <T : View> findViewById(id: Int): T =
                requireView().findViewById(id)

        })
    }

    return vm.myForm!!
}

fun Activity.vmForm(viewContainer: ViewContainer? = null, myForm: MyForm.() -> Unit): MyForm {
    val vm = FormViewModel.getInstance(this.javaClass)

    if (vm.myForm == null) {
        MyForm().apply {
            myForm(this)
            start(viewContainer ?: object : ViewContainer {
                override fun <T : View> findViewById(id: Int): T =
                    this@vmForm.findViewById(id)

            })
            vm.myForm = this
        }
    } else {
        vm.myForm!!.start(viewContainer ?: object : ViewContainer {
            override fun <T : View> findViewById(id: Int): T =
                this@vmForm.findViewById(id)

        })
    }

    return vm.myForm!!
}

interface ViewContainer {
    fun <T : View> findViewById(id: Int): T
}