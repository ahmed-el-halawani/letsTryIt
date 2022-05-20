package com.newcore.letstryit.core.adapters

data class IntentButton(
    val name: String,
    val description: String = "",
    val onClick: () -> Unit,
)