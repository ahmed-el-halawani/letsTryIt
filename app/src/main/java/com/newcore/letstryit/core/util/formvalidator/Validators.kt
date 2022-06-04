package com.newcore.letstryit.core.util.formvalidator

import java.lang.Long.parseLong

object Validators {
    fun emptyValidator(target: String?): String? {
        if (target.isNullOrBlank())
            return "must not be empty"

        return null
    }

    fun lengthValidator(target: String, upperBound: Int, lowerBound: Int): String? {
        if (target.length > upperBound)
            return "must be lower than $upperBound"
        if (target.length < lowerBound)
            return "must be greater than $lowerBound "
        return null
    }

    fun emailValidator(target: String): String? {
        val emailRegex = Regex("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$")
        if (!emailRegex.matches(target))
            return "You have entered an invalid email address!"

        return null
    }

    fun numberValidator(target: String): String? {
        try {
            parseLong(target)
        } catch (t: NumberFormatException) {
            return "please enter valid number"
        }

        return null
    }

    fun confirmValidator(target: String?, confirmFrom: String?): String? {
        if (target != confirmFrom)
            return "password and confirm password not the same"
        return null
    }
}