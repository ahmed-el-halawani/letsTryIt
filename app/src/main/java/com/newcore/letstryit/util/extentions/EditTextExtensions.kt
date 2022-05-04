package com.newcore.letstryit.util.extentions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

object EditTextExtensions {
    fun TextView.onTextChange(onTextChange: (CharSequence?) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return onTextChange(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}