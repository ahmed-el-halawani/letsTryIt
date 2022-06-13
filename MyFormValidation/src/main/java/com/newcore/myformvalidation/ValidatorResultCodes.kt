package com.newcore.myformvalidation

enum class ValidatorResultCodes(val message: String) {
    EmptyField("field must not be empty"),
    MoreThanUpper("must be lower than"),
    LessThanLower("must be upper than"),
    InvalidEmail("You have entered an invalid email address!"),
    InvalidNumber("You have entered an invalid number!"),
    IsChecked("must check"),
}
