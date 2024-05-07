package com.ionut.sneakerdata.model

data class MessageDialogModel(
    val btnText: Int,
    val btnBackground: Int,
    val subTitle: String,
    val title: String,
    var overrideOnClose : Boolean = false,
    var onCancelled: (() -> Unit)? = null,
    var onButtonPressed: () -> Unit
)