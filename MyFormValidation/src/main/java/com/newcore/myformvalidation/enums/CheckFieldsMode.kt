package com.newcore.myformvalidation.enums

import android.text.InputType

enum class CheckFieldsMode {
    Always, AfterFirstSubmit, EverySubmit
}

enum class InputTextClass(val classType: Int) {
    Number(InputType.TYPE_CLASS_NUMBER),
    Text(InputType.TYPE_CLASS_TEXT),
    DateTime(InputType.TYPE_CLASS_DATETIME),
    Phone(InputType.TYPE_CLASS_PHONE)
}