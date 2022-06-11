package com.newcore.letstryit.core.util.formvalidator2

enum class ValidatorResultCodes(val message: String) {
    EmptyField("field must not be empty"),
    MoreThanUpper("must be lower than"),
    LessThanLower("must be upper than"),
    InvalidEmail("You have entered an invalid email address!"),
    InvalidNumber("You have entered an invalid number!"),
}
