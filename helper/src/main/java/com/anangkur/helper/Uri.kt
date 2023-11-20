package com.anangkur.helper

import android.net.Uri

fun String?.toUriOrNull(): Uri? {
    return if (!isNullOrEmpty()) {
        Uri.parse(this)
    } else {
        null
    }
}