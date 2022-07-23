package com.newcore.myformvalidation

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.newcore.core.ViewContainer

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

    val container = viewContainer ?: object : ViewContainer {
        override fun <T : View> findViewById(id: Int): T =
            requireView().findViewById(id)
    }

    if (vm.myForm == null) {
        MyForm(container).apply {
            myForm(this)
            vm.myForm = this
        }
    }

    return vm.myForm!!.also { it.start(container) }
}

fun Activity.vmForm(viewContainer: ViewContainer? = null, myForm: MyForm.() -> Unit): MyForm {
    val vm = FormViewModel.getInstance(this.javaClass)

    val container = viewContainer ?: object : ViewContainer {
        override fun <T : View> findViewById(id: Int): T =
            this@vmForm.findViewById(id)
    }

    if (vm.myForm == null) {
        MyForm(container).apply {
            myForm(this)
            vm.myForm = this
        }
    }

    return vm.myForm!!.also { it.start(container) }
}
