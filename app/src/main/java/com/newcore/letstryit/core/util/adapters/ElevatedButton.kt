package com.newcore.letstryit.core.util.adapters

data class ElevatedButton(
    val name: String,
    val description: String = "",
    val onClick: () -> Unit,
)