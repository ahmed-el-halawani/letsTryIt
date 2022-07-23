package com.newcore.myformvalidation

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.newcore.core.ViewContainer


class FormViewModel {

    companion object {
        private var formViewModel2: Pair<Class<*>, FormViewModel>? = null

        fun getInstance(refactorClass: Class<*>): FormViewModel {
            return if (formViewModel2 == null) {
                FormViewModel().also {
                    formViewModel2 = Pair(refactorClass, it)
                }
            } else if (formViewModel2!!.first == refactorClass) {
                formViewModel2!!.second
            } else {
                FormViewModel().also {
                    formViewModel2 = Pair(refactorClass, it)
                }
            }
        }

        fun removeVm(refactorClass: Class<*>) {
            formViewModel2 = null
        }
    }


    var myForm: MyForm? = null

}

fun Fragment.vmForm(viewContainer: ViewContainer? = null, myForm: MyForm.() -> Unit): MyForm {
    //    val vm = FormViewModel.getInstance(this.javaClass)

    val container = viewContainer ?: object : ViewContainer {
        override fun <T : View> findViewById(id: Int): T =
            requireView().findViewById(id)
    }

    return MyForm(container).apply {
        myForm(this)
        //        vm.myForm?.checkFieldsMode?.also {
        //            this.checkFieldsMode(it)
        //        }
        //        vm.myForm = this
    }.also { it.start(container) }

}

fun Activity.vmForm(viewContainer: ViewContainer? = null, myForm: MyForm.() -> Unit): MyForm {
    //    val vm = FormViewModel.getInstance(this.javaClass)

    val container = viewContainer ?: object : ViewContainer {
        override fun <T : View> findViewById(id: Int): T =
            this@vmForm.findViewById(id)
    }

    return MyForm(container).apply {
        myForm(this)
        //        vm.myForm?.checkFieldsMode?.also {
        //            this.checkFieldsMode(it)
        //        }
        //        vm.myForm = this
    }.also { it.start(container) }

}
